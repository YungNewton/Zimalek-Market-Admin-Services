package com.zmarket.marketadminservice.modules.market.service;

import com.zmarket.marketadminservice.modules.market.model.Market;

import java.util.List;

public interface MarketService {

    Market getMarketById(Long id);

    List<Market> getAllMarkets();

    List<Market> getMarketsByState(Long id);
}
