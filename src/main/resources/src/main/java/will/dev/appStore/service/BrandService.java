package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import will.dev.appStore.entites.Brand;
import will.dev.appStore.repository.BrandRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public String creerBrand(Brand brand){
        Brand brandDansBD = this.brandRepository.findByTitle(brand.getTitle());
        if (brandDansBD ==  null ){
            this.brandRepository.save(brand);
            return "La marque '" + brand.getTitle() + "' créée avec succès.";
        }else {
            return "La marque '" + brand.getTitle() + "' existe déjà.";
        }
    }

    public List<Brand> rechercher(){
        return  this.brandRepository.findAll();
    }

    public Brand lire(Long id) {
        Optional<Brand> optionalBrand =  this.brandRepository.findById(id);
        return optionalBrand.orElseThrow(() -> new EntityNotFoundException("Aucune marque n'existe avec cette identifiant"));
    }

    public Brand modifier(Long id, Brand brand) {
        Brand brandDansLaBD = brandRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Brand not found with id " + id));

        brandDansLaBD.setUser(brand.getUser());
        brandDansLaBD.setTitle(brand.getTitle());
        brandDansLaBD.setDescription(brand.getDescription());
        brandDansLaBD.setLogoPath(brand.getLogoPath());
        brandDansLaBD.setWebAddress(brand.getWebAddress());
        brandDansLaBD.setSlug(brand.getSlug());
        brandDansLaBD.setFacebook(brand.getFacebook());
        brandDansLaBD.setInstagram(brand.getInstagram());
        brandDansLaBD.setTwitterX(brand.getTwitterX());
        brandDansLaBD.setOtherSocial(brand.getOtherSocial());
        brandDansLaBD.setOtherSocialHandle(brand.getOtherSocialHandle());
        brandDansLaBD.setDeletedAt(brand.getDeletedAt());
        brandDansLaBD.setDeletedBy(brand.getDeletedBy());

        return brandRepository.save(brandDansLaBD);
    }

    public void supprimer(Long id) {
        this.brandRepository.deleteById(id);
    }
}
