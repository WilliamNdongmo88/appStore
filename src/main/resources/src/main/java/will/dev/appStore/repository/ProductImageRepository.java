package will.dev.appStore.repository;

import will.dev.appStore.entites.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}