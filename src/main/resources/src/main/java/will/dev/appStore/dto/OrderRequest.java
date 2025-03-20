package will.dev.appStore.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private List<Long> orderItemIds;
    private Long deliveryModeId;
    private Long paymentModeId;
    private String orderKey;
    private String billingStatus;
    private double totalToBePaid;
    private boolean paid;
    private double totalPaid;
    private double totalBalance;
    private Date proposedDeliveryStartDate;
    private Date proposedDeliveryDestinationReachDate;
    private String fullName;
    private String email;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String postCode;
    private String townCity;
    private String stateRegion;
    private String country;
    private String deliveryInstructions;
}
