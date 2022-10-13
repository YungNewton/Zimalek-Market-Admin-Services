package com.zmarket.marketadminservice.modules.shopproduct.dto;

import com.zmarket.marketadminservice.modules.colour.dto.ColourDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopProductDto {
    @NotBlank(message = "product name is required")
    private String productName;

    @NotNull(message = "price is required")
    private BigDecimal price;

    @NotNull(message = "category is required")
    private Long productCategoryId;

    @NotBlank(message = "description is required")
    private String description;

    private Set<String> imageUrls;

    private Set<@Valid ColourDto> colours;

}

