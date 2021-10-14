package com.example.zadatak.Zadatak.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("{id}")
    public ResponseEntity<Optional<CustomerModel>> getCustomerById(@PathVariable Long id){
        Optional<CustomerModel> model = customerService.getCustomerById(id);
        if (model.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
        }
    }

    @GetMapping()
    public List<CustomerModel> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping()
    public ResponseEntity<CustomerModel> addCustomer(@RequestBody @Valid CustomerModel model){
        try {
            return new ResponseEntity<>(customerService.addCustomer(model), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable long id, @RequestBody @Valid CustomerModel newCustomer){
        Optional<CustomerModel> oldCustomer = customerService.getCustomerById(id);

        if(oldCustomer.isPresent()){
            CustomerModel customer = oldCustomer.get();
            customer.setFirst_name(newCustomer.getFirst_name());
            customer.setLast_name(newCustomer.getLast_name());
            customer.setEmail(newCustomer.getEmail());
            try {
                return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable long id){
        try {
            customerService.deleteCustomerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
