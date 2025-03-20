package will.dev.appStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import will.dev.appStore.entites.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
