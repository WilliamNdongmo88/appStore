package will.dev.appStore.controller;

import will.dev.appStore.entites.Favorite;
import will.dev.appStore.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    // Create
    @PostMapping("creer")
    public Favorite createFavorite(@RequestBody Favorite favorite) {
        return favoriteService.createFavorite(favorite);
    }

    // Read (Get All)
    @GetMapping("all_favorites")
    public List<Favorite> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public Favorite getFavoriteById(@PathVariable Long id) {
        return favoriteService.getFavoriteById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Favorite> updateFavorite(@PathVariable Long id, @RequestBody Favorite favoriteDetails) {
        return ResponseEntity.ok(favoriteService.updateFavorite(id, favoriteDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        favoriteService.deleteFavorite(id);
        return ResponseEntity.noContent().build();
    }
}