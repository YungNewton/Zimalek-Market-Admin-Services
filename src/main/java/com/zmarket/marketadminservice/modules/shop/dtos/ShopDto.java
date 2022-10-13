package com.zmarket.marketadminservice.modules.shop.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShopDto implements Serializable {
    private String name;
    private String shopAddress;
    private Long marketId;
    private Long businessCategoryId;
    private String handle;
    private String facebookUrl;
    private String twitterUrl;
    private String instagramUrl;
    private String logo;
}
