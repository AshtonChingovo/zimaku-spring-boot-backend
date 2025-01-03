package com.zimaku.zimaku.domain.hatchery.receiveStock.repository;

import com.zimaku.zimaku.domain.hatchery.receiveStock.entity.HatcheryStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HatcheryRepository extends PagingAndSortingRepository<HatcheryStock, Long>, JpaRepository<HatcheryStock, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE HatcheryStock SET breakages = :breakages WHERE id = :id")
    void updateHatcheryStockQuantity(@Param("breakages") Integer breakages, @Param("id") Long id);

}
