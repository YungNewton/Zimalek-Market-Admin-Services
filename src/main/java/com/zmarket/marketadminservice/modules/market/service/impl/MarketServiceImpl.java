package com.zmarket.marketadminservice.modules.market.service.impl;

import com.zmarket.marketadminservice.exceptions.NoContentException;
import com.zmarket.marketadminservice.exceptions.NotFoundException;
import com.zmarket.marketadminservice.modules.market.model.Market;
import com.zmarket.marketadminservice.modules.market.repository.MarketRepository;
import com.zmarket.marketadminservice.modules.market.service.MarketService;
import com.zmarket.marketadminservice.modules.states.model.State;
import com.zmarket.marketadminservice.modules.states.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository;

    private final StateRepository stateRepository;

    @Override
    public Market getMarketById(Long id) {
        Optional<Market> market = marketRepository.findById(id);
        if (market.isEmpty()) {
            throw new NotFoundException("Market not found");
        }
        return market.get();
    }

    @Override
    public List<Market> getAllMarkets() {
        List<Market> market = marketRepository.findAll();
        if (market.isEmpty()) {
            throw new NoContentException();
        }
        return market;
    }

    @Override
    public List<Market> getMarketsByState(Long id) {
        Optional<State> state = stateRepository.findById(id);
        if (state.isEmpty()) {
            throw new NotFoundException("State not found");
        }


        List<Market> markets = marketRepository.findByState(state.get());
        if (markets.isEmpty()) {
            throw new NoContentException();
        }
        return markets;
    }
}
