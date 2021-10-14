package com.example.zadatak.Zadatak.Webshop_order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Webshop_orderService {
    @Autowired
    private Webshop_orderRepository webshop_orderRepository;

    public List<Webshop_orderModel> getAllOrders(){
        return webshop_orderRepository.findAll();
    }

    public Optional<Webshop_orderModel> getOrderByID(long Id){
        return webshop_orderRepository.findById(Id);
    }

    public Webshop_orderModel updateOrder(Webshop_orderModel order){
        return webshop_orderRepository.save(order);
    }

    public void deleteOrderById(long Id){
        webshop_orderRepository.deleteById(Id);
    }

    public Webshop_orderModel addOrder(Webshop_orderModel order){
        return webshop_orderRepository.save(order);
    }
}
