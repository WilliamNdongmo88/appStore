package will.dev.appStore.controller;

import will.dev.appStore.entites.ProductDescription;
import will.dev.appStore.service.ProductDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product-descriptions")
public class ProductDescriptionController {

    @Autowired
    private ProductDescriptionService productDescriptionService;

    // Create
    @PostMapping("creer")
    public ProductDescription createProductDescription(@RequestBody ProductDescription productDescription) {
        return productDescriptionService.createProductDescription(productDescription);
    }

    // Read (Get All)
    @GetMapping("all_prod-description")
    public List<ProductDescription> getAllProductDescriptions() {
        return productDescriptionService.getAllProductDescriptions();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public ProductDescription getProductDescriptionById(@PathVariable Long id) {
        return productDescriptionService.getProductDescriptionById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProductDescription> updateProductDescription(@PathVariable Long id, @RequestBody ProductDescription productDescriptionDetails) {
        return ResponseEntity.ok(productDescriptionService.updateProductDescription(id, productDescriptionDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductDescription(@PathVariable Long id) {
        productDescriptionService.deleteProductDescription(id);
        return ResponseEntity.noContent().build();
    }
}