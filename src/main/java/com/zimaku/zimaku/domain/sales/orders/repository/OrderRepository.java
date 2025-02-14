package com.zimaku.zimaku.domain.sales.orders.repository;

import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import com.zimaku.zimaku.domain.sales.orders.model.Order;
import org.hibernate.type.BottomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long>, JpaRepository<Order, Long> {

    @Query(value = "SELECT o FROM orders o WHERE o.isPaid = :isPaid")
    Page<Order> findOrders(@Param("isPaid") Boolean isPaid, Pageable pageable);
}
