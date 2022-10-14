package com.zmarket.marketadminservice.modules.shopproduct.service.impl;

import com.zmarket.marketadminservice.exceptions.BadRequestException;
import com.zmarket.marketadminservice.exceptions.NoContentException;
import com.zmarket.marketadminservice.exceptions.NotFoundException;
import com.zmarket.marketadminservice.modules.businesscategory.models.BusinessCategory;
import com.zmarket.marketadminservice.modules.businesscategory.repositories.BusinessCategoryRepository;
import com.zmarket.marketadminservice.modules.colour.dto.ColourDto;
import com.zmarket.marketadminservice.modules.colour.model.Colour;
import com.zmarket.marketadminservice.modules.colour.repository.ColourRepository;
import com.zmarket.marketadminservice.modules.image.model.Image;
import com.zmarket.marketadminservice.modules.image.repository.ImageRepository;
import com.zmarket.marketadminservice.modules.security.jwt.TokenProvider;
import com.zmarket.marketadminservice.modules.shop.models.Shop;
import com.zmarket.marketadminservice.modules.shop.repositories.ShopRepository;
import com.zmarket.marketadminservice.modules.shopproduct.dto.ShopProductDto;
import com.zmarket.marketadminservice.modules.shopproduct.model.ShopProduct;
import com.zmarket.marketadminservice.modules.shopproduct.repository.ShopProductRepository;
import com.zmarket.marketadminservice.modules.shopproduct.service.ShopProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShopProductServiceImpl implements ShopProductService {

    private final ShopProductRepository shopProductRepository;

    private final ShopRepository shopRepository;

    private final BusinessCategoryRepository businessCategoryRepository;

    private final ImageRepository imageRepository;

    private final ColourRepository colourRepository;

    private final TokenProvider tokenProvider;


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ShopProduct createProduct(ShopProductDto dto, Long shopId) {
        Optional<Shop> shop = shopRepository.findFirstByIdAndUserId(shopId, tokenProvider.getId());
        if(shop.isEmpty()){
            throw new NotFoundException("Shop does not exist");
        }

        BusinessCategory productCategory = businessCategoryRepository.findById(dto.getProductCategoryId()).orElse(shop.get().getCategory());


        if(Objects.isNull(dto.getImageUrls()) || dto.getImageUrls().isEmpty()){
            throw new BadRequestException("Product image is required");
        }


        Set<Image> images = saveImages(dto.getImageUrls());
        Set<Colour> colours = saveColours(dto.getColours());

        ShopProduct product = saveNewProduct(dto, shop.get(), productCategory, images, colours);

        return product;
    }


    @Override
    public ShopProduct updateProduct(ShopProductDto dto, Long productId, Long shopId) {
        Optional<Shop> shop = shopRepository.findFirstByIdAndUserId(shopId, tokenProvider.getId());
        if(shop.isEmpty()){
            throw new NotFoundException("Shop does not exist");
        }

        Optional<ShopProduct> productOptional = shopProductRepository.findFirstByShopAndId(shop.get(), productId);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product does not exist");
        }

        BusinessCategory productCategory = businessCategoryRepository.findById(dto.getProductCategoryId()).orElse(shop.get().getCategory());

        if(Objects.isNull(dto.getImageUrls()) || dto.getImageUrls().isEmpty()){
            throw new BadRequestException("Product image is required");
        }

        Set<Image> images = saveImages(dto.getImageUrls());
        Set<Colour> colours = saveColours(dto.getColours());

        ShopProduct product = productOptional.get();

        product = updateProduct(dto, productCategory, images, colours, product);
        return product;
    }



    @Override
    public ShopProduct getProductById(Long productId) {

        Optional<ShopProduct> product = shopProductRepository.findById(productId);
        if (product.isEmpty()) {
            throw new NotFoundException("Product does not exist");
        }

        return product.get();
    }


    @Override
    public Page<ShopProduct> getProductByShopId(Long shopId, LocalDate startDate, LocalDate endDate, String name, String color, String category, BigDecimal price, Pageable pageable) {
        Optional<Shop> shop = shopRepository.findById(shopId);
        if(shop.isEmpty()){
            throw new NotFoundException("Shop does not exist");
        }

        LocalDateTime start = Objects.nonNull(startDate) ? LocalDateTime.of(startDate, LocalTime.MIN) : null;
        LocalDateTime end = Objects.nonNull(startDate) ? LocalDateTime.of(endDate, LocalTime.MAX) : null;

        if (Objects.isNull(start) && Objects.isNull(end) && Objects.isNull(name) && Objects.isNull(color) && Objects.isNull(category) && Objects.isNull(price)) {
            Page<ShopProduct> products = shopProductRepository.findByShop(shop.get(), pageable);
            if(products.isEmpty()){
                throw new NoContentException();
            }

            return products;
        }

        CriteriaBuilder qb = entityManager.getCriteriaBuilder();

        CriteriaQuery<ShopProduct> cq = qb.createQuery(ShopProduct.class);

        Root<ShopProduct> root = cq.from(ShopProduct.class);

        List<Predicate> predicates = getPredicates(start, end, name, color, category, price, qb, root, cq);

        predicates.add(qb.equal(root.get("shop"), shop.get()));

        cq.select(root).where(predicates.toArray(new Predicate[]{}));

        List<ShopProduct> res = entityManager.createQuery(cq).getResultList();

        if (res.isEmpty()) {
            throw new NoContentException();
        }

        return new PageImpl<>(res, pageable, res.size());
    }

    private List<Predicate> getPredicates(LocalDateTime start, LocalDateTime end, String name, String colour, String category, BigDecimal price, CriteriaBuilder qb, Root<ShopProduct> root, CriteriaQuery<ShopProduct> cq) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(start)) {
            predicates.add(qb.greaterThanOrEqualTo(root.get("createdAt"), start));
        }

        if (Objects.nonNull(end)) {
            predicates.add(qb.lessThan(root.get("createdAt"), end));
        }

        if (Objects.nonNull(name)) {
            predicates.add(qb.equal(root.get("productName"), name));
        }

        if (Objects.nonNull(category)) {
            predicates.add(qb.equal(root.get("category"), category));
        }

        if (Objects.nonNull(price)) {
            predicates.add(qb.equal(root.get("price"), price));
        }

        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<ShopProduct> subqueryStudent = subquery.from(ShopProduct.class);
        Join<Colour, ShopProduct> subqueryCourse = subqueryStudent.join("colours");

        subquery.select(subqueryStudent.get("id")).where(qb.equal(subqueryCourse.get("name"), colour));

        predicates.add(qb.in(root.get("id")).value(subquery));

        return predicates;
    }

    @Override
    public Page<ShopProduct> getAllProducts(LocalDate startDate, LocalDate endDate, String name, String color, String category, BigDecimal price, Pageable pageable) {

        LocalDateTime start = Objects.nonNull(startDate) ? LocalDateTime.of(startDate, LocalTime.MIN) : null;
        LocalDateTime end = Objects.nonNull(startDate) ? LocalDateTime.of(endDate, LocalTime.MAX) : null;

        if (Objects.isNull(start) && Objects.isNull(end) && Objects.isNull(name) && Objects.isNull(color) && Objects.isNull(category) && Objects.isNull(price)) {
            Page<ShopProduct> products = shopProductRepository.findAll(pageable);
            if(products.isEmpty()){
                throw new NoContentException();
            }

            return products;
        }

        CriteriaBuilder qb = entityManager.getCriteriaBuilder();

        CriteriaQuery<ShopProduct> cq = qb.createQuery(ShopProduct.class);

        Root<ShopProduct> root = cq.from(ShopProduct.class);

        List<Predicate> predicates = getPredicates(start, end, name, color, category, price, qb, root, cq);

        cq.select(root).where(predicates.toArray(new Predicate[]{}));

        List<ShopProduct> res = entityManager.createQuery(cq).getResultList();

        if (res.isEmpty()) {
            throw new NoContentException();
        }

        return new PageImpl<>(res, pageable, res.size());
    }

    @Override
    public Object deleteProductById(Long productId, Long shopId) {
        Optional<Shop> shop = shopRepository.findFirstByIdAndUserId(shopId, tokenProvider.getId());
        if(shop.isEmpty()){
            throw new NotFoundException("Shop does not exist");
        }

        Optional<ShopProduct> product = shopProductRepository.findFirstByShopAndId(shop.get(), productId);
        if (product.isEmpty()) {
            throw new NotFoundException("Product does not exist");
        }
        shopProductRepository.delete(product.get());
        return new Object();
    }

    private ShopProduct saveNewProduct(ShopProductDto dto, Shop shop, BusinessCategory productCategory, Set<Image> images, Set<Colour> colours) {
        ShopProduct product = new ShopProduct();
        product.setCreatedAt(new Date());
        product.setProductName(dto.getProductName());
        product.setCategory(productCategory.getName());
        product.setColours(colours);
        product.setImages(images);
        product.setShop(shop);
        product.setPrice(dto.getPrice());
        product.setUserId(tokenProvider.getId());
        product.setDescription(dto.getDescription());
        product = shopProductRepository.save(product);
        return product;
    }

    private ShopProduct updateProduct(ShopProductDto dto, BusinessCategory productCategory, Set<Image> images, Set<Colour> colours, ShopProduct product) {
        imageRepository.deleteAll(product.getImages());
        colourRepository.deleteAll(product.getColours());

        product.setUpdatedAt(new Date());
        product.setProductName(dto.getProductName());
        product.setCategory(productCategory.getName());
        product.setColours(colours);
        product.setImages(images);
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product = shopProductRepository.save(product);
        return product;
    }

    private Set<Colour> saveColours(Set<ColourDto> dto) {
        if (Objects.isNull(dto) || dto.isEmpty()) {
            return new HashSet<>();
        }
        Set<Colour> colors = new HashSet<>();
        dto.forEach(m -> colors.add(colourRepository.save(new Colour(m.getName(), m.getCode(), new Date()))));
        return colors;
    }

    private Set<Image> saveImages(Set<String> imageUrls) {
        Set<Image> images = new HashSet<>();
        imageUrls.forEach(m -> images.add(imageRepository.save(new Image(m, new Date()))));
        return images;
    }
}
