package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import will.dev.appStore.entites.ProductReview;
import will.dev.appStore.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    // Create
    public ProductReview createProductReview(ProductReview productReview) {
        return productReviewRepository.save(productReview);
    }

    // Read (Get All)
    public List<ProductReview> getAllProductReviews() {
        return productReviewRepository.findAll();
    }

    // Read (Get By Id)
    public ProductReview getProductReviewById(Long id) {
        Optional<ProductReview> optionalProductReview = this.productReviewRepository.findById(id);
        return optionalProductReview.orElseThrow(()-> new EntityNotFoundException("Aucun produit unit n'existe avec cette identifiant"));
    }

    // Update
    public ProductReview updateProductReview(Long id, ProductReview productReviewDetails) {
        ProductReview productReview = productReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductReview not found with id " + id));

        productReview.setAddedBy(productReviewDetails.getAddedBy());
        productReview.setProduct(productReviewDetails.getProduct());
        productReview.setProductUnit(productReviewDetails.getProductUnit());
        productReview.setTitle(productReviewDetails.getTitle());
        productReview.setContent(productReviewDetails.getContent());
        productReview.setRating(productReviewDetails.getRating());
        productReview.setDeletedAt(productReviewDetails.getDeletedAt());
        productReview.setDeletedBy(productReviewDetails.getDeletedBy());

        return productReviewRepository.save(productReview);
    }

    // Delete
    public void deleteProductReview(Long id) {
        ProductReview productReview = productReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductReview not found with id " + id));

        productReviewRepository.delete(productReview);
    }
}