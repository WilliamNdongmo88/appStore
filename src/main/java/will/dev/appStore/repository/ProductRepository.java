package will.dev.appStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import will.dev.appStore.entites.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitle(String title);
    List<Product> findBySubCategoryTitle(String subCategoryName);
    List<Product> findByTitleStartingWith(String prefix);
}
