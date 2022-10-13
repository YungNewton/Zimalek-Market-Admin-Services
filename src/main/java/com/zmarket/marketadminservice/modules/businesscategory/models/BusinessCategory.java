package com.zmarket.marketadminservice.modules.businesscategory.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zmarket.marketadminservice.modules.shop.models.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class BusinessCategory {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Shop> shops;

    @JsonIgnore
    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    private Date updatedAt;

    public BusinessCategory(String name, Date createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }
}
