package will.dev.appStore.controller;

import will.dev.appStore.entites.Category;
import will.dev.appStore.entites.CategoryProduct;
import will.dev.appStore.service.CategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category-products")
public class CategoryProductController {

    @Autowired
    private CategoryProductService categoryProductService;

    // Create
    @PostMapping("creer")
    public CategoryProduct createCategoryProduct(@RequestBody CategoryProduct categoryProduct) {
        return categoryProductService.createCategoryProduct(categoryProduct);
    }

    // Read (Get All)
    @GetMapping("all_cat-prod")
    public List<CategoryProduct> getAllCategoryProducts() {
        return categoryProductService.getAllCategoryProducts();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public CategoryProduct getCategoryProductById(@PathVariable Long id) {
        return categoryProductService.getCategoryProductById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<CategoryProduct> updateCategoryProduct(@PathVariable Long id, @RequestBody CategoryProduct categoryProductDetails) {
        return ResponseEntity.ok(categoryProductService.updateCategoryProduct(id, categoryProductDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryProduct(@PathVariable Long id) {
        categoryProductService.deleteCategoryProduct(id);
        return ResponseEntity.noContent().build();
    }
}