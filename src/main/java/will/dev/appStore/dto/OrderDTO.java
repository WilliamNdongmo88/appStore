package will.dev.appStore.dto;

import java.util.Date;
import java.util.List;

public record OrderDTO(
        Long id,
        UserDTO user,
        //List<OrderItemDTO> orderItems,
        //DeliveryModeDTO deliveryMode,
        //PaymentModeDTO paymentMode,
        String orderKey,
        String billingStatus,
        double totalToBePaid,
        boolean paid,
        double totalPaid,
        double totalBalance,
        Date proposedDeliveryStartDate,
        Date proposedDeliveryDestinationReachDate,
        String fullName,
        String email,
        String phone,
        String addressLine1,
        String addressLine2,
        String postCode,
        String townCity,
        String stateRegion,
        String country,
        String deliveryInstructions,
        UserDTO deliveredBy,
        String deletedAt,
        UserDTO deletedBy,
        Date createdAt,
        Date updatedAt
) {}