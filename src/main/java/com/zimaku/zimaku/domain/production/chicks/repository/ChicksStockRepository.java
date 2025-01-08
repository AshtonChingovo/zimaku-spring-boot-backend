package com.zimaku.zimaku.domain.production.chicks.repository;

import com.zimaku.zimaku.domain.production.chicks.entity.ChicksStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChicksStockRepository extends PagingAndSortingRepository<ChicksStock, Long>, JpaRepository<ChicksStock, Long> {

}
