package will.dev.appStore.entites;

import jakarta.persistence.*;
import lombok.Data;
import will.dev.appStore.enums.TypeDeRole;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeDeRole libelle;
}