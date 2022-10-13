package com.zmarket.marketadminservice.modules.shopproduct.service;

import com.zmarket.marketadminservice.modules.shopproduct.dto.ShopProductDto;
import com.zmarket.marketadminservice.modules.shopproduct.model.ShopProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ShopProductService {
    ShopProduct createProduct(ShopProductDto dto, Long shopId);
    ShopProduct updateProduct(ShopProductDto dto, Long productId, Long shopId);
    ShopProduct getProductById(Long productId);
    Object deleteProductById(Long productId, Long shopId);
    Page<ShopProduct> getProductByShopId(Long shopId, LocalDate start, LocalDate end, String name, String color, String category, BigDecimal price, Pageable pageable);
    Page<ShopProduct> getAllProducts(LocalDate start, LocalDate end, String name, String color, String category, BigDecimal price, Pageable pageable);
}
