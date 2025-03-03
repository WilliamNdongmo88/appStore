package will.dev.appStore.repository;

import will.dev.appStore.entites.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import will.dev.appStore.entites.SubCategory;

import java.util.List;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {
    //List<CategoryProduct> findByCategoryProduct_Title(String categoryProductTitle);
}