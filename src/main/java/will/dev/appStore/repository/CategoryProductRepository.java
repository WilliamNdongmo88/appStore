package will.dev.appStore.repository;

import will.dev.appStore.entites.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}