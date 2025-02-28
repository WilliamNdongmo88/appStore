package will.dev.appStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import will.dev.appStore.entites.Category;
import will.dev.appStore.service.CategoryService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "category", produces = APPLICATION_JSON_VALUE)
public class CategoryController {
    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("creer")
    public ResponseEntity<String> creerCategory(@RequestBody Category category){
        String message = this.categoryService.creerCategory(category);
        return ResponseEntity.ok(message);
    }

    @GetMapping(path = "all_category", produces = APPLICATION_JSON_VALUE)
    public List<Category> rechercher(){
        return this.categoryService.rechercher();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Category lire(@PathVariable Long id){
        return this.categoryService.lire(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable Long id,@RequestBody Category category){
        this.categoryService.updateCategory(id, category);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(path = "{id}")
    public void supprimer(@PathVariable Long id){
        this.categoryService.deleteCategory(id);
    }
}
