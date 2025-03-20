package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import will.dev.appStore.entites.ProductUnit;
import will.dev.appStore.repository.ProductUnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductUnitService {

    private final ProductUnitRepository productUnitRepository;

    // Create
    public ProductUnit createProductUnit(ProductUnit productUnit) {
        return productUnitRepository.save(productUnit);
    }

    // Read (Get All)
    public List<ProductUnit> getAllProductUnits() {
        return productUnitRepository.findAll();
    }

    // Read (Get By Id)
    public ProductUnit getProductUnitById(Long id) {
        Optional<ProductUnit> optionalProductUnit =  this.productUnitRepository.findById(id);
        return optionalProductUnit.orElseThrow(() -> new EntityNotFoundException("Aucun produit unit n'existe avec cette identifiant"));
    }

    // Update
    public ProductUnit updateProductUnit(Long id, ProductUnit productUnitDetails) {
        ProductUnit productUnit = productUnitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductUnit not found with id " + id));

        productUnit.setAddedBy(productUnitDetails.getAddedBy());
        productUnit.setBoughtBy(productUnitDetails.getBoughtBy());
        productUnit.setDiscount(productUnitDetails.getDiscount());
        productUnit.setProduct(productUnitDetails.getProduct());
        productUnit.setSku(productUnitDetails.getSku());
        productUnit.setTitle(productUnitDetails.getTitle());
        productUnit.setSlug(productUnitDetails.getSlug());
        productUnit.setDescription(productUnitDetails.getDescription());
        productUnit.setPurchasePrice(productUnitDetails.getPurchasePrice());
        productUnit.setRetailPrice(productUnitDetails.getRetailPrice());
        productUnit.setInitialRetailPrice(productUnitDetails.getInitialRetailPrice());
        productUnit.setSold(productUnitDetails.isSold());
        productUnit.setProductDefault(productUnitDetails.isProductDefault());
        productUnit.setDeletedAt(productUnitDetails.getDeletedAt());
        productUnit.setDeletedBy(productUnitDetails.getDeletedBy());

        return productUnitRepository.save(productUnit);
    }

    // Delete
    public void deleteProductUnit(Long id) {
        ProductUnit productUnit = productUnitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductUnit not found with id " + id));

        productUnitRepository.delete(productUnit);
    }
}