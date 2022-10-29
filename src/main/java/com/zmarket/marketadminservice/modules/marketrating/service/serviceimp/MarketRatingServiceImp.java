package com.zmarket.marketadminservice.modules.marketrating.service.serviceimp;

import com.zmarket.marketadminservice.exceptions.NotFoundException;
import com.zmarket.marketadminservice.modules.market.model.Market;
import com.zmarket.marketadminservice.modules.market.repository.MarketRepository;
import com.zmarket.marketadminservice.modules.marketrating.dto.MarketRatingDto;
import com.zmarket.marketadminservice.modules.marketrating.model.MarketRating;
import com.zmarket.marketadminservice.modules.marketrating.repository.MarketRatingRepository;
import com.zmarket.marketadminservice.modules.marketrating.service.MarketRatingService;
import com.zmarket.marketadminservice.modules.security.jwt.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MarketRatingServiceImp implements MarketRatingService {

    private final MarketRatingRepository marketRatingRepository;
    private final MarketRepository marketRepository;
    private final CurrentUser currentUser;

    @Override
    public MarketRating rateMarket(Long id, MarketRatingDto request) {
        Optional<Market> optionalMarket = marketRepository.findById(id);
        if (optionalMarket.isEmpty()){
            throw new NotFoundException("Market with id not found");
        }
        Market market = optionalMarket.get();
        market = updateMarketRatingInfo(request, market);

        MarketRating marketRating = new MarketRating();
        marketRating.setStarCount(request.getStarCount());
        marketRating.setMarket(market);
        marketRating.setComment(request.getComment());
        marketRating.setAnonymous(request.isAnonymous());
        marketRating.setUserId(currentUser.getId());
        marketRating.setUserFullName(currentUser.getFirstname()+" "+currentUser.getLastname());
        marketRating.setCreatedAt(new Date());

        marketRating = marketRatingRepository.save(marketRating);
        return marketRating;
    }

    private Market updateMarketRatingInfo(MarketRatingDto request, Market market) {
        if (request.getStarCount()==1){
            market.setTotalOneStarRating(market.getTotalOneStarRating()+1);
        }
        if (request.getStarCount()==2){
            market.setTotalTwoStarRating(market.getTotalTwoStarRating()+2);
        }
        if (request.getStarCount()==3){
            market.setTotalThreeStarRating(market.getTotalThreeStarRating()+3);
        }
        if (request.getStarCount()==4){
            market.setTotalFourStarRating(market.getTotalFourStarRating()+4);
        }
        if (request.getStarCount()==5){
            market.setTotalFiveStarRating(market.getTotalFiveStarRating()+5);
        }

        int totalCount= market.getTotalStarRating()+1;

        int average = (market.getTotalOneStarRating()+ (market.getTotalTwoStarRating()*2)+(market.getTotalThreeStarRating()*3)+(market.getTotalFourStarRating()*4)+ (market.getTotalFiveStarRating()*5))/totalCount;

        market.setTotalStarRating(totalCount);
        market.setAvgStarRating(average);
        market = marketRepository.save(market);
        return market;
    }

    @Override
    public List<MarketRating> getAllMarketRatingByMarketId(Long id) {
        Optional<Market> optionalMarket = marketRepository.findById(id);
        if (optionalMarket.isEmpty()){
            throw new NotFoundException("Market with id not found");
        }
        return marketRatingRepository.findByMarket(optionalMarket.get());
    }
}
