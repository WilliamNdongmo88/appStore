package will.dev.appStore.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import will.dev.appStore.dto.OrderDTO;
import will.dev.appStore.dto.PaymentDTO;
import will.dev.appStore.dto.UserDTO;
import will.dev.appStore.entites.Order;
import will.dev.appStore.entites.Payment;
import will.dev.appStore.entites.User;

import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class PaymentDtoMapper implements Function<Payment, PaymentDTO> {
    private final Function<User, UserDTO> userDtoMapper;
    private final Function<Order, OrderDTO> orderDtoMapper;

    /*public PaymentDtoMapper(Function<User, UserDTO> userDtoMapper, Function<Order, OrderDTO> orderDtoMapper) {
        this.userDtoMapper = userDtoMapper;
        this.orderDtoMapper = orderDtoMapper;
    }*/

    @Override
    public PaymentDTO apply(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                userDtoMapper.apply(payment.getUser()),
                orderDtoMapper.apply(payment.getOrder()),
                payment.getAmount(),
                payment.getDeletedAt(),
                payment.getDeletedBy() != null ? userDtoMapper.apply(payment.getDeletedBy()) : null,
                payment.getCreatedAt(),
                payment.getUpdatedAt());
    }
}
