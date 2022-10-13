package com.zmarket.marketadminservice.modules.market.repository;

import com.zmarket.marketadminservice.modules.market.model.Market;
import com.zmarket.marketadminservice.modules.states.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    Optional<Market> findFirstByNameAndState_Name(String name, String name1);

    List<Market> findByState(State state);


}