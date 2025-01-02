package com.zimaku.zimaku.domain.hatchery.receiveStock.repository;

import com.zimaku.zimaku.domain.hatchery.receiveStock.entity.HatcheryStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HatcheryRepository extends PagingAndSortingRepository<HatcheryStock, Long>, JpaRepository<HatcheryStock, Long> {

    @Modifying
    @Query("UPDATE hatchery_stock SET quantity = :quantity WHERE id = :id")
    void updateHatcheryStockQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);

}
