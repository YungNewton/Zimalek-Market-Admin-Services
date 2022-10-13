package com.zmarket.marketadminservice.modules.businesscategory.controller;

import com.zmarket.marketadminservice.annotations.WrapApiResponse;
import com.zmarket.marketadminservice.modules.businesscategory.models.BusinessCategory;
import com.zmarket.marketadminservice.modules.businesscategory.services.BusinessCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@WrapApiResponse
@RequiredArgsConstructor
public class BusinessCategoryController {

    private final BusinessCategoryService businessCategoryService;


    @GetMapping("/{id}")
    public BusinessCategory getById(@PathVariable Long id){
        return businessCategoryService.getById(id);
    }

    @GetMapping
    public List<BusinessCategory> getAll(){
        return businessCategoryService.getAll();
    }

}
