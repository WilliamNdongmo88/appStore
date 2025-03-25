package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import will.dev.appStore.controller.Validation;
import will.dev.appStore.dto.UserDTO;
import will.dev.appStore.entites.User;
import will.dev.appStore.mapper.UserDtoMapper;
import will.dev.appStore.repository.UserRepository;
import will.dev.appStore.repository.ValidationRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final ValidationService validationService;
    private final ValidationRepository validationRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Aucun utilisateur ne correspond a cet identifiant"));
    }

    public Stream<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream().map(userDtoMapper);
    }

    /*public User getUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(()-> new EntityNotFoundException("User not found with id " + id));
    }*/
    public UserDTO getUser(long id) {
        return userRepository.findById(id)
                .map(userDtoMapper)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }


    public User modifier(Long id, User userDetails) {
        User userConnected = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDetails.setVerifiedBy(userConnected);
        User userDansLaBD = this.userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found with id " + id));

        userDansLaBD.setUsername(userDetails.getUsername());
        userDansLaBD.setFirstName(userDetails.getFirstName());
        userDansLaBD.setOtherNames(userDetails.getOtherNames());
        userDansLaBD.setLastName(userDetails.getLastName());
        userDansLaBD.setUserImagePath(userDetails.getUserImagePath());
        userDansLaBD.setEnterpriseName(userDetails.getEnterpriseName());
        userDansLaBD.setEmail(userDetails.getEmail());
        userDansLaBD.setPhone(userDetails.getPhone());
        userDansLaBD.setPassword(userDetails.getPassword());
        userDansLaBD.setRole(userDetails.getRole());
        userDansLaBD.setEmailVerified(userDetails.getEmailVerified());
        userDansLaBD.setAuthToken(userDetails.getAuthToken());
        userDansLaBD.setEmailVerifyToken(userDetails.getEmailVerifyToken());
        userDansLaBD.setPasswordResetToken(userDetails.getPasswordResetToken());
        userDansLaBD.setVerified(userDetails.isVerified());
        //userDansLaBD.setVerifiedBy(userDetails.getVerifiedBy());
        userDansLaBD.setOnline(userDetails.isOnline());
        userDansLaBD.setShowOnlineStatus(userDetails.isShowOnlineStatus());
        userDansLaBD.setLastTimeActive(userDetails.getLastTimeActive());
        userDansLaBD.setActive(userDetails.isActive());
        userDansLaBD.setDeletedAt(userDetails.getDeletedAt());

        return userRepository.save(userDansLaBD);
    }

    //Activation
    public ResponseEntity<?> activation(Map<String, String> activation) {
        Validation validation = this.validationService.lireCode(activation.get("code"));
        if (Instant.now().isAfter(validation.getExpiration())) {
            throw new RuntimeException("Votre code a expiré");
        }
        User userActiver = this.userRepository.findById(validation.getUser().getId()).orElseThrow(() -> new RuntimeException("Utilisateur inconnu"));
        userActiver.setActive(true);
        Instant creation = Instant.now();
        validation.setActivation(creation);
        validation.setValidationDay(LocalDate.now(ZoneId.systemDefault()));
        this.validationRepository.save(validation);
        this.userRepository.save(userActiver);
        return ResponseEntity.ok("### Compte activé ### ->" + userActiver.isActive());
    }

    // Delete
    public void deleteUser(Long id,User userDetails) {
        User userConnected = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDetails.setVerifiedBy(userConnected);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        userRepository.delete(user);
    }
}
