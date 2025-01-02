package com.zimaku.zimaku.domain.production.eggs.repository;

import com.zimaku.zimaku.domain.production.eggs.entity.Eggs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EggsRepository extends PagingAndSortingRepository<Eggs, Long>, JpaRepository<Eggs, Long> {

    @Query(value = "SELECT e FROM Eggs e WHERE e.isDispatched != true")
    Page<Eggs> findEggsNotDispatched(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Eggs SET isDispatched = true WHERE id = :id")
    void updateDispatchStatus(@Param("id") Integer id);

}
