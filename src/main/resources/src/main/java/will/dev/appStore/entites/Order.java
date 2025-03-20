package will.dev.appStore.entites;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.MERGE)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "delivery_mode_id")
    private DeliveryMode deliveryMode;

    @ManyToOne
    @JoinColumn(name = "payment_mode_id")
    private PaymentMode paymentMode;

    @Column(nullable = false)
    private String orderKey;

    @Column(nullable = false)
    private String billingStatus;

    private double totalToBePaid;
    private boolean paid = false;
    private double totalPaid;
    private double totalBalance;

    private Date proposedDeliveryStartDate;
    private Date proposedDeliveryDestinationReachDate;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String addressLine1;

    @Column(nullable = false)
    private String addressLine2;

    private String postCode;
    private String townCity;
    private String stateRegion;

    @Column(nullable = false)
    private String country;

    private String deliveryInstructions;

    @ManyToOne
    @JoinColumn(name = "delivered_by")
    private User deliveredBy;

    private String deletedAt;

    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private User deletedBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}