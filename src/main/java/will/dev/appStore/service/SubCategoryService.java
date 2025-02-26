package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import will.dev.appStore.entites.SubCategory;
import will.dev.appStore.repository.SubCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;

    public String creerSubCategory(SubCategory subCategory){
        SubCategory categoryDansBD = this.subCategoryRepository.findByTitle(subCategory.getTitle());
        if (categoryDansBD ==  null ){
            this.subCategoryRepository.save(subCategory);
            return "La sous-Catégorie '" + subCategory.getTitle() + "' créée avec succès.";
        }else {
            return "La sous-Catégorie '" + subCategory.getTitle() + "' existe déjà.";
        }
    }

    public List<SubCategory> rechercher(){
        return  this.subCategoryRepository.findAll();
    }

    public SubCategory lire(Long id) {
        Optional<SubCategory> optionalSubCategory =  this.subCategoryRepository.findById(id);
        return optionalSubCategory.orElseThrow(() -> new EntityNotFoundException("Aucune sous-categorie n'existe avec cette id"));
    }

    public void modifier(Long id, SubCategory subCategory) {
        SubCategory subCategoryDansLaBD = this.lire(id);//Recupération de la subCategory dns la bd
        if (subCategoryDansLaBD.getId() == subCategory.getId() && subCategoryDansLaBD.getId() == id){
            //Ajout des informations recu
            subCategoryDansLaBD.setTitle(subCategory.getTitle());
            //subCategoryDansLaBD.setAddedBy(subCategory.getAddedBy());
            subCategoryDansLaBD.setCategory(subCategory.getCategory());
            subCategoryDansLaBD.setDescription(subCategory.getDescription());
            this.subCategoryRepository.save(subCategoryDansLaBD);
        }
    }

    public void supprimer(Long id) {
        this.subCategoryRepository.deleteById(id);
    }
}
