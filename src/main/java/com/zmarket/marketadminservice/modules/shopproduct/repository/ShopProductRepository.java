package com.zmarket.marketadminservice.modules.shopproduct.repository;

import com.zmarket.marketadminservice.modules.shop.models.Shop;
import com.zmarket.marketadminservice.modules.shopproduct.model.ShopProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopProductRepository extends JpaRepository<ShopProduct, Long> {
    Optional<ShopProduct> findFirstByShopAndId(Shop shop, Long id);

    Page<ShopProduct> findByShop(Shop shop, Pageable pageable);

    Optional<ShopProduct> findFirstByIdAndUserId(Long id, Long userId);




}