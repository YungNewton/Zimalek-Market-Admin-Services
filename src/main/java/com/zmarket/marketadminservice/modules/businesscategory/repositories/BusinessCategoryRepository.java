package com.zmarket.marketadminservice.modules.businesscategory.repositories;

import com.zmarket.marketadminservice.modules.businesscategory.models.BusinessCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long> {
    Optional<BusinessCategory> findFirstByName(String name);

}