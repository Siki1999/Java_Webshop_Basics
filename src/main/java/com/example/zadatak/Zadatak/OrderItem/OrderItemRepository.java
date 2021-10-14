package com.example.zadatak.Zadatak.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemModel, Long> {
    List<OrderItemModel> findAllByOrder_Id(long id);
}
