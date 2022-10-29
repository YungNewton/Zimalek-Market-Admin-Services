package com.zmarket.marketadminservice.modules.shoprating.service;

import com.zmarket.marketadminservice.modules.shoprating.dto.ShopRatingDto;
import com.zmarket.marketadminservice.modules.shoprating.model.ShopRating;

import java.util.List;

public interface ShopRatingService {
    ShopRating rateShop(Long id, ShopRatingDto request);

    List<ShopRating> getAllShopRatingByShopId(Long id);
}
