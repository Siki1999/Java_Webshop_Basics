package com.example.zadatak.Zadatak.OrderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItemModel> getAllOrderedItems(){
        return orderItemRepository.findAll();
    }

    public Optional<OrderItemModel> getOrderedItemById(long Id){
        return orderItemRepository.findById(Id);
    }

    public OrderItemModel updateOrderedItem(OrderItemModel item){
        return orderItemRepository.save(item);
    }

    public void deleteOrderedItemById(long Id){
        orderItemRepository.deleteById(Id);
    }

    public OrderItemModel addOrderItem(OrderItemModel item){
        return orderItemRepository.save(item);
    }

    public List<OrderItemModel> getOrderedItemsByOrderId(long id){
        return orderItemRepository.findAllByOrder_Id(id);
    }
}
