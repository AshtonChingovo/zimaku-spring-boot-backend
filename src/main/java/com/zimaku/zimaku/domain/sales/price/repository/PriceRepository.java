package com.zimaku.zimaku.domain.sales.price.repository;

import com.zimaku.zimaku.domain.sales.price.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> findFirstByOrderByIdDesc();
}
