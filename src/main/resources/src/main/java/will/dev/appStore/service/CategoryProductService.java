package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import will.dev.appStore.entites.CategoryProduct;
import will.dev.appStore.entites.CategoryProduct;
import will.dev.appStore.repository.CategoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryProductService {

    private final CategoryProductRepository categoryProductRepository;
    //private final

    // Create
    public CategoryProduct createCategoryProduct(CategoryProduct categoryProduct) {
        return categoryProductRepository.save(categoryProduct);
    }

    // Read (Get All)
    public List<CategoryProduct> getAllCategoryProducts() {
        return categoryProductRepository.findAll();
    }

    // Read (Get By Id)
    public CategoryProduct getCategoryProductById(Long id) {
        //return categoryProductRepository.findById(id);
        Optional<CategoryProduct> optionalCategoryProduct =  this.categoryProductRepository.findById(id);
        return optionalCategoryProduct.orElseThrow(() -> new EntityNotFoundException("Aucune categorie de produit n'existe avec cette identifiant"));
    }

    // Update
    public CategoryProduct updateCategoryProduct(Long id, CategoryProduct categoryProductDetails) {
        CategoryProduct categoryProduct = categoryProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CategoryProduct not found with id " + id));

        categoryProduct.setAddedBy(categoryProductDetails.getAddedBy());
        categoryProduct.setCategory(categoryProductDetails.getCategory());
        categoryProduct.setProduct(categoryProductDetails.getProduct());
        categoryProduct.setDeletedAt(categoryProductDetails.getDeletedAt());
        categoryProduct.setDeletedBy(categoryProductDetails.getDeletedBy());

        return categoryProductRepository.save(categoryProduct);
    }

    // Delete
    public void deleteCategoryProduct(Long id) {
        CategoryProduct categoryProduct = categoryProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CategoryProduct not found with id " + id));

        categoryProductRepository.delete(categoryProduct);
    }
}