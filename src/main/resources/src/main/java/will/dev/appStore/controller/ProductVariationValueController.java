package will.dev.appStore.controller;

import will.dev.appStore.entites.ProductVariationValue;
import will.dev.appStore.service.ProductVariationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-variation-value")
public class ProductVariationValueController {

    @Autowired
    private ProductVariationValueService productVariationValueService;

    // Create
    @PostMapping("creer")
    public ProductVariationValue createProductVariationValue(@RequestBody ProductVariationValue productVariationValue) {
        return productVariationValueService.createProductVariationValue(productVariationValue);
    }

    // Read (Get All)
    @GetMapping("all_prod-var-value")
    public List<ProductVariationValue> getAllProductVariationValues() {
        return productVariationValueService.getAllProductVariationValues();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public ProductVariationValue getProductVariationValueById(@PathVariable Long id) {
        return productVariationValueService.getProductVariationValueById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProductVariationValue> updateProductVariationValue(@PathVariable Long id, @RequestBody ProductVariationValue productVariationValueDetails) {
        return ResponseEntity.ok(productVariationValueService.updateProductVariationValue(id, productVariationValueDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductVariationValue(@PathVariable Long id) {
        productVariationValueService.deleteProductVariationValue(id);
        return ResponseEntity.noContent().build();
    }
}