package com.zimaku.zimaku.domain.production.eggs.repository;

import com.zimaku.zimaku.domain.production.eggs.entity.Eggs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EggsRepository extends CrudRepository<Eggs, Long> {

}
