package will.dev.appStore.dto;

import java.util.Date;

public record ProductDTO(
        Long id,
        UserDTO addedBy, // Utilisation de UserDTO pour représenter l'utilisateur qui a ajouté le produit
        //SubCategoryDTO subCategory, // Utilisation de SubCategoryDTO pour représenter la sous-catégorie
        //BrandDTO brand, // Utilisation de BrandDTO pour représenter la marque
        //DiscountDTO discount, // Utilisation de DiscountDTO pour représenter la réduction
        String title,
        String slug,
        String description,
        String features,
        String retailPrice,
        String initialRetailPrice,
        String currency,
        String deletedAt,
        UserDTO deletedBy, // Utilisation de UserDTO pour représenter l'utilisateur qui a supprimé
        Date createdAt,
        Date updatedAt
) {}