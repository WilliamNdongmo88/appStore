package will.dev.appStore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import will.dev.appStore.entites.User;
import will.dev.appStore.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with username" + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && user.get().getId() == id){
            return user.get();
        }else {
            return null;
        }

    }

    public void modifier(int id, User user) {
        User userDansLaBD = this.getUser(id);//Recupération du user dans la bd
        if (Objects.equals(userDansLaBD.getId(), user.getId()) && userDansLaBD.getId() == id){
            //Ajout des informations recu au userDansLaBD
            userDansLaBD.setUsername(user.getUsername());
            userDansLaBD.setFirstName(user.getFirstName());
            userDansLaBD.setLastName(user.getLastName());
            userDansLaBD.setRole(user.getRole());
            this.userRepository.save(userDansLaBD);
        }
    }
}
