package com.zmarket.marketadminservice.modules.shop.repositories;

import com.zmarket.marketadminservice.modules.shop.models.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findFirstByName(String name);
    Optional<Shop> findFirstByHandle(String handle);
    Optional<Shop> findFirstByIdAndUserId(Long id, Long userId);
    List<Shop> findByUserId(Long userId);
    Page<Shop> findByMarketId(Long marketId, Pageable pageable);
}