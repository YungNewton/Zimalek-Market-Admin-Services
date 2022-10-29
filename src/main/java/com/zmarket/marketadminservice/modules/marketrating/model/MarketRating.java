package com.zmarket.marketadminservice.modules.marketrating.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zmarket.marketadminservice.modules.market.model.Market;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "market_ratings")
@AllArgsConstructor
@NoArgsConstructor
public class MarketRating {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int starCount;

    private String comment;

    private long userId;

    private String userFullName;

    private boolean anonymous;

    @ManyToOne
    private Market market;

    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    private Date updatedAt;
}
