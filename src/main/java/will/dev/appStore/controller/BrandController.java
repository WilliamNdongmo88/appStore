package will.dev.appStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import will.dev.appStore.entites.Brand;
import will.dev.appStore.service.BrandService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "brand", produces = APPLICATION_JSON_VALUE)
public class BrandController {
    private final BrandService brandService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "creer")
    public ResponseEntity<String> creerBrand(@RequestBody Brand brand){
        String message = this.brandService.creerBrand(brand);
        return ResponseEntity.ok(message);
    }

    @GetMapping(path = "all_brand", produces = APPLICATION_JSON_VALUE)
    public List<Brand> rechercher(){
        return this.brandService.rechercher();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Brand lire(@PathVariable Long id){
        return this.brandService.lire(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable Long id,@RequestBody Brand brand){
        this.brandService.modifier(id, brand);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(path = "{id}")
    public void supprimer(@PathVariable Long id){
        this.brandService.supprimer(id);
    }
}
