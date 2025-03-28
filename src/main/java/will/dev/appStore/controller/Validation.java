package will.dev.appStore.controller;

import jakarta.persistence.*;
import lombok.Data;
import will.dev.appStore.entites.User;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "validation")
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant creation;
    private Instant expiration;
    private Instant activation;
    private String code;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)// +
    private User user;

    // Nouveau champ pour stocker uniquement le jour
    private LocalDate validationDay;
}
