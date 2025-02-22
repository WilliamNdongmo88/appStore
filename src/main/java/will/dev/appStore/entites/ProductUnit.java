package will.dev.appStore.entites;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "product_units")
@Data
public class ProductUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "added_by")
    private User addedBy;

    @ManyToOne
    @JoinColumn(name = "bought_by")
    private User boughtBy;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private String sku;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String purchasePrice;

    @Column(nullable = false)
    private String retailPrice;

    private String initialRetailPrice;

    private boolean sold = false;

    private boolean isProductDefault = false;

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