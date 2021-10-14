package com.example.zadatak.Zadatak.Webshop_order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Webshop_orderRepository extends JpaRepository<Webshop_orderModel, Long> {
}
