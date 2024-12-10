package com.zimaku.zimaku.domain.production.chicks.repository;

import com.zimaku.zimaku.domain.production.chicks.entity.Chicks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChicksRepository extends PagingAndSortingRepository<Chicks, Long>, JpaRepository<Chicks, Long> {

}
