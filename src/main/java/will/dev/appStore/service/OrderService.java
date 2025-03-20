package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import will.dev.appStore.dto.OrderDTO;
import will.dev.appStore.dto.OrderRequest;
import will.dev.appStore.entites.*;
import will.dev.appStore.mapper.OrderDtoMapper;
import will.dev.appStore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDtoMapper orderDtoMapper;

    // Create
    public Order createOrder(Order order) {return orderRepository.save(order);}


    // Read (Get All)
    public Stream<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(orderDtoMapper);
    }

    // Read (Get By Id)
    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id).map(orderDtoMapper).orElseThrow(() -> new EntityNotFoundException("Aucune commande n'existe avec cet identifiant"));
    }

    // Read (Get By Order Key)
    public OrderDTO getOrderByOrderKey(String orderKey) {
        return orderRepository.findByOrderKey(orderKey).map(orderDtoMapper).orElseThrow(() -> new EntityNotFoundException("Aucune commande n'existe avec cette clé"));
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