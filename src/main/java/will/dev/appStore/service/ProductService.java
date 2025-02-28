package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import will.dev.appStore.entites.Product;
import will.dev.appStore.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public String creerProduct(Product product){
        Product productDansBD = this.productRepository.findByTitle(product.getTitle());
        if (productDansBD ==  null ){
            this.productRepository.save(product);
            return "Le produit '" + product.getTitle() + "' créée avec succès.";
        }else {
            return "Le produit '" + product.getTitle() + "' existe déjà.";
        }
    }

    public List<Product> rechercher(){
        return  this.productRepository.findAll();
    }

    public Product lire(Long id) {
        Optional<Product> optionalSubCategory =  this.productRepository.findById(id);
        return optionalSubCategory.orElseThrow(() -> new EntityNotFoundException("Aucun produit n'existe avec cette identifiant"));
    }

    // Update
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        product.setAddedBy(productDetails.getAddedBy());
        product.setBrand(productDetails.getBrand());
        product.setDiscount(productDetails.getDiscount());
        product.setTitle(productDetails.getTitle());
        product.setSlug(productDetails.getSlug());
        product.setDescription(productDetails.getDescription());
        product.setFeatures(productDetails.getFeatures());
        product.setRetailPrice(productDetails.getRetailPrice());
        product.setInitialRetailPrice(productDetails.getInitialRetailPrice());
        product.setCurrency(productDetails.getCurrency());
        product.setDeletedAt(productDetails.getDeletedAt());
        product.setDeletedBy(productDetails.getDeletedBy());

        return productRepository.save(product);
    }

    // Delete
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        productRepository.delete(product);
    }
}
