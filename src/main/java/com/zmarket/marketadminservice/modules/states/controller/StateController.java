package com.zmarket.marketadminservice.modules.states.controller;

import com.zmarket.marketadminservice.annotations.WrapApiResponse;
import com.zmarket.marketadminservice.modules.market.model.Market;
import com.zmarket.marketadminservice.modules.market.service.MarketService;
import com.zmarket.marketadminservice.modules.states.model.State;
import com.zmarket.marketadminservice.modules.states.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/states")
@WrapApiResponse
public class StateController {

    private final StateRepository stateRepository;

    @GetMapping()
    public List<State> getAllMarkets(){
        return stateRepository.findAll();
    }

}
