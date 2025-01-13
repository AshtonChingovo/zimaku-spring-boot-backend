package com.zimaku.zimaku.domain.sales.clients.repository;

import com.zimaku.zimaku.domain.sales.clients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, UUID>, JpaRepository<Client, UUID> {
}
