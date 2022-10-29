package com.zmarket.marketadminservice.modules.marketrating.repository;

import com.zmarket.marketadminservice.modules.market.model.Market;
import com.zmarket.marketadminservice.modules.marketrating.model.MarketRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketRatingRepository extends JpaRepository<MarketRating, Long> {

    List<MarketRating> findByMarket(Market market);
}
