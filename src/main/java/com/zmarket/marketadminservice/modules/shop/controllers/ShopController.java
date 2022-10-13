package com.zmarket.marketadminservice.modules.shop.controllers;

import com.zmarket.marketadminservice.annotations.WrapApiResponse;
import com.zmarket.marketadminservice.modules.shop.models.Shop;
import com.zmarket.marketadminservice.modules.shop.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shops")
@WrapApiResponse
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/{id}")
    public Shop getStoreById(@PathVariable Long id){
        return shopService.getStoreById(id);
    }

    @GetMapping("/handle/{handle}")
    public Shop getStoreByHandle(@PathVariable String handle){
        return shopService.getShopByHandle(handle);
    }

    @GetMapping
    public Page<Shop> getAllStore(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                  @RequestParam(required = false) String category,
                                  @PageableDefault(size = 7 ) @SortDefault.SortDefaults({
                                          @SortDefault(sort = "name", direction = Sort.Direction.ASC),
                                          @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
                                  }) Pageable pageable){
        return shopService.getAllShops(start, end, category, pageable);
    }

    @GetMapping("/market/{marketId}")
    public Page<Shop> getMarketStores(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                      @RequestParam(required = false) String category,
                                      @PageableDefault(size = 7 ) @SortDefault.SortDefaults({
                                              @SortDefault(sort = "name", direction = Sort.Direction.ASC),
                                              @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
                                      }) Pageable pageable, @PathVariable Long marketId){
        return shopService.getShopsByMarketId(marketId, start, end, category, pageable);
    }
}
