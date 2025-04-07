package will.dev.appStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import will.dev.appStore.dto.OrderDTO;
import will.dev.appStore.dto.OrderItemDTO;
import will.dev.appStore.dto.OrderRequest;
import will.dev.appStore.entites.Order;
import will.dev.appStore.entites.OrderItem;
import will.dev.appStore.service.OrderItemService;
import will.dev.appStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    // Create
    @PostMapping("creer")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // Read (Get All)
    @GetMapping("all_order")
    public Stream<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // Read (Get By Order Key)
    @GetMapping("/order-key/{orderKey}")
    public OrderDTO getOrderByOrderKey(@PathVariable String orderKey) {
        return orderService.getOrderByOrderKey(orderKey);
    }

    //Read (Get OrderItem By Id)
    @PreAuthorize("hasAnyAuthority('ADMIN_READ','MANAGER_READ','USER_READ')")
    @GetMapping("/{orderId}/items")
    public Stream<OrderItemDTO> getListOrderItemById(@PathVariable Long orderId){
        return orderItemService.getListOrderItemById(orderId);
    }

    // Update
    @PreAuthorize("hasAnyAuthority('USER_UPDATE')")
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDetails));
    }

    // Delete
    @PreAuthorize("hasAnyAuthority('USER_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}