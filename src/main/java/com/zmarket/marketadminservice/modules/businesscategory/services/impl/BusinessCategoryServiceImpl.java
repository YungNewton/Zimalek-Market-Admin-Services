package com.zmarket.marketadminservice.modules.businesscategory.services.impl;

import com.zmarket.marketadminservice.exceptions.NotFoundException;
import com.zmarket.marketadminservice.modules.businesscategory.models.BusinessCategory;
import com.zmarket.marketadminservice.modules.businesscategory.repositories.BusinessCategoryRepository;
import com.zmarket.marketadminservice.modules.businesscategory.services.BusinessCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusinessCategoryServiceImpl implements BusinessCategoryService {

    private final BusinessCategoryRepository businessCategoryRepository;

    @Override
    public BusinessCategory getById(Long id) {
        Optional<BusinessCategory> optionalBusiness = businessCategoryRepository.findById(id);
        if (optionalBusiness.isEmpty()){
            throw new NotFoundException("Category with id not found");
        }
        return optionalBusiness.get();
    }

    @Override
    public List<BusinessCategory> getAll() {
        return businessCategoryRepository.findAll();
    }


}
