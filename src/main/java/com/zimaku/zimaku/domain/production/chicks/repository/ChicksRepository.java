package com.zimaku.zimaku.domain.production.chicks.repository;

import com.zimaku.zimaku.domain.production.chicks.entity.Chicks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChicksRepository extends CrudRepository<Chicks, Long> {
}
