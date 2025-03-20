package will.dev.appStore.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import will.dev.appStore.dto.*;
import will.dev.appStore.entites.*;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProductDtoMapper implements Function<Product, ProductDTO> {

    private final Function<User, UserDTO> userDtoMapper;
    //private final Function<SubCategory, SubCategoryDTO> subCategoryDtoMapper;
    //private final Function<Brand, BrandDTO> brandDtoMapper;
    //private final Function<Discount, DiscountDTO> discountDtoMapper;

    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                userDtoMapper.apply(product.getAddedBy()),
                /*subCategoryDtoMapper.apply(product.getSubCategory()),
                brandDtoMapper.apply(product.getBrand()),
                discountDtoMapper.apply(product.getDiscount()),*/
                product.getTitle(),
                product.getSlug(),
                product.getDescription(),
                product.getFeatures(),
                product.getRetailPrice(),
                product.getInitialRetailPrice(),
                product.getCurrency(),
                product.getDeletedAt(),
                product.getDeletedBy() != null ? userDtoMapper.apply(product.getDeletedBy()) : null,
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}