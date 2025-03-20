package will.dev.appStore.controller;

import will.dev.appStore.dto.OrderItemDTO;
import will.dev.appStore.entites.OrderItem;
import will.dev.appStore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // Create
    @PostMapping("creer")
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.createOrderItem(orderItem);
    }

    // Read (Get All)
    @GetMapping("all_orderItem")
    public Stream<OrderItemDTO> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public OrderItemDTO getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItemDetails) {
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, orderItemDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}