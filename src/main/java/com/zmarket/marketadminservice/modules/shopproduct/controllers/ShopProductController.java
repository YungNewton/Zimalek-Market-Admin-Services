package com.zmarket.marketadminservice.modules.shopproduct.controllers;

import com.zmarket.marketadminservice.annotations.WrapApiResponse;
import com.zmarket.marketadminservice.modules.shopproduct.model.ShopProduct;
import com.zmarket.marketadminservice.modules.shopproduct.service.ShopProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@WrapApiResponse
public class ShopProductController {

    private final ShopProductService shopProductService;

    @GetMapping("/{id}")
    public ShopProduct getProductById(@PathVariable Long id){
        return shopProductService.getProductById(id);
    }

    @GetMapping("/shop/{shopId}")
    public Page<ShopProduct> getProductByShopId(@PathVariable Long shopId,
                                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String color,
                                                @RequestParam(required = false) String category,
                                                @RequestParam(required = false) BigDecimal price,
                                                @PageableDefault(size = 7 ) @SortDefault.SortDefaults({
                                                        @SortDefault(sort = "productName", direction = Sort.Direction.ASC),
                                                        @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
                                                }) Pageable pageable){
        return shopProductService.getProductByShopId(shopId, start, end, name, color, category, price, pageable);
    }

    @GetMapping
    public Page<ShopProduct> getAllProducts(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String color,
                                            @RequestParam(required = false) String category,
                                            @RequestParam(required = false) BigDecimal price,
                                            @PageableDefault(size = 7 ) @SortDefault.SortDefaults({
                                                     @SortDefault(sort = "productName", direction = Sort.Direction.ASC),
                                                     @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
                                             }) Pageable pageable){
        return shopProductService. getAllProducts(start, end, name, color, category, price, pageable);
    }

}
