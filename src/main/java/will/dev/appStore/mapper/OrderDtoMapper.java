package will.dev.appStore.mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import will.dev.appStore.dto.*;
import will.dev.appStore.entites.Order;
import will.dev.appStore.entites.User;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class OrderDtoMapper implements Function<Order, OrderDTO> {

    private final Function<User, UserDTO> userDtoMapper;
    //private final Function<OrderItem, OrderItemDTO> orderItemDtoMapper;
    //private final Function<DeliveryMode, DeliveryModeDTO> deliveryModeDtoMapper;
    //private final Function<PaymentMode, PaymentModeDTO> paymentModeDtoMapper;


    @Override
    public OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getId(),
                userDtoMapper.apply(order.getUser()),
                //deliveryModeDtoMapper.apply(order.getDeliveryMode()),
                //paymentModeDtoMapper.apply(order.getPaymentMode()),
                order.getOrderKey(),
                order.getBillingStatus(),
                order.getTotalToBePaid(),
                order.isPaid(),
                order.getTotalPaid(),
                order.getTotalBalance(),
                order.getProposedDeliveryStartDate(),
                order.getProposedDeliveryDestinationReachDate(),
                order.getFullName(),
                order.getEmail(),
                order.getPhone(),
                order.getAddressLine1(),
                order.getAddressLine2(),
                order.getPostCode(),
                order.getTownCity(),
                order.getStateRegion(),
                order.getCountry(),
                order.getDeliveryInstructions(),
                order.getDeliveredBy() != null ? userDtoMapper.apply(order.getDeliveredBy()) : null,
                order.getDeletedAt(),
                order.getDeletedBy() != null ? userDtoMapper.apply(order.getDeletedBy()) : null,
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}