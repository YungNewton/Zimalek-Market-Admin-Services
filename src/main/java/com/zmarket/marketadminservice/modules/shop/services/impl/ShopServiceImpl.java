package com.zmarket.marketadminservice.modules.shop.services.impl;

import com.zmarket.marketadminservice.exceptions.BadRequestException;
import com.zmarket.marketadminservice.exceptions.ForbiddenException;
import com.zmarket.marketadminservice.exceptions.NoContentException;
import com.zmarket.marketadminservice.exceptions.NotFoundException;
import com.zmarket.marketadminservice.modules.businesscategory.models.BusinessCategory;
import com.zmarket.marketadminservice.modules.businesscategory.repositories.BusinessCategoryRepository;
import com.zmarket.marketadminservice.modules.market.model.Market;
import com.zmarket.marketadminservice.modules.market.repository.MarketRepository;
import com.zmarket.marketadminservice.modules.security.jwt.TokenProvider;
import com.zmarket.marketadminservice.modules.shop.dtos.ShopDto;
import com.zmarket.marketadminservice.modules.shop.dtos.UpdateShopDto;
import com.zmarket.marketadminservice.modules.shop.models.Shop;
import com.zmarket.marketadminservice.modules.shop.repositories.ShopRepository;
import com.zmarket.marketadminservice.modules.shop.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    @PersistenceContext
    private EntityManager entityManager;
    
    private final TokenProvider tokenProvider;

    private final ShopRepository shopRepository;
    
    private final MarketRepository marketRepository;

    private final BusinessCategoryRepository businessCategoryRepository;

    @Override
    public Shop createShop(ShopDto shopDto) {
        Optional<Shop> optionalShop = shopRepository.findFirstByName(shopDto.getName());
        if(optionalShop.isPresent()) {
            throw new BadRequestException("shop with name already exist");
        }

        optionalShop = shopRepository.findFirstByHandle(shopDto.getHandle());
        if(optionalShop.isPresent()) {
            throw new BadRequestException("shop with handle already exist");
        }

        Optional<BusinessCategory> businessCategory = businessCategoryRepository.findById(shopDto.getBusinessCategoryId());
        if(businessCategory.isEmpty()) {
            throw new NotFoundException("Business category does not exist");
        }

        Optional<Market> optionalMarket = marketRepository.findById(shopDto.getMarketId());
        if(optionalMarket.isEmpty()) {
            throw new NotFoundException("Market does not exist");
        }

        Shop shop = createNewShop(shopDto, optionalMarket.get(), businessCategory.get());

        return shop;
    }


    @Override
    public Shop updateShop(UpdateShopDto shopDto, Long shopId) {
        Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if(optionalShop.isEmpty()) {
            throw new NotFoundException("shop does not exist");
        }

        Shop shop = optionalShop.get();
        if (!Objects.equals(shop.getUserId(), tokenProvider.getId())) {
            throw new ForbiddenException("You are not authorized to carryout this operation");
        }

        Optional<BusinessCategory> businessCategory = businessCategoryRepository.findById(shopDto.getBusinessCategoryId());
        if(businessCategory.isEmpty()) {
            throw new NotFoundException("Business category does not exist");
        }

        Optional<Market> optionalMarket = marketRepository.findById(shopDto.getMarketId());
        if(optionalMarket.isEmpty()) {
            throw new NotFoundException("Market does not exist");
        }

        shop = updateShop(shopDto, optionalShop.get(), businessCategory.get(), optionalMarket.get());

        return shop;
    }


    @Override
    public Shop getStoreById(Long id) {
        Optional<Shop> shop = shopRepository.findById(id);
        if(shop.isEmpty()){
            throw new NotFoundException("Store does not exist");
        }

        return shop.get();
    }

    @Override
    public List<Shop> getAllUserShops() {
        List<Shop> shops = shopRepository.findByUserId(tokenProvider.getId());
        if(shops.isEmpty()) {
            throw new NoContentException();
        }
        return shops;
    }

    @Override
    public Page<Shop> getShopsByMarketId(Long marketId, LocalDate startDate, LocalDate endDate, String category, Pageable pageable) {

        Optional<Market> market = marketRepository.findById(marketId);

        LocalDateTime start = Objects.nonNull(startDate) ? LocalDateTime.of(startDate, LocalTime.MIN) : null;
        LocalDateTime end = Objects.nonNull(startDate) ? LocalDateTime.of(endDate, LocalTime.MAX) : null;

        if (Objects.isNull(start) && Objects.isNull(end)  && Objects.isNull(category)) {
            Page<Shop> products = shopRepository.findByMarketId(marketId,pageable);
            if(products.isEmpty()){
                throw new NoContentException();
            }

            return products;
        }

        CriteriaBuilder qb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Shop> cq = qb.createQuery(Shop.class);

        Root<Shop> root = cq.from(Shop.class);

        List<Predicate> predicates = getPredicates(start, end, category, qb, root, cq);

        predicates.add(qb.equal(root.get("market"), market.get()));

        cq.select(root).where(predicates.toArray(new Predicate[]{}));

        List<Shop> res = entityManager.createQuery(cq).getResultList();

        if (res.isEmpty()) {
            throw new NoContentException();
        }

        return new PageImpl<>(res, pageable, res.size());
    }


    @Override
    public Shop getShopByHandle(String handle) {
        Optional<Shop> shop = shopRepository.findFirstByHandle(handle);
        if(shop.isEmpty()){
            throw new NotFoundException("Shop does not exist");
        }

        return shop.get();
    }

    @Override
    public Page<Shop> getAllShops(LocalDate startDate, LocalDate endDate, String category, Pageable pageable) {

        LocalDateTime start = Objects.nonNull(startDate) ? LocalDateTime.of(startDate, LocalTime.MIN) : null;
        LocalDateTime end = Objects.nonNull(startDate) ? LocalDateTime.of(endDate, LocalTime.MAX) : null;

        if (Objects.isNull(start) && Objects.isNull(end)  && Objects.isNull(category)) {
            Page<Shop> products = shopRepository.findAll(pageable);
            if(products.isEmpty()){
                throw new NoContentException();
            }

            return products;
        }

        CriteriaBuilder qb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Shop> cq = qb.createQuery(Shop.class);

        Root<Shop> root = cq.from(Shop.class);

        List<Predicate> predicates = getPredicates(start, end, category, qb, root, cq);

        cq.select(root).where(predicates.toArray(new Predicate[]{}));

        List<Shop> res = entityManager.createQuery(cq).getResultList();

        if (res.isEmpty()) {
            throw new NoContentException();
        }

        return new PageImpl<>(res, pageable, res.size());
    }

    private Shop updateShop(UpdateShopDto shopDto, Shop shop, BusinessCategory businessCategory, Market market) {
        shop.setName(shopDto.getName());
        shop.setUpdatedAt(new Date());
        shop.setCategory(businessCategory);
        shop.setFacebookUrl(shopDto.getFacebookUrl());
        shop.setInstagramUrl(shopDto.getInstagramUrl());
        shop.setLogo(shopDto.getLogo());
        shop.setMarket(market);
        shop.setShopAddress(shopDto.getShopAddress());
        shop.setTwitterUrl(shopDto.getTwitterUrl());
        shop = shopRepository.save(shop);
        return shop;
    }


    private Shop createNewShop(ShopDto storedto, Market market, BusinessCategory businessCategory) {
        Shop shop = new Shop();
        shop.setName(storedto.getName());
        shop.setCreatedAt(new Date());
        shop.setCategory(businessCategory);
        shop.setFacebookUrl(storedto.getFacebookUrl());
        shop.setInstagramUrl(storedto.getInstagramUrl());
        shop.setLogo(storedto.getLogo());
        shop.setName(storedto.getName());
        shop.setMarket(market);
        shop.setShopAddress(storedto.getShopAddress());
        shop.setTwitterUrl(storedto.getTwitterUrl());
        shop.setHandle(storedto.getHandle());
        shop.setUserId(tokenProvider.getId());
        shop = shopRepository.save(shop);
        return shop;
    }


    private List<Predicate> getPredicates(LocalDateTime start, LocalDateTime end, String category, CriteriaBuilder qb, Root<Shop> root, CriteriaQuery<Shop> cq) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(start)) {
            predicates.add(qb.greaterThanOrEqualTo(root.get("createdAt"), start));
        }

        if (Objects.nonNull(end)) {
            predicates.add(qb.lessThan(root.get("createdAt"), end));
        }

        BusinessCategory businessCategory = businessCategoryRepository.findFirstByName(category).orElse(null);

        if (Objects.nonNull(businessCategory)) {
            predicates.add(qb.equal(root.get("category"), businessCategory));
        }


        return predicates;
    }

}
