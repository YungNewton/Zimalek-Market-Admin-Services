package com.zmarket.marketadminservice.modules.shoprating.repository;

import com.zmarket.marketadminservice.modules.shop.models.Shop;
import com.zmarket.marketadminservice.modules.shoprating.model.ShopRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRatingRepository extends JpaRepository<ShopRating, Long> {
    List<ShopRating> findByShop(Shop shop);
}
