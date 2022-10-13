package com.zmarket.marketadminservice.modules.shopproduct.controllers;

import com.zmarket.marketadminservice.annotations.WrapApiResponse;
import com.zmarket.marketadminservice.modules.shop.dtos.ShopDto;
import com.zmarket.marketadminservice.modules.shop.dtos.UpdateShopDto;
import com.zmarket.marketadminservice.modules.shop.models.Shop;
import com.zmarket.marketadminservice.modules.shop.services.ShopService;
import com.zmarket.marketadminservice.modules.shopproduct.dto.ShopProductDto;
import com.zmarket.marketadminservice.modules.shopproduct.model.ShopProduct;
import com.zmarket.marketadminservice.modules.shopproduct.service.ShopProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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
