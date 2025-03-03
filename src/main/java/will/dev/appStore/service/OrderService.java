package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import will.dev.appStore.entites.Order;
import will.dev.appStore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Create
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Read (Get All)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Read (Get By Id)
    public Order getOrderById(Long id) {
        Optional<Order> optionalorder =  this.orderRepository.findById(id);
        return optionalorder.orElseThrow(() -> new EntityNotFoundException("Aucune commande n'existe avec cet identifiant"));
    }

    // Read (Get By Order Key)
    public Order getOrderByOrderKey(String orderKey) {
        Optional<Order> optionalorder =  this.orderRepository.findByOrderKey(orderKey);
        return optionalorder.orElseThrow(() -> new EntityNotFoundException("Aucune commande n'existe avec cette clé"));
    }

    // Update
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));

        order.setUser(orderDetails.getUser());
        order.setOrderItems(orderDetails.getOrderItems());
        order.setDeliveryMode(orderDetails.getDeliveryMode());
        order.setPaymentMode(orderDetails.getPaymentMode());
        order.setOrderKey(orderDetails.getOrderKey());
        order.setBillingStatus(orderDetails.getBillingStatus());
        order.setTotalToBePaid(orderDetails.getTotalToBePaid());
        order.setPaid(orderDetails.isPaid());
        order.setTotalPaid(orderDetails.getTotalPaid());
        order.setTotalBalance(orderDetails.getTotalBalance());
        order.setProposedDeliveryStartDate(orderDetails.getProposedDeliveryStartDate());
        order.setProposedDeliveryDestinationReachDate(orderDetails.getProposedDeliveryDestinationReachDate());
        order.setFullName(orderDetails.getFullName());
        order.setEmail(orderDetails.getEmail());
        order.setPhone(orderDetails.getPhone());
        order.setAddressLine1(orderDetails.getAddressLine1());
        order.setAddressLine2(orderDetails.getAddressLine2());
        order.setPostCode(orderDetails.getPostCode());
        order.setTownCity(orderDetails.getTownCity());
        order.setStateRegion(orderDetails.getStateRegion());
        order.setCountry(orderDetails.getCountry());
        order.setDeliveryInstructions(orderDetails.getDeliveryInstructions());
        order.setDeliveredBy(orderDetails.getDeliveredBy());
        order.setDeletedAt(orderDetails.getDeletedAt());
        order.setDeletedBy(orderDetails.getDeletedBy());

        return orderRepository.save(order);
    }

    // Delete
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));

        orderRepository.delete(order);
    }
}