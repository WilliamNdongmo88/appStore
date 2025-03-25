package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;
import will.dev.appStore.entites.Category;
import will.dev.appStore.entites.User;
import will.dev.appStore.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    //Créer category
    public ResponseEntity<?> creerCategory(Category category){
        User userConnected = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        category.setAddedBy(userConnected);
        Category categoryDansBD = this.categoryRepository.findByTitle(category.getTitle());
        if (categoryDansBD ==  null ){
            this.categoryRepository.save(category);
            return ResponseEntity.ok("Catégorie '" + category.getTitle() + "' créée avec succès.");
        }else {
            throw new RuntimeException("La catégorie '" + category.getTitle() + "' existe déjà.") ;
        }
    }

    public List<Category> rechercher(){
        return  this.categoryRepository.findAll();
    }

    public Category lire(Long id) {
        Optional<Category> optionalCategory =  this.categoryRepository.findById(id);
        return optionalCategory.orElseThrow(() -> new EntityNotFoundException("Aucune categorie n'existe avec cette id"));
    }

    // Update
    public Category updateCategory(Long id, Category categoryDetails) {
        User userConnected = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        categoryDetails.setUpdateBy(userConnected);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));

        category.setUpdateBy(categoryDetails.getUpdateBy());
        category.setTitle(categoryDetails.getTitle());
        category.setSlug(categoryDetails.getSlug());
        category.setDescription(categoryDetails.getDescription());
        category.setDeletedAt(categoryDetails.getDeletedAt());
        category.setDeletedBy(categoryDetails.getDeletedBy());

        return categoryRepository.save(category);
    }

    // Delete
    public void deleteCategory(Long id, Category categoryDetails) {
        User userConnected = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        categoryDetails.setDeletedBy(userConnected);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));

        categoryRepository.delete(category);
    }
}
