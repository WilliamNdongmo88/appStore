package will.dev.appStore.repository;

import will.dev.appStore.entites.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}