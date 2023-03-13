package com.example.marketplace.Repositories;

import com.example.marketplace.Entities.PromotionCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromotionCodeRepository extends JpaRepository<PromotionCode,Long> {
}
