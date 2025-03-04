package will.dev.appStore.controller.rechercheCategoryProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import will.dev.appStore.entites.Product;
import will.dev.appStore.entites.SubCategory;
import will.dev.appStore.service.ProductService;
import will.dev.appStore.service.SubCategoryService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/recherche")
public class SearchCategoryProduct {
    private final SubCategoryService subCategoryService;
    private final ProductService productService;

    @GetMapping("/categories/{categoryTitle}/subcategories")
    public List<SubCategory> getAllSubCategory(@PathVariable String categoryTitle){
        return this.subCategoryService.getSubCategoryByCategory(categoryTitle);
    }

    @GetMapping("/subcategories/{subCategoryTitle}/products")
    public List<Product> getAllProduct(@PathVariable String subCategoryTitle){
        return this.productService.getProductsBySubCategory(subCategoryTitle);
    }

    //@GetMapping("/prefix/subcategories/{subCategoryPrefix}/products")
    @GetMapping("/search")
    public List<Product> searchProductsByPrefix(@RequestParam String prefix) {
        return this.productService.getProductTitlesStartingWith(prefix);
    }
}
