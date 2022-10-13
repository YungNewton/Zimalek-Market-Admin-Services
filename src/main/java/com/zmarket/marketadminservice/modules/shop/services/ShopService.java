package com.zmarket.marketadminservice.modules.shop.services;

import com.zmarket.marketadminservice.modules.shop.dtos.ShopDto;
import com.zmarket.marketadminservice.modules.shop.dtos.UpdateShopDto;
import com.zmarket.marketadminservice.modules.shop.models.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ShopService {
    Shop createShop(ShopDto storedto);
    Shop updateShop(UpdateShopDto storedto, Long shopId);
    Shop getStoreById(Long id);
    Shop getShopByHandle(String handle);
    List<Shop> getAllUserShops();
    Page<Shop> getAllShops(LocalDate start, LocalDate end, String category, Pageable pageable);
    Page<Shop> getShopsByMarketId(Long marketId, LocalDate start, LocalDate end, String category, Pageable pageable);

}
