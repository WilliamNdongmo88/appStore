package will.dev.appStore.entites;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "product_images")
@Data
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "added_by")
    private User addedBy;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Embedded
    private ImagePath imagePath;

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