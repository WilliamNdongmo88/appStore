package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import will.dev.appStore.dto.UserDTO;
import will.dev.appStore.entites.User;
import will.dev.appStore.mapper.UserDtoMapper;
import will.dev.appStore.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with username" + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
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
        userDansLaBD.setVerifiedBy(userDetails.getVerifiedBy());
        userDansLaBD.setOnline(userDetails.isOnline());
        userDansLaBD.setShowOnlineStatus(userDetails.isShowOnlineStatus());
        userDansLaBD.setLastTimeActive(userDetails.getLastTimeActive());
        userDansLaBD.setActive(userDetails.isActive());
        userDansLaBD.setDeletedAt(userDetails.getDeletedAt());

        return userRepository.save(userDansLaBD);
    }

    // Delete
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        userRepository.delete(user);
    }
}
