package com.example.zadatak.Zadatak.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerModel> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<CustomerModel> getCustomerById(long Id){
        return customerRepository.findById(Id);
    }

    public CustomerModel addCustomer(CustomerModel Customer){
        return customerRepository.save(Customer);
    }

    public CustomerModel updateCustomer(CustomerModel Customer){
        return customerRepository.save(Customer);
    }

    public void deleteCustomerById(long Id){
        customerRepository.deleteById(Id);
    }
}
