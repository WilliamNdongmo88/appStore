package will.dev.appStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import will.dev.appStore.entites.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByTitle(String title);
}
