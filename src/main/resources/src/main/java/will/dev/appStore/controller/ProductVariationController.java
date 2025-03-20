package will.dev.appStore.controller;

import will.dev.appStore.entites.ProductVariation;
import will.dev.appStore.service.ProductVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product-variation")
public class ProductVariationController {

    @Autowired
    private ProductVariationService productVariationService;

    // Create
    @PostMapping("creer")
    public ProductVariation createProductVariation(@RequestBody ProductVariation productVariation) {
        return productVariationService.createProductVariation(productVariation);
    }

    // Read (Get All)
    @GetMapping("all_prod-variation")
    public List<ProductVariation> getAllProductVariations() {
        return productVariationService.getAllProductVariations();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public ProductVariation getProductVariationById(@PathVariable Long id) {
        return productVariationService.getProductVariationById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProductVariation> updateProductVariation(@PathVariable Long id, @RequestBody ProductVariation productVariationDetails) {
        return ResponseEntity.ok(productVariationService.updateProductVariation(id, productVariationDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductVariation(@PathVariable Long id) {
        productVariationService.deleteProductVariation(id);
        return ResponseEntity.noContent().build();
    }
}