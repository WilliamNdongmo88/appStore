package will.dev.appStore.repository;

import will.dev.appStore.entites.DeliveryMode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryModeRepository extends JpaRepository<DeliveryMode, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}