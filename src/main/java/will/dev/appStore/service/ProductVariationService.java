package will.dev.appStore.service;

import lombok.RequiredArgsConstructor;
import will.dev.appStore.entites.ProductVariation;
import will.dev.appStore.repository.ProductVariationRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductVariationService {

    private final ProductVariationRepository productVariationRepository;

    // Create
    public ProductVariation createProductVariation(ProductVariation productVariation) {
        return productVariationRepository.save(productVariation);
    }

    // Read (Get All)
    public List<ProductVariation> getAllProductVariations() {
        return productVariationRepository.findAll();
    }

    // Read (Get By Id)
    public ProductVariation getProductVariationById(Long id) {
        Optional<ProductVariation> optionalProductVariation =  this.productVariationRepository.findById(id);
        return optionalProductVariation.orElseThrow(() -> new EntityNotFoundException("Aucun produit unit n'existe avec cette identifiant"));
    }

    // Update
    public ProductVariation updateProductVariation(Long id, ProductVariation productVariationDetails) {
        ProductVariation productVariation = productVariationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductVariation not found with id " + id));

        productVariation.setAddedBy(productVariationDetails.getAddedBy());
        productVariation.setProduct(productVariationDetails.getProduct());
        productVariation.setTitle(productVariationDetails.getTitle());
        productVariation.setSlug(productVariationDetails.getSlug());
        productVariation.setDescription(productVariationDetails.getDescription());
        productVariation.setRetailPrice(productVariationDetails.getRetailPrice());
        productVariation.setInitialRetailPrice(productVariationDetails.getInitialRetailPrice());
        productVariation.setDeletedAt(productVariationDetails.getDeletedAt());
        productVariation.setDeletedBy(productVariationDetails.getDeletedBy());

        return productVariationRepository.save(productVariation);
    }

    // Delete
    public void deleteProductVariation(Long id) {
        ProductVariation productVariation = productVariationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductVariation not found with id " + id));

        productVariationRepository.delete(productVariation);
    }
}