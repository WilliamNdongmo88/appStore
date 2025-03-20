package will.dev.appStore.controller;

import will.dev.appStore.entites.ProductUnit;
import will.dev.appStore.service.ProductUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product-unit")
public class ProductUnitController {

    @Autowired
    private ProductUnitService productUnitService;

    // Create
    @PostMapping("creer")
    public ProductUnit createProductUnit(@RequestBody ProductUnit productUnit) {
        return productUnitService.createProductUnit(productUnit);
    }

    // Read (Get All)
    @GetMapping("all_prod-unit")
    public List<ProductUnit> getAllProductUnits() {
        return productUnitService.getAllProductUnits();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public ProductUnit getProductUnitById(@PathVariable Long id) {
        return productUnitService.getProductUnitById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProductUnit> updateProductUnit(@PathVariable Long id, @RequestBody ProductUnit productUnitDetails) {
        return ResponseEntity.ok(productUnitService.updateProductUnit(id, productUnitDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductUnit(@PathVariable Long id) {
        productUnitService.deleteProductUnit(id);
        return ResponseEntity.noContent().build();
    }
}