package com.zmarket.marketadminservice.modules.shop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zmarket.marketadminservice.modules.businesscategory.models.BusinessCategory;
import com.zmarket.marketadminservice.modules.market.model.Market;
import com.zmarket.marketadminservice.modules.shopproduct.model.ShopProduct;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String shopAddress;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Market market;

    @JoinColumn(nullable = false)
    @ManyToOne
    private BusinessCategory category;

    @JsonIgnore
    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER)
    private Set<ShopProduct> products;

    private String handle;

    private String facebookUrl;

    private String twitterUrl;

    private String instagramUrl;

    private Long userId;

    private Date createdAt;

    private Date updatedAt;

    private String logo;

    @JsonProperty("productCount")
    public long productCount() {
        return Objects.isNull(products) ? 0 : products.size();
    }


}
