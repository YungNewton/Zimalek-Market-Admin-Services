package com.zmarket.marketadminservice.modules.businesscategory.services;

import com.zmarket.marketadminservice.modules.businesscategory.models.BusinessCategory;

import java.util.List;

public interface BusinessCategoryService {
    BusinessCategory getById(Long id);
    List<BusinessCategory> getAll();

}
