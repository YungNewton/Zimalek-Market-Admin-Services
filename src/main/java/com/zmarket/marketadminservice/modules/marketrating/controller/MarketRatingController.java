package com.zmarket.marketadminservice.modules.marketrating.controller;

import com.zmarket.marketadminservice.annotations.WrapApiResponse;
import com.zmarket.marketadminservice.modules.marketrating.dto.MarketRatingDto;
import com.zmarket.marketadminservice.modules.marketrating.model.MarketRating;
import com.zmarket.marketadminservice.modules.marketrating.service.MarketRatingService;
import com.zmarket.marketadminservice.modules.shoprating.dto.ShopRatingDto;
import com.zmarket.marketadminservice.modules.shoprating.model.ShopRating;
import com.zmarket.marketadminservice.modules.shoprating.service.ShopRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ratings")
@WrapApiResponse
@RequiredArgsConstructor
public class MarketRatingController {
    private final MarketRatingService marketRatingService;

    @PostMapping("/{id}")
    public MarketRating rateMarket(@PathVariable Long id, @RequestBody @Valid MarketRatingDto request){
        return marketRatingService.rateMarket(id,request);
    }

    @GetMapping("/{id}")
    public List<MarketRating> getAllShopRatingByShopId(@PathVariable Long id){
        return marketRatingService.getAllMarketRatingByMarketId(id);
    }

}
