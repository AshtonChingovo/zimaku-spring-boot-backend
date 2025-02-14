package com.zimaku.zimaku.domain.production.dispatch.repository;

import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchRepository extends PagingAndSortingRepository<Dispatch, Long>, JpaRepository<Dispatch, Long> {

    @Query("SELECT d FROM Dispatch d WHERE d.id NOT IN (SELECT h.dispatch.id FROM HatcheryStock h WHERE h.dispatch IS NOT NULL)")
    Page<Dispatch> findDispatchesNotInHatchery(Pageable page);

}
