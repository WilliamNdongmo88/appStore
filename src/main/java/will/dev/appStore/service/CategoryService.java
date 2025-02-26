package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import will.dev.appStore.entites.Category;
import will.dev.appStore.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public String creerCategory(Category category){
        Category categoryDansBD = this.categoryRepository.findByTitle(category.getTitle());
        if (categoryDansBD ==  null ){
            this.categoryRepository.save(category);
            return "Catégorie '" + category.getTitle() + "' créée avec succès.";
        }else {
            return "La catégorie '" + category.getTitle() + "' existe déjà.";
        }
    }

    public List<Category> rechercher(){
        return  this.categoryRepository.findAll();
    }

    public Category lire(Long id) {
        Optional<Category> optionalCategory =  this.categoryRepository.findById(id);
        return optionalCategory.orElseThrow(() -> new EntityNotFoundException("Aucune categorie n'existe avec cette id"));
    }

    /*public Category lireOuCreer(Category categoryAcreer) {
        Category categoryDansLaBD = this.categoryRepository.findByTitle(categoryAcreer.getTitle());
        if(categoryDansLaBD == null){
            categoryDansLaBD = this.categoryRepository.save(categoryAcreer);
        }
        return categoryDansLaBD;S
    }*/

    public void modifier(Long id, Category category) {
        Category categoryDansLaBD = this.lire(id);//Recupération de la category dns la bd
        if (categoryDansLaBD.getId() == category.getId() && categoryDansLaBD.getId() == id){
            //Ajout des informations recu
            categoryDansLaBD.setTitle(category.getTitle());
            categoryDansLaBD.setAddedBy(category.getAddedBy());
            this.categoryRepository.save(categoryDansLaBD);
        }
    }

    public void supprimer(Long id) {
        this.categoryRepository.deleteById(id);
    }
}
