package will.dev.appStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import will.dev.appStore.repository.UserRepository;
import will.dev.appStore.entites.User;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1")
public class RegistrationLoginController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping(path = "register")
    public ResponseEntity<?> userRegister(@RequestBody User user){
        if (userRepository.findByUsername(user.getUsername()) != null){
            return ResponseEntity.badRequest().body("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping(path = "login")
    public ResponseEntity<?> userLogin(@RequestBody User user) {
        try {
            // Authentification de l'utilisateur
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            // Récupération de l'utilisateur à partir de la base de données
            User authenticatedUser = userRepository.findByUsername(user.getUsername());

            // Retourner le rôle
            return ResponseEntity.ok("Login Successful. Role: " + authenticatedUser.getRole());
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
