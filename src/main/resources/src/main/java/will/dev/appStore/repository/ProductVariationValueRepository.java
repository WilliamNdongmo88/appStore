package will.dev.appStore.repository;

import will.dev.appStore.entites.ProductVariationValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariationValueRepository extends JpaRepository<ProductVariationValue, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}