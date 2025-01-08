package com.zimaku.zimaku.domain.production.eggs.repository;

import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EggsStockRepository extends PagingAndSortingRepository<EggsStock, Long>, JpaRepository<EggsStock, Long> {

    @Query(value = "SELECT e FROM EggsStock e WHERE e.isDispatched != true")
    Page<EggsStock> findEggsNotDispatched(Pageable pageable);

/*    @Modifying
    @Query(value = "UPDATE EggsStock SET isDispatched = true WHERE id = :id")
    void updateDispatchStatus(@Param("id") Long id);*/

}
