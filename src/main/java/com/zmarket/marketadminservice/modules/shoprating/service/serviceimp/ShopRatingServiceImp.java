package com.zmarket.marketadminservice.modules.shoprating.service.serviceimp;

import com.zmarket.marketadminservice.exceptions.NotFoundException;
import com.zmarket.marketadminservice.modules.security.jwt.CurrentUser;
import com.zmarket.marketadminservice.modules.shop.models.Shop;
import com.zmarket.marketadminservice.modules.shop.repositories.ShopRepository;
import com.zmarket.marketadminservice.modules.shoprating.dto.ShopRatingDto;
import com.zmarket.marketadminservice.modules.shoprating.model.ShopRating;
import com.zmarket.marketadminservice.modules.shoprating.repository.ShopRatingRepository;
import com.zmarket.marketadminservice.modules.shoprating.service.ShopRatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShopRatingServiceImp implements ShopRatingService {

    private final ShopRatingRepository shopRatingRepository;
    private final ShopRepository shopRepository;
    private final CurrentUser currentUser;

    @Override
    public ShopRating rateShop(Long id, ShopRatingDto request) {
        Optional<Shop> optionalShop = shopRepository.findById(id);
        if (optionalShop.isEmpty()){
            throw new NotFoundException("Shop with id not found");
        }
        Shop shop = optionalShop.get();
        shop = updateShopRatingInfo(request, shop);

        ShopRating shopRating = new ShopRating();
        shopRating.setStarCount(request.getStarCount());
        shopRating.setShop(shop);
        shopRating.setComment(request.getComment());
        shopRating.setAnonymous(request.isAnonymous());
        shopRating.setUserId(currentUser.getId());
        shopRating.setUserFullName(currentUser.getFirstname()+" "+currentUser.getLastname());
        shopRating.setCreatedAt(new Date());

        shopRating = shopRatingRepository.save(shopRating);
        return shopRating;
    }

    private Shop updateShopRatingInfo(ShopRatingDto request, Shop shop) {
        if (request.getStarCount()==1){
            shop.setTotalOneStarRating(shop.getTotalOneStarRating()+1);
        }
        if (request.getStarCount()==2){
            shop.setTotalTwoStarRating(shop.getTotalTwoStarRating()+2);
        }
        if (request.getStarCount()==3){
            shop.setTotalThreeStarRating(shop.getTotalThreeStarRating()+3);
        }
        if (request.getStarCount()==4){
            shop.setTotalFourStarRating(shop.getTotalFourStarRating()+4);
        }
        if (request.getStarCount()==5){
            shop.setTotalFiveStarRating(shop.getTotalFiveStarRating()+5);
        }

        int totalCount= shop.getTotalStarRating()+1;

        int average = (shop.getTotalOneStarRating()+ (shop.getTotalTwoStarRating()*2)+(shop.getTotalThreeStarRating()*3)+(shop.getTotalFourStarRating()*4)+ (shop.getTotalFiveStarRating()*5))/totalCount;

        shop.setTotalStarRating(totalCount);
        shop.setAvgStarRating(average);
        shop = shopRepository.save(shop);
        return shop;
    }

    @Override
    public List<ShopRating> getAllShopRatingByShopId(Long id) {
        Optional<Shop> optionalShop = shopRepository.findById(id);
        if (optionalShop.isEmpty()){
            throw new NotFoundException("Shop with id not found");
        }
        return shopRatingRepository.findByShop(optionalShop.get());
    }
}
