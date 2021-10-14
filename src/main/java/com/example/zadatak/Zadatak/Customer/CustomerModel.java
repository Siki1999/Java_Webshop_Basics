package com.example.zadatak.Zadatak.Customer;

import com.example.zadatak.Zadatak.Webshop_order.Webshop_orderModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "customer")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Please enter first name")
    @NotNull(message = "First name can't be null")
    private String first_name;

    @NotEmpty(message = "Please enter last name")
    @NotNull(message = "Last name can't be null")
    private String last_name;

    @NotEmpty(message = "Please enter email")
    @NotNull(message = "Email can't be null")
    @Email(message = "Email must be valid")
    private String email;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
    private List<Webshop_orderModel> orders;

    public CustomerModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
