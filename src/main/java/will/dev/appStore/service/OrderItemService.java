package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import will.dev.appStore.dto.OrderItemDTO;
import will.dev.appStore.entites.Order;
import will.dev.appStore.entites.OrderItem;
import will.dev.appStore.mapper.OrderItemDtoMapper;
import will.dev.appStore.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemDtoMapper orderItemDtoMapper;

    // Create
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Read (Get All)
    public Stream<OrderItemDTO> getAllOrderItems() {
        return orderItemRepository.findAll().stream().map(orderItemDtoMapper);
    }

    // Read (Get By Id)
    public OrderItemDTO getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .map(orderItemDtoMapper).orElseThrow(() -> new EntityNotFoundException("Aucune commande n'existe avec cet identifiant"));
    }
    // Read (Get List)
    public Stream<OrderItemDTO> getListOrderItemById(Long id) {
        List<OrderItemDTO> orderItemDtoList = this.orderItemRepository.findOrderItemByOrderId(id)
                .stream()
                .map(orderItemDtoMapper)
                .toList();
        if (orderItemDtoList.isEmpty()){
            throw new EntityNotFoundException("Liste vide");
        }
        return orderItemDtoList.stream();
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