package com.zmarket.marketadminservice.modules.shopproduct.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zmarket.marketadminservice.modules.colour.model.Colour;
import com.zmarket.marketadminservice.modules.image.model.Image;
import com.zmarket.marketadminservice.modules.shop.models.Shop;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "shop_products")
@Getter
@Setter
public class ShopProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private BigDecimal price;

    private Long userId;

    private String category;

    private String description;

    @ManyToOne
    private Shop shop;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "shop_product_image",
            joinColumns = @JoinColumn(name = "shop_product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "shop_product_colour",
            joinColumns = @JoinColumn(name = "shop_product_id"),
            inverseJoinColumns = @JoinColumn(name = "colour_id"))
    private Set<Colour> colours;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
