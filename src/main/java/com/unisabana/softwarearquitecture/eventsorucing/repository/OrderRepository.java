package com.unisabana.softwarearquitecture.eventsorucing.repository;

import com.unisabana.softwarearquitecture.eventsorucing.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
