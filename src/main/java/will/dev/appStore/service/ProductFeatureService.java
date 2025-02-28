package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import will.dev.appStore.entites.ProductFeature;
import will.dev.appStore.repository.ProductFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductFeatureService {

    @Autowired
    private ProductFeatureRepository productFeatureRepository;

    // Create
    public ProductFeature createProductFeature(ProductFeature productFeature) {
        return productFeatureRepository.save(productFeature);
    }

    // Read (Get All)
    public List<ProductFeature> getAllProductFeatures() {
        return productFeatureRepository.findAll();
    }

    // Read (Get By Id)
    public ProductFeature getProductFeatureById(Long id) {
        Optional<ProductFeature> optionalProductFeature = this.productFeatureRepository.findById(id);
        return optionalProductFeature.orElseThrow(()-> new EntityNotFoundException("Aucun produit unit n'existe avec cette identifiant"));
    }

    // Update
    public ProductFeature updateProductFeature(Long id, ProductFeature productFeatureDetails) {
        ProductFeature productFeature = productFeatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductFeature not found with id " + id));

        productFeature.setAddedBy(productFeatureDetails.getAddedBy());
        productFeature.setProduct(productFeatureDetails.getProduct());
        productFeature.setContent(productFeatureDetails.getContent());
        productFeature.setDeletedAt(productFeatureDetails.getDeletedAt());
        productFeature.setDeletedBy(productFeatureDetails.getDeletedBy());

        return productFeatureRepository.save(productFeature);
    }

    // Delete
    public void deleteProductFeature(Long id) {
        ProductFeature productFeature = productFeatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductFeature not found with id " + id));

        productFeatureRepository.delete(productFeature);
    }
}