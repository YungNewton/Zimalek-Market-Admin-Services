package com.zmarket.marketadminservice.runner;

import com.zmarket.marketadminservice.modules.businesscategory.models.BusinessCategory;
import com.zmarket.marketadminservice.modules.businesscategory.repositories.BusinessCategoryRepository;
import com.zmarket.marketadminservice.modules.market.model.Market;
import com.zmarket.marketadminservice.modules.market.repository.MarketRepository;
import com.zmarket.marketadminservice.modules.states.model.State;
import com.zmarket.marketadminservice.modules.states.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationStarterRunner implements ApplicationRunner {


    private final StateRepository stateRepository;

    private final MarketRepository marketRepository;

    private final BusinessCategoryRepository businessCategoryRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Optional<State> optionalState = stateRepository.findFirstByName("Lagos");
        State state;
        if (optionalState.isEmpty()) {
            state = stateRepository.save(new State("Lagos", true, new Date()));
        } else {
            state = optionalState.get();
        }

        Optional<Market> optionalMarket = marketRepository.findFirstByNameAndState_Name("Market A", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market A", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market C", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market C", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market B", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market B", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market D", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market D", state, state.getName(), new Date()));
        }

        optionalState = stateRepository.findFirstByName("Abuja");
        if (optionalState.isEmpty()) {
            state = stateRepository.save(new State("Abuja", true, new Date()));
        } else {
            state = optionalState.get();
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market E", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market E", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market F", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market F", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market G", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market G", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market H", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market H", state, state.getName(), new Date()));
        }

        optionalState = stateRepository.findFirstByName("Abia");
        if (optionalState.isEmpty()) {
            state = stateRepository.save(new State("Abia", true, new Date()));
        } else {
            state = optionalState.get();
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market I", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market I", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market J", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market J", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market K", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market K", state, state.getName(), new Date()));
        }

        optionalState = stateRepository.findFirstByName("Akwa Ibom");
        if (optionalState.isEmpty()) {
            state = stateRepository.save(new State("Akwa Ibom", true, new Date()));
        } else {
            state = optionalState.get();
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market M", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market M", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market P", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market P", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market O", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market O", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market Q", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market Q", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market R", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market R", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market V", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market V", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market UU", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market UU", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market UUU", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market UUU", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market UUUU", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market UUUU", state, state.getName(), new Date()));
        }

        optionalState = stateRepository.findFirstByName("Edo");
        if (optionalState.isEmpty()) {
            state = stateRepository.save(new State("Edo", true, new Date()));
        } else {
            state = optionalState.get();
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market T", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market T", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market S", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market S", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market W", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market W", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market X", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market X", state, state.getName(), new Date()));
        }

        optionalState = stateRepository.findFirstByName("Delta");
        if (optionalState.isEmpty()) {
            state = stateRepository.save(new State("Delta", true, new Date()));
        } else {
            state = optionalState.get();
        }

        optionalState = stateRepository.findFirstByName("Imo");
        if (optionalState.isEmpty()) {
            state = stateRepository.save(new State("Imo", true, new Date()));
        } else {
            state = optionalState.get();
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market Y", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market Y", state, state.getName(), new Date()));
        }

        optionalMarket = marketRepository.findFirstByNameAndState_Name("Market Z", state.getName());
        if (optionalState.isEmpty()) {
            marketRepository.save(new Market("Market Z", state, state.getName(), new Date()));
        }



        Optional<BusinessCategory> category = businessCategoryRepository.findFirstByName("Technology");
        if (category.isEmpty()) {
            businessCategoryRepository.save(new BusinessCategory("Technology", new Date()));
        }

        category = businessCategoryRepository.findFirstByName("Fashion");
        if (category.isEmpty()) {
            businessCategoryRepository.save(new BusinessCategory("Fashion", new Date()));
        }

        category = businessCategoryRepository.findFirstByName("Music");
        if (category.isEmpty()) {
            businessCategoryRepository.save(new BusinessCategory("Music", new Date()));
        }

        category = businessCategoryRepository.findFirstByName("Movies");
        if (category.isEmpty()) {
            businessCategoryRepository.save(new BusinessCategory("Movies", new Date()));
        }

        category = businessCategoryRepository.findFirstByName("Electronics");
        if (category.isEmpty()) {
            businessCategoryRepository.save(new BusinessCategory("Electronics", new Date()));
        }

        category = businessCategoryRepository.findFirstByName("Furniture");
        if (category.isEmpty()) {
            businessCategoryRepository.save(new BusinessCategory("Furniture", new Date()));
        }

        category = businessCategoryRepository.findFirstByName("Games");
        if (category.isEmpty()) {
            businessCategoryRepository.save(new BusinessCategory("Games", new Date()));
        }


    }
}
