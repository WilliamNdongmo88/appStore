package will.dev.appStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import will.dev.appStore.entites.SubCategory;
import will.dev.appStore.service.SubCategoryService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "subCategory", produces = APPLICATION_JSON_VALUE)
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAuthority('ADMIN_CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "creer")
    public ResponseEntity<?> creerSubCategory(@RequestBody SubCategory subCategory){
        return this.subCategoryService.creerSubCategory(subCategory);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_READ','MANAGER_READ','USER_READ')")
    @GetMapping(path = "all_subCategory", produces = APPLICATION_JSON_VALUE)
    public List<SubCategory> rechercher(){
        return this.subCategoryService.rechercher();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_READ','MANAGER_READ','USER_READ')")
    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public SubCategory lire(@PathVariable Long id){
        return this.subCategoryService.lire(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','MANAGER_UPDATE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable Long id,@RequestBody SubCategory category){
        this.subCategoryService.updateSubCategory(id, category);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(path = "{id}")
    public void supprimer(@PathVariable Long id,SubCategory subCategory){
        this.subCategoryService.deleteSubCategory(id, subCategory);
    }
}
