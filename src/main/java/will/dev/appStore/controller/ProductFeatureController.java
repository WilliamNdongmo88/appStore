package will.dev.appStore.controller;

import will.dev.appStore.entites.ProductFeature;
import will.dev.appStore.service.ProductFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product-features")
public class ProductFeatureController {

    @Autowired
    private ProductFeatureService productFeatureService;

    // Create
    @PostMapping("creer")
    public ProductFeature createProductFeature(@RequestBody ProductFeature productFeature) {
        return productFeatureService.createProductFeature(productFeature);
    }

    // Read (Get All)
    @GetMapping("all_prod-features")
    public List<ProductFeature> getAllProductFeatures() {
        return productFeatureService.getAllProductFeatures();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public ProductFeature getProductFeatureById(@PathVariable Long id) {
        return productFeatureService.getProductFeatureById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProductFeature> updateProductFeature(@PathVariable Long id, @RequestBody ProductFeature productFeatureDetails) {
        return ResponseEntity.ok(productFeatureService.updateProductFeature(id, productFeatureDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductFeature(@PathVariable Long id) {
        productFeatureService.deleteProductFeature(id);
        return ResponseEntity.noContent().build();
    }
}