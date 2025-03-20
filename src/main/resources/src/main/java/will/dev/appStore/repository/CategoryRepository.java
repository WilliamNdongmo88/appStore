package will.dev.appStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import will.dev.appStore.entites.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);
}
