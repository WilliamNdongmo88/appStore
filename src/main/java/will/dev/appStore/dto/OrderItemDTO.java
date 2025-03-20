package will.dev.appStore.dto;

import java.util.Date;

public record OrderItemDTO(
        Long id,
        UserDTO user, // Utilisation de UserDTO pour représenter l'utilisateur
        ProductDTO product, // Utilisation de ProductDTO pour représenter le produit
        OrderDTO order, // Utilisation de OrderDTO pour représenter la commande
        int quantity,
        double price,
        String deletedAt,
        UserDTO deletedBy, // Utilisation de UserDTO pour représenter l'utilisateur qui a supprimé
        Date createdAt,
        Date updatedAt
) {}