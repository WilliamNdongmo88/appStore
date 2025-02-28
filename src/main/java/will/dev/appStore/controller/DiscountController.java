package will.dev.appStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import will.dev.appStore.entites.Discount;
import will.dev.appStore.service.DiscountService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "discount", produces = APPLICATION_JSON_VALUE)
public class DiscountController {
    private final DiscountService discountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "creer")
    public ResponseEntity<String> creerDiscount(@RequestBody Discount discount){
        String message = this.discountService.creerDiscount(discount);
        return ResponseEntity.ok(message);
    }

    @GetMapping(path = "all_discount", produces = APPLICATION_JSON_VALUE)
    public List<Discount> rechercher(){
        return this.discountService.rechercher();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Discount lire(@PathVariable Long id){
        return this.discountService.lire(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable Long id,@RequestBody Discount discount){
        this.discountService.updateDiscount(id, discount);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(path = "{id}")
    public void supprimer(@PathVariable Long id){
        this.discountService.deleteDiscount(id);
    }
}
