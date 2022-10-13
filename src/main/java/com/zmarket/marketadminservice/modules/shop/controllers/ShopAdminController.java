package com.zmarket.marketadminservice.modules.shop.controllers;

import com.zmarket.marketadminservice.annotations.WrapApiResponse;
import com.zmarket.marketadminservice.modules.shop.dtos.ShopDto;
import com.zmarket.marketadminservice.modules.shop.dtos.UpdateShopDto;
import com.zmarket.marketadminservice.modules.shop.models.Shop;
import com.zmarket.marketadminservice.modules.shop.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/admin/shops")
@WrapApiResponse
public class ShopAdminController {

    private final ShopService shopService;

    @PreAuthorize("hasRole('ROLE_MARKET_SELLER')")
    @PostMapping
    public Shop createShop(@RequestBody @Valid ShopDto shopDto){
        return shopService.createShop(shopDto);
    }

    @PreAuthorize("hasRole('ROLE_MARKET_SELLER')")
    @PutMapping("/{id}")
    public Shop updateShop(@PathVariable Long id, @RequestBody @Valid UpdateShopDto shopDto){
        return shopService.updateShop(shopDto, id);
    }

    @PreAuthorize("hasRole('ROLE_MARKET_SELLER')")
    @GetMapping
    public List<Shop> getAllAdminShops(){
        return shopService.getAllUserShops();
    }

}
