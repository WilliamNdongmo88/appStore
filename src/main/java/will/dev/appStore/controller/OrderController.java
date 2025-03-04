package will.dev.appStore.controller;

import lombok.RequiredArgsConstructor;
import will.dev.appStore.dto.OrderRequest;
import will.dev.appStore.entites.Order;
import will.dev.appStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    // Create
    @PostMapping("creer")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // Read (Get All)
    @GetMapping("all_order")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // Read (Get By Order Key)
    @GetMapping("/order-key/{orderKey}")
    public Order getOrderByOrderKey(@PathVariable String orderKey) {
        return orderService.getOrderByOrderKey(orderKey);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}