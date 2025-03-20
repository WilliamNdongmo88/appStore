package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import will.dev.appStore.dto.ProductDTO;
import will.dev.appStore.entites.Product;
import will.dev.appStore.mapper.ProductDtoMapper;
import will.dev.appStore.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDtoMapper productDtoMapper;

    public String creerProduct(Product product){
        Product productDansBD = this.productRepository.findByTitle(product.getTitle());
        if (productDansBD ==  null ){
            this.productRepository.save(product);
            return "Le produit '" + product.getTitle() + "' créée avec succès.";
        }else {
            return "Le produit '" + product.getTitle() + "' existe déjà.";
        }
    }

    public Stream<ProductDTO> rechercher(){
        return  this.productRepository.findAll().stream().map(productDtoMapper);
    }

    public ProductDTO lire(Long id) {
        return productRepository.findById(id).map(productDtoMapper).orElseThrow(() -> new EntityNotFoundException("Aucun produit n'existe avec cette identifiant"));
    }

    public List<Product> getProductsBySubCategory(String title) {
        List<Product> productDansBD = productRepository.findBySubCategoryTitle(title);
        System.out.println("subCategoryDansBD : " + productDansBD);
        if (productDansBD.isEmpty()){
            throw new EntityNotFoundException("Aucun produit n'existe avec ce nom : " + title);
        }
        return productDansBD;
    }

    public List<Product> getProductTitlesStartingWith(String prefix) {
        List<Product> productTitles = productRepository.findByTitleStartingWith(prefix);
        if (productTitles == null) {
            throw new EntityNotFoundException("Aucune produit n'existe avec un nom commençant par : " + prefix);
        }
        return productTitles;
    }

    // Update
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        product.setAddedBy(productDetails.getAddedBy());
        product.setBrand(productDetails.getBrand());
        product.setDiscount(productDetails.getDiscount());
        product.setSubCategory(productDetails.getSubCategory());
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
