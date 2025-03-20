package will.dev.appStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import will.dev.appStore.entites.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount findByTitle(String title);
}
