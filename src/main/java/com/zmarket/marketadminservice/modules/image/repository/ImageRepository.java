package com.zmarket.marketadminservice.modules.image.repository;

import com.zmarket.marketadminservice.modules.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}