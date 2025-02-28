package will.dev.appStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import will.dev.appStore.entites.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitle(String title);
}
