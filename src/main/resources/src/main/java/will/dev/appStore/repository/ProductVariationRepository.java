package will.dev.appStore.repository;

import will.dev.appStore.entites.ProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariationRepository extends JpaRepository<ProductVariation, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}