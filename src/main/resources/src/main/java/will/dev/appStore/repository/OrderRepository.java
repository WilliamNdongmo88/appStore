package will.dev.appStore.repository;

import will.dev.appStore.entites.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
    Optional<Order> findByOrderKey(String orderKey);
}