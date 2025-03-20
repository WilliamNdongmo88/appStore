package will.dev.appStore.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import will.dev.appStore.dto.*;
import will.dev.appStore.entites.Order;
import will.dev.appStore.entites.OrderItem;
import will.dev.appStore.entites.Product;
import will.dev.appStore.entites.User;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class OrderItemDtoMapper implements Function<OrderItem, OrderItemDTO> {

    private final Function<User, UserDTO> userDtoMapper;
    private final Function<Product, ProductDTO> productDtoMapper;
    private final Function<Order, OrderDTO> orderDtoMapper;

    @Override
    public OrderItemDTO apply(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                userDtoMapper.apply(orderItem.getUser()),
                productDtoMapper.apply(orderItem.getProduct()),
                orderDtoMapper.apply(orderItem.getOrder()),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getDeletedAt(),
                orderItem.getDeletedBy() != null ? userDtoMapper.apply(orderItem.getDeletedBy()) : null,
                orderItem.getCreatedAt(),
                orderItem.getUpdatedAt()
        );
    }
}
