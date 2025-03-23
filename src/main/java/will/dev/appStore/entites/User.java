package will.dev.appStore.entites;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String username;

    @Column(nullable = false)
    private String firstName;

    private String otherNames;

    @Column(nullable = false)
    private String lastName;

    @Embedded
    private ImagePath userImagePath;

    private String enterpriseName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private Date emailVerified;
    private String authToken;
    private String emailVerifyToken;
    private String passwordResetToken;

    private boolean verified = false;

    @ManyToOne
    @JoinColumn(name = "verified_by")
    private User verifiedBy;

    private boolean online = false;
    private boolean showOnlineStatus = true;
    private String lastTimeActive;

    private boolean active = false;
    private String deletedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    // Nouveau champ pour stocker uniquement le jour
    private LocalDate createDay;

    // Méthode pour définir automatiquement le jour de la creation du compte
    @PrePersist
    public void prePersist() {
        this.createDay = LocalDate.now(ZoneId.systemDefault()); // Stocke la date actuelle
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE: " + this.role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}

@Embeddable
@Data
class ImagePath {
    private String publicId;
    private String url;
}