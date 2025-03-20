package will.dev.appStore.repository;

import will.dev.appStore.entites.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}