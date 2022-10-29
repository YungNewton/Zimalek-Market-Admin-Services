package com.zmarket.marketadminservice.modules.shoprating.controller;

import com.zmarket.marketadminservice.annotations.WrapApiResponse;
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
public class ShopRatingController {
    private final ShopRatingService shopRatingService;

    @PostMapping("/{id}")
    public ShopRating rateShop(@PathVariable Long id, @RequestBody @Valid ShopRatingDto request){
        return shopRatingService.rateShop(id,request);
    }

    @GetMapping("/{id}")
    public List<ShopRating> getAllShopRatingByShopId(@PathVariable Long id){
        return shopRatingService.getAllShopRatingByShopId(id);
    }
}
