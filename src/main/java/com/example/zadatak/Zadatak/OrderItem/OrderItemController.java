package com.example.zadatak.Zadatak.OrderItem;

import com.example.zadatak.Zadatak.Customer.CustomerModel;
import com.example.zadatak.Zadatak.Customer.CustomerService;
import com.example.zadatak.Zadatak.Product.ProductModel;
import com.example.zadatak.Zadatak.Product.ProductService;
import com.example.zadatak.Zadatak.Webshop_order.OrderStatus;
import com.example.zadatak.Zadatak.Webshop_order.Webshop_orderModel;
import com.example.zadatak.Zadatak.Webshop_order.Webshop_orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private Webshop_orderService webshop_orderService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("{id}")
    public ResponseEntity<Optional<OrderItemModel>> getOrderItemById(@PathVariable Long id){
        Optional<OrderItemModel> model = orderItemService.getOrderedItemById(id);
        if (model.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(orderItemService.getOrderedItemById(id), HttpStatus.OK);
        }
    }

    @GetMapping()
    public List<OrderItemModel> getAllOrderedItems(){
        return orderItemService.getAllOrderedItems();
    }


    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderItemModel>> getOrderItemsByOrderId(@PathVariable long id){
        List<OrderItemModel> model = orderItemService.getOrderedItemsByOrderId(id);
        if(model.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<OrderItemModel> addOrderItem(@RequestBody OrderItemModel model,
                                                   @RequestParam long productID,
                                                   @RequestParam long orderID,
                                                   @RequestParam long customerID){
        try {
            Optional<ProductModel> product = productService.getProductByID(productID);
            Optional<Webshop_orderModel> order = webshop_orderService.getOrderByID(orderID);
            if(product.get().isIs_available()){
                model.setProduct(product.get());
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(order.isPresent()){
                model.setOrder(order.get());
            } else {
                Webshop_orderModel newOrder = new Webshop_orderModel();
                Optional<CustomerModel> customer = customerService.getCustomerById(customerID);
                newOrder.setCustomer(customer.get());
                newOrder.setStatus(OrderStatus.DRAFT);
                webshop_orderService.addOrder(newOrder);
                model.setOrder(newOrder);
            }
            return new ResponseEntity<>(orderItemService.addOrderItem(model), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderItemModel> updateOrderedItem(@PathVariable Long id, @RequestBody @Valid OrderItemModel newOrderedItem){
        Optional<OrderItemModel> oldOrderedItem = orderItemService.getOrderedItemById(id);

        if(oldOrderedItem.isPresent()){
            OrderItemModel item = oldOrderedItem.get();
            item.setQuantity(newOrderedItem.getQuantity());
            try {
                return new ResponseEntity<>(orderItemService.updateOrderedItem(item), HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderedItem(@PathVariable Long id){
        try {
            orderItemService.deleteOrderedItemById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
