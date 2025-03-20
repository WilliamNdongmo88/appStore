package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import will.dev.appStore.entites.CategoryProduct;
import will.dev.appStore.entites.Product;
import will.dev.appStore.entites.SubCategory;
import will.dev.appStore.repository.CategoryRepository;
import will.dev.appStore.repository.ProductRepository;
import will.dev.appStore.repository.SubCategoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public String creerSubCategory(SubCategory subCategory){
        SubCategory categoryDansBD = this.subCategoryRepository.findByTitle(subCategory.getTitle());
        if (categoryDansBD ==  null ){
            this.subCategoryRepository.save(subCategory);
            return "La sous-Catégorie '" + subCategory.getTitle() + "' créée avec succès.";
        }else {
            return "La sous-Catégorie '" + subCategory.getTitle() + "' existe déjà.";
        }
    }

    public List<SubCategory> rechercher(){
        return  this.subCategoryRepository.findAll();
    }

    public SubCategory lire(Long id) {
        Optional<SubCategory> optionalSubCategory =  this.subCategoryRepository.findById(id);
        return optionalSubCategory.orElseThrow(() -> new EntityNotFoundException("Aucune sous-categorie n'existe avec cette id"));
    }

    public List<SubCategory> getSubCategoryByCategory(String title) {
        List<SubCategory> subCategoryDansBD = subCategoryRepository.findByCategoryTitle(title);
        System.out.println("subCategoryDansBD Title"+ subCategoryDansBD.get(0).getTitle());
        if (subCategoryDansBD == null) {
            throw new EntityNotFoundException("Aucune sous-catégorie n'existe avec ce nom : " + title);
        }
        return subCategoryDansBD;
    }

    // Update
    public SubCategory updateSubCategory(Long id, SubCategory subCategoryDetails) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategory not found with id " + id));

        subCategory.setAddedBy(subCategoryDetails.getAddedBy());
        subCategory.setCategory(subCategoryDetails.getCategory());
        subCategory.setTitle(subCategoryDetails.getTitle());
        subCategory.setSlug(subCategoryDetails.getSlug());
        subCategory.setDescription(subCategoryDetails.getDescription());
        subCategory.setDeletedAt(subCategoryDetails.getDeletedAt());
        subCategory.setDeletedBy(subCategoryDetails.getDeletedBy());

        return subCategoryRepository.save(subCategory);
    }

    // Delete
    public void deleteSubCategory(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategory not found with id " + id));

        subCategoryRepository.delete(subCategory);
    }
}
