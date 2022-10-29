package com.zmarket.marketadminservice.modules.market.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zmarket.marketadminservice.modules.image.model.Image;
import com.zmarket.marketadminservice.modules.shop.models.Shop;
import com.zmarket.marketadminservice.modules.states.model.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "markets")
@AllArgsConstructor
@NoArgsConstructor
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private State state;

    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "market", fetch = FetchType.EAGER)
    private Set<Shop> shops;

    @ManyToMany
    @JoinTable(
            name = "market_image",
            joinColumns = @JoinColumn(name = "market_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    private Date createdAt;

    private Date updatedAt;

    private int totalOneStarRating;

    private int totalTwoStarRating;

    private int totalThreeStarRating;

    private int totalFourStarRating;

    private int totalFiveStarRating;

    private int totalStarRating;

    private int avgStarRating;

    @JsonProperty("shopCount")
    public long shopCount() {
        return Objects.isNull(shops) ? 0 : shops.size();
    }


    @JsonProperty("productCount")
    public long productCount() {
        return Objects.isNull(shops) ? 0 : getProductCount();
    }

    private long getProductCount() {
        return shops.stream().map(Shop::productCount).reduce(0L, Long::sum);
    }

    public Market(String name, State state, String address, Date createdAt) {
        this.name = name;
        this.state = state;
        this.address = address;
        this.createdAt = createdAt;
    }
}
