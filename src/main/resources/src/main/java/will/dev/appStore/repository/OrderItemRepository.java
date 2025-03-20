package will.dev.appStore.repository;

import will.dev.appStore.entites.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}