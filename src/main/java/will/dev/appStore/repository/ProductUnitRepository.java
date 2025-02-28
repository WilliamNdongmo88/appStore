package will.dev.appStore.repository;

import will.dev.appStore.entites.ProductUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductUnitRepository extends JpaRepository<ProductUnit, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}