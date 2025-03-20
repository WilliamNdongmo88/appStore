package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import will.dev.appStore.entites.Order;
import will.dev.appStore.entites.OrderItem;
import will.dev.appStore.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Create
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Read (Get All)
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Read (Get By Id)
    public OrderItem getOrderItemById(Long id) {
        Optional<OrderItem> optionalorderItem =  this.orderItemRepository.findById(id);
        return optionalorderItem.orElseThrow(() -> new EntityNotFoundException("Aucune commande n'existe avec cet identifiant"));
    }

    // Update
    public OrderItem updateOrderItem(Long id, OrderItem orderItemDetails) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id " + id));

        orderItem.setUser(orderItemDetails.getUser());
        orderItem.setProduct(orderItemDetails.getProduct());
        orderItem.setOrder(orderItemDetails.getOrder());
        orderItem.setQuantity(orderItemDetails.getQuantity());
        orderItem.setPrice(orderItemDetails.getPrice());
        orderItem.setDeletedAt(orderItemDetails.getDeletedAt());
        orderItem.setDeletedBy(orderItemDetails.getDeletedBy());

        return orderItemRepository.save(orderItem);
    }

    // Delete
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id " + id));

        orderItemRepository.delete(orderItem);
    }
}