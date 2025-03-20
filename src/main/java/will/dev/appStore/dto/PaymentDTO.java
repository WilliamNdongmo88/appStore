package will.dev.appStore.dto;

import java.util.Date;

public record PaymentDTO(
        Long id,
        UserDTO user,
        OrderDTO order,
        double amount,
        String deletedAt,
        UserDTO deletedBy,
        Date createdAt,
        Date updatedAt
) {}