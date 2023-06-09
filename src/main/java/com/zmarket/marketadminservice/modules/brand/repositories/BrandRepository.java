package com.zmarket.marketadminservice.modules.brand.repositories;

import com.zmarket.marketadminservice.modules.brand.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}