package com.zmarket.marketadminservice.modules.market.controller;

import com.zmarket.marketadminservice.annotations.WrapApiResponse;
import com.zmarket.marketadminservice.modules.market.model.Market;
import com.zmarket.marketadminservice.modules.market.service.MarketService;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/markets")
@WrapApiResponse
public class MarketController {

    private final MarketService marketService;

    @GetMapping("/{id}")
    public Market getMarketById(@PathVariable Long id){
        return marketService.getMarketById(id);
    }

    @GetMapping()
    public List<Market> getAllMarkets(){
        return marketService.getAllMarkets();
    }

    @GetMapping("/state/{id}")
    public List<Market> getMarketsByState(@PathVariable Long id){
        return marketService.getMarketsByState(id);
    }


}
