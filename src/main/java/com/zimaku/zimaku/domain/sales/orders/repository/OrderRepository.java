package com.zimaku.zimaku.domain.sales.orders.repository;

import com.zimaku.zimaku.domain.sales.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long>, JpaRepository<Order, Long> {
}
