package will.dev.appStore.repository;

import will.dev.appStore.entites.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentModeRepository extends JpaRepository<PaymentMode, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
    Optional<PaymentMode> findBySlug(String slug);
}