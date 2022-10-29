package com.zmarket.marketadminservice.modules.marketrating.service;

import com.zmarket.marketadminservice.modules.marketrating.dto.MarketRatingDto;
import com.zmarket.marketadminservice.modules.marketrating.model.MarketRating;

import java.util.List;

public interface MarketRatingService {
    MarketRating rateMarket(Long id, MarketRatingDto request);

    List<MarketRating> getAllMarketRatingByMarketId(Long id);
}
