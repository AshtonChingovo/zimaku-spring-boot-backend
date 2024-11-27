package com.zimaku.zimaku.domain.production.dispatch.repository;

import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchRepository extends CrudRepository<Dispatch, Long> {
}
