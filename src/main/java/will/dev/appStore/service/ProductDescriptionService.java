package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import will.dev.appStore.entites.ProductDescription;
import will.dev.appStore.repository.ProductDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDescriptionService {

    @Autowired
    private ProductDescriptionRepository productDescriptionRepository;

    // Create
    public ProductDescription createProductDescription(ProductDescription productDescription) {
        return productDescriptionRepository.save(productDescription);
    }

    // Read (Get All)
    public List<ProductDescription> getAllProductDescriptions() {
        return productDescriptionRepository.findAll();
    }

    // Read (Get By Id)
    public ProductDescription getProductDescriptionById(Long id) {
        Optional<ProductDescription> optionalProductDescription = this.productDescriptionRepository.findById(id);
        return optionalProductDescription.orElseThrow(()-> new EntityNotFoundException("Aucun produit unit n'existe avec cette identifiant"));
    }

    // Update
    public ProductDescription updateProductDescription(Long id, ProductDescription productDescriptionDetails) {
        ProductDescription productDescription = productDescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductDescription not found with id " + id));

        productDescription.setAddedBy(productDescriptionDetails.getAddedBy());
        productDescription.setProduct(productDescriptionDetails.getProduct());
        productDescription.setContent(productDescriptionDetails.getContent());
        productDescription.setDeletedAt(productDescriptionDetails.getDeletedAt());
        productDescription.setDeletedBy(productDescriptionDetails.getDeletedBy());

        return productDescriptionRepository.save(productDescription);
    }

    // Delete
    public void deleteProductDescription(Long id) {
        ProductDescription productDescription = productDescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductDescription not found with id " + id));

        productDescriptionRepository.delete(productDescription);
    }
}