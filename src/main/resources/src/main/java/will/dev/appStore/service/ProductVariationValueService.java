package will.dev.appStore.service;

import will.dev.appStore.entites.ProductVariationValue;
import will.dev.appStore.repository.ProductVariationValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductVariationValueService {

    @Autowired
    private ProductVariationValueRepository productVariationValueRepository;

    // Create
    public ProductVariationValue createProductVariationValue(ProductVariationValue productVariationValue) {
        return productVariationValueRepository.save(productVariationValue);
    }

    // Read (Get All)
    public List<ProductVariationValue> getAllProductVariationValues() {
        return productVariationValueRepository.findAll();
    }

    // Read (Get By Id)
    public ProductVariationValue getProductVariationValueById(Long id) {
        Optional<ProductVariationValue> optionalProductUnit =  this.productVariationValueRepository.findById(id);
        return optionalProductUnit.orElseThrow(() -> new EntityNotFoundException("Aucun produit unit n'existe avec cette identifiant"));
    }

    // Update
    public ProductVariationValue updateProductVariationValue(Long id, ProductVariationValue productVariationValueDetails) {
        ProductVariationValue productVariationValue = productVariationValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductVariationValue not found with id " + id));

        productVariationValue.setAddedBy(productVariationValueDetails.getAddedBy());
        productVariationValue.setProduct(productVariationValueDetails.getProduct());
        productVariationValue.setProductUnit(productVariationValueDetails.getProductUnit());
        productVariationValue.setProductVariation(productVariationValueDetails.getProductVariation());
        productVariationValue.setValue(productVariationValueDetails.getValue());
        productVariationValue.setSlug(productVariationValueDetails.getSlug());
        productVariationValue.setDeletedAt(productVariationValueDetails.getDeletedAt());
        productVariationValue.setDeletedBy(productVariationValueDetails.getDeletedBy());

        return productVariationValueRepository.save(productVariationValue);
    }

    // Delete
    public void deleteProductVariationValue(Long id) {
        ProductVariationValue productVariationValue = productVariationValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductVariationValue not found with id " + id));

        productVariationValueRepository.delete(productVariationValue);
    }
}