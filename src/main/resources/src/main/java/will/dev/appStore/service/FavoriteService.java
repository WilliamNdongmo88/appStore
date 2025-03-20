package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import will.dev.appStore.entites.Favorite;
import will.dev.appStore.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    // Create
    public Favorite createFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    // Read (Get All)
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    // Read (Get By Id)
    public Favorite getFavoriteById(Long id) {
        Optional<Favorite> optionalFavorite = this.favoriteRepository.findById(id);
        return optionalFavorite.orElseThrow(()-> new EntityNotFoundException(("Aucun favorite n'existe avec cette identifiant")));
    }

    // Update
    public Favorite updateFavorite(Long id, Favorite favoriteDetails) {
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorite not found with id " + id));

        favorite.setAddedBy(favoriteDetails.getAddedBy());
        favorite.setProduct(favoriteDetails.getProduct());
        favorite.setDeletedAt(favoriteDetails.getDeletedAt());
        favorite.setDeletedBy(favoriteDetails.getDeletedBy());

        return favoriteRepository.save(favorite);
    }

    // Delete
    public void deleteFavorite(Long id) {
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorite not found with id " + id));

        favoriteRepository.delete(favorite);
    }
}