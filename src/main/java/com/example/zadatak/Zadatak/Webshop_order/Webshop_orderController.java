package com.example.zadatak.Zadatak.Webshop_order;

import com.example.zadatak.Zadatak.Customer.CustomerModel;
import com.example.zadatak.Zadatak.Customer.CustomerService;
import com.example.zadatak.Zadatak.OrderItem.OrderItemModel;
import com.example.zadatak.Zadatak.OrderItem.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class Webshop_orderController {
    @Autowired
    private Webshop_orderService webshop_orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("{id}")
    public ResponseEntity<Optional<Webshop_orderModel>> getOrderById(@PathVariable Long id){
        Optional<Webshop_orderModel> model = webshop_orderService.getOrderByID(id);
        if (model.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(webshop_orderService.getOrderByID(id), HttpStatus.OK);
        }
    }

    @GetMapping()
    public List<Webshop_orderModel> getAllOrders(){
        return webshop_orderService.getAllOrders();
    }

    @PutMapping("final/{id}")
    public ResponseEntity<Webshop_orderModel> updateOrder(@PathVariable Long id) {
        Optional<Webshop_orderModel> oldOrder = webshop_orderService.getOrderByID(id);

        if (oldOrder.isPresent()){
            Webshop_orderModel order = oldOrder.get();
            order.setStatus(OrderStatus.SUBMITTED);
            List<OrderItemModel> orderItems = orderItemService.getOrderedItemsByOrderId(order.getId());
            float sum = 0;
            for (OrderItemModel o : orderItems){
                sum += (o.getProduct().getPrice_hrk() * o.getQuantity());
            }
            order.setTotal_price_hrk(sum);
            String url = "https://api.hnb.hr/tecajn/v1?valuta=EUR";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object[]> exchangeHRKEUR = restTemplate.getForEntity(url, Object[].class);
            String all = exchangeHRKEUR.getBody()[0].toString();
            String value = all.substring(all.indexOf("Srednji za devize=") + 18 , all.indexOf(", Prodajni za devize"));
            order.setTotal_price_eur(order.getTotal_price_hrk() / Float.parseFloat(value.replace(",", ".")));
            try {
                return new ResponseEntity<>(webshop_orderService.updateOrder(order), HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Webshop_orderModel> addOrder(@RequestBody @Valid Webshop_orderModel model, @RequestParam long customerID){
        try {
            model.setStatus(OrderStatus.DRAFT);
            Optional<CustomerModel> customer = customerService.getCustomerById(customerID);
            model.setCustomer(customer.get());
            return new ResponseEntity<>(webshop_orderService.addOrder(model), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        try {
            webshop_orderService.deleteOrderById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
