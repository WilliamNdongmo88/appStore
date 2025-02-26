package will.dev.appStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import will.dev.appStore.entites.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findByTitle(String title);
}
