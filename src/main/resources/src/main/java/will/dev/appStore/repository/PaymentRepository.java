package will.dev.appStore.repository;

import will.dev.appStore.entites.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}