package will.dev.appStore.controller;

import will.dev.appStore.entites.ProductReview;
import will.dev.appStore.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product-reviews")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    // Create
    @PostMapping("creer")
    public ProductReview createProductReview(@RequestBody ProductReview productReview) {
        return productReviewService.createProductReview(productReview);
    }

    // Read (Get All)
    @GetMapping("all_prod-reviews")
    public List<ProductReview> getAllProductReviews() {
        return productReviewService.getAllProductReviews();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public ProductReview getProductReviewById(@PathVariable Long id) {
        return productReviewService.getProductReviewById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProductReview> updateProductReview(@PathVariable Long id, @RequestBody ProductReview productReviewDetails) {
        return ResponseEntity.ok(productReviewService.updateProductReview(id, productReviewDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductReview(@PathVariable Long id) {
        productReviewService.deleteProductReview(id);
        return ResponseEntity.noContent().build();
    }
}