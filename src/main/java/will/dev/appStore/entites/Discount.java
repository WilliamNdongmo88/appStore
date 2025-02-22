package will.dev.appStore.entites;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "discounts")
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "added_by")
    private User addedBy;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    private String valueUnit;

    private boolean usableOnce = false;
    private String usedAt;

    @ManyToOne
    @JoinColumn(name = "used_by")
    private User usedBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

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