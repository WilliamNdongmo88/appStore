package will.dev.appStore.repository;

import will.dev.appStore.entites.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire

    List<OrderItem> findOrderItemByOrderId(Long id);
}