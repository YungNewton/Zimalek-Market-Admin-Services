package com.zmarket.marketadminservice.modules.shopproduct.controllers;

import com.zmarket.marketadminservice.annotations.WrapApiResponse;
import com.zmarket.marketadminservice.modules.shopproduct.dto.ShopProductDto;
import com.zmarket.marketadminservice.modules.shopproduct.model.ShopProduct;
import com.zmarket.marketadminservice.modules.shopproduct.service.ShopProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/products")
@WrapApiResponse
public class ShopProductAdminController {

    private final ShopProductService shopProductService;

    @PreAuthorize("hasRole('ROLE_MARKET_SELLER')")
    @PostMapping
    public ShopProduct createProduct(@RequestBody @Valid ShopProductDto shopProductDto, @RequestHeader(value = "shop-id") Long shopId){
        return shopProductService.createProduct(shopProductDto, shopId);
    }

    @PreAuthorize("hasRole('ROLE_MARKET_SELLER')")
    @PutMapping("/{id}")
    public ShopProduct updateProduct(@PathVariable Long id, @RequestBody @Valid ShopProductDto shopDto, @RequestHeader(value = "shop-id") Long shopId){
        return shopProductService.updateProduct(shopDto, id, shopId);
    }

    @PreAuthorize("hasRole('ROLE_MARKET_SELLER')")
    @DeleteMapping("/{id}")
    public Object deleteProductById(@PathVariable Long id, @RequestHeader(value = "shop-id") Long shopId){
        return shopProductService.deleteProductById(id, shopId);
    }

}
