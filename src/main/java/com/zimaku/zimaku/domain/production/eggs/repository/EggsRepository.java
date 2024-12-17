package com.zimaku.zimaku.domain.production.eggs.repository;

import com.zimaku.zimaku.domain.production.eggs.entity.Eggs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EggsRepository extends PagingAndSortingRepository<Eggs, Long>, JpaRepository<Eggs, Long> {

}
