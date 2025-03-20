package will.dev.appStore.repository;

import will.dev.appStore.entites.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}