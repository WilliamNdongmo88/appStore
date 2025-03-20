package will.dev.appStore.controller;

import will.dev.appStore.entites.ProductImage;
import will.dev.appStore.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product-images")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    // Create
    @PostMapping("creer")
    public ProductImage createProductImage(@RequestBody ProductImage productImage) {
        return productImageService.createProductImage(productImage);
    }

    // Read (Get All)
    @GetMapping("all_prod-images")
    public List<ProductImage> getAllProductImages() {
        return productImageService.getAllProductImages();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public ProductImage getProductImageById(@PathVariable Long id) {
        return productImageService.getProductImageById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProductImage> updateProductImage(@PathVariable Long id, @RequestBody ProductImage productImageDetails) {
        return ResponseEntity.ok(productImageService.updateProductImage(id, productImageDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductImage(@PathVariable Long id) {
        productImageService.deleteProductImage(id);
        return ResponseEntity.noContent().build();
    }
}