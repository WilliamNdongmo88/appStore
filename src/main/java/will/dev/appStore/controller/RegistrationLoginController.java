package will.dev.appStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import will.dev.appStore.dto.AuthentificationDto;
import will.dev.appStore.entites.EmailValids;
import will.dev.appStore.entites.Role;
import will.dev.appStore.enums.TypeDeRole;
import will.dev.appStore.repository.EmailValidsRepository;
import will.dev.appStore.repository.UserRepository;
import will.dev.appStore.entites.User;
import will.dev.appStore.service.JwtService;
import will.dev.appStore.service.UserService;
import will.dev.appStore.service.ValidationService;

import javax.naming.AuthenticationException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "userAuth")
public class RegistrationLoginController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationService validationService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final EmailValidsRepository emailValidsRepository;

    @PostMapping(path = "register")
    public ResponseEntity<?> userRegister(@RequestBody User user){
        User userDansBD = this.userRepository.findByUsername(user.getUsername());
        if (userDansBD != null){
            return ResponseEntity.badRequest().body("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getEmail().contains(".admin")){
            Optional<EmailValids> emailValids = Optional.ofNullable(this.emailValidsRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("Email non valid")));
            System.out.println("Email: " + emailValids);
            if (emailValids.isPresent()){
                Role userRole = new Role();
                userRole.setLibelle(TypeDeRole.ADMIN);
                user.setRole(userRole);
                user = this.userRepository.save(user);
                this.validationService.enregistrer(user);//Utiliser pour activer un compte utilisateur
            }
        } else if (user.getEmail().contains(".manager")) {
            Optional<EmailValids> emailValids = Optional.ofNullable(this.emailValidsRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("Email non valid")));
            System.out.println("Email: " + emailValids);
            if (emailValids.isPresent()){
                Role userRole = new Role();
                userRole.setLibelle(TypeDeRole.MANAGER);
                user.setRole(userRole);
                user = this.userRepository.save(user);
                this.validationService.enregistrer(user);//Utiliser pour activer un compte utilisateur
            }
        }else {
            Role userRole = new Role();
            userRole.setLibelle(TypeDeRole.USER);
            user.setRole(userRole);
            user = this.userRepository.save(user);
            this.validationService.enregistrer(user);//Utiliser pour activer un compte utilisateur
        }
        this.validationService.enregistrer(user);
        return ResponseEntity.ok(userRepository.save(user));
    }

    //Activation
    @PostMapping("activation")
    public ResponseEntity<?> activation(@RequestBody Map<String, String> activation){
        return this.userService.activation(activation);
    }

    //New Activation Code
    @PostMapping("new-activation-code")
    public void newActivationCode(@RequestBody Map<String, String> param){
        this.userService.modifiedPassword(param);
    }

    //Modifier Password
    @PostMapping("modified-password")
    public void modifiedPassword(@RequestBody Map<String, String> param){
        this.userService.modifiedPassword(param);
    }

    //New Password
    @PostMapping("new-password")
    public void newPassword(@RequestBody Map<String, String> param){
        this.userService.newPassword(param);
    }

    @PostMapping(path = "login")
    public Map<String, String> userLogin(@RequestBody AuthentificationDto authDto) throws BadCredentialsException {
        try {
            // Authentification de l'utilisateur
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password()));

            System.out.println("username: " + authenticate);
            // Récupération de l'utilisateur à partir de la base de données
            Optional<User> authenticatedUser = this.userRepository.findByEmail(authDto.username());
            System.out.println("username: " + authenticatedUser.get().getUsername());
            if (authenticatedUser.get().isActive()){
                authenticatedUser.get().setOnline(true);
                this.userRepository.save(authenticatedUser.get());
                // Retourner le token
                return this.jwtService.generate(authDto.username());
            }else {
                throw new RuntimeException("Veuillez activer votre compte avant de vous connecter");
            }
        }catch(Exception ex){
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    //Refresh Token
    @PostMapping("refresh-token")
    public Map<String, String> refreshToken(@RequestBody Map<String, String> refreh){
        return this.jwtService.refreshToken(refreh);
    }

    @PostMapping("deconnexion")
    public void deconnexion(){
        this.jwtService.deconnexion();
    }
}
