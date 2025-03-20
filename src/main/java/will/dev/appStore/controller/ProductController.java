package will.dev.appStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import will.dev.appStore.dto.ProductDTO;
import will.dev.appStore.entites.Product;
import will.dev.appStore.service.ProductService;

import java.util.List;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "product", produces = APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "creer")
    public ResponseEntity<String> creerProduct(@RequestBody Product product){
        String message = this.productService.creerProduct(product);
        return ResponseEntity.ok(message);
    }

    @GetMapping(path = "all_product", produces = APPLICATION_JSON_VALUE)
    public Stream<ProductDTO> rechercher(){
        return this.productService.rechercher();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public ProductDTO lire(@PathVariable Long id){
        return this.productService.lire(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable Long id,@RequestBody Product product){
        this.productService.updateProduct(id, product);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(path = "{id}")
    public void supprimer(@PathVariable Long id){
        this.productService.deleteProduct(id);
    }
}
