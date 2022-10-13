package com.zmarket.marketadminservice.modules.states.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zmarket.marketadminservice.modules.market.model.Market;
import com.zmarket.marketadminservice.modules.shop.models.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "state", fetch = FetchType.EAGER)
    private Set<Market> shops;

    private Date createdAt;

    private Date updatedAt;

    public State(String name, boolean enabled, Date createdAt) {
        this.name = name;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }
}
