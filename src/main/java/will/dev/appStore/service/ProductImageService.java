package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import will.dev.appStore.entites.ProductImage;
import will.dev.appStore.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    // Create
    public ProductImage createProductImage(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }

    // Read (Get All)
    public List<ProductImage> getAllProductImages() {
        return productImageRepository.findAll();
    }

    // Read (Get By Id)
    public ProductImage getProductImageById(Long id) {
        Optional<ProductImage> optionalProductImage = this.productImageRepository.findById(id);
        return optionalProductImage.orElseThrow(()-> new EntityNotFoundException("Aucune image n'existe avec cette identifiant"));
    }

    // Update
    public ProductImage updateProductImage(Long id, ProductImage productImageDetails) {
        ProductImage productImage = productImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductImage not found with id " + id));

        productImage.setAddedBy(productImageDetails.getAddedBy());
        productImage.setProduct(productImageDetails.getProduct());
        productImage.setImagePath(productImageDetails.getImagePath());
        productImage.setProductDefault(productImageDetails.isProductDefault());
        productImage.setDeletedAt(productImageDetails.getDeletedAt());
        productImage.setDeletedBy(productImageDetails.getDeletedBy());

        return productImageRepository.save(productImage);
    }

    // Delete
    public void deleteProductImage(Long id) {
        ProductImage productImage = productImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductImage not found with id " + id));

        productImageRepository.delete(productImage);
    }
}