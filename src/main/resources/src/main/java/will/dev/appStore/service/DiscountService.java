package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import will.dev.appStore.entites.Discount;
import will.dev.appStore.repository.DiscountRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;

    public String creerDiscount(Discount discount){
        Discount discountDansBd = this.discountRepository.findByTitle(discount.getTitle());
        if (discountDansBd == null){
            this.discountRepository.save(discount);
            return "Discount '" + discount.getTitle() + "' créee avec succès.";
        }else {
            return "Discount '" + discount.getTitle() + "' existe déjà.";
        }
    }

    public List<Discount> rechercher(){return this.discountRepository.findAll();}

    public Discount lire(Long id){
        Optional<Discount> optionalDiscount = this.discountRepository.findById(id);
        return optionalDiscount.orElseThrow(()-> new EntityNotFoundException("Aucun discount n'existe avec cette identifiant"));
    }

    // Update
    public Discount updateDiscount(Long id, Discount discountDetails) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount not found with id " + id));

        discount.setAddedBy(discountDetails.getAddedBy());
        discount.setTitle(discountDetails.getTitle());
        discount.setSlug(discountDetails.getSlug());
        discount.setCode(discountDetails.getCode());
        discount.setDescription(discountDetails.getDescription());
        discount.setValue(discountDetails.getValue());
        discount.setValueUnit(discountDetails.getValueUnit());
        discount.setUsableOnce(discountDetails.isUsableOnce());
        discount.setUsedAt(discountDetails.getUsedAt());
        discount.setUsedBy(discountDetails.getUsedBy());
        discount.setUpdatedBy(discountDetails.getUpdatedBy());
        discount.setDeletedAt(discountDetails.getDeletedAt());
        discount.setDeletedBy(discountDetails.getDeletedBy());

        return discountRepository.save(discount);
    }

    // Delete
    public void deleteDiscount(Long id) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount not found with id " + id));

        discountRepository.delete(discount);
    }
}
