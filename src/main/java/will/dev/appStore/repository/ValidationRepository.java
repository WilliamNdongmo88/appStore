package will.dev.appStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import will.dev.appStore.entites.Validation;
import will.dev.appStore.entites.User;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation, Long> {
    Optional<Validation> findByCode(String code);

    Optional<Validation> findByUser(User user);
}
