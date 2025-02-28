package will.dev.appStore.repository;

import will.dev.appStore.entites.ProductFeature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFeatureRepository extends JpaRepository<ProductFeature, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}