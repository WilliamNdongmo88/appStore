package will.dev.appStore.repository;

import will.dev.appStore.entites.ProductDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDescriptionRepository extends JpaRepository<ProductDescription, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}