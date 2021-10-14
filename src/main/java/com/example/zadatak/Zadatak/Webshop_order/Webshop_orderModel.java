package com.example.zadatak.Zadatak.Webshop_order;

import com.example.zadatak.Zadatak.Customer.CustomerModel;
import com.example.zadatak.Zadatak.OrderItem.OrderItemModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "webshop_order")
public class Webshop_orderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = CustomerModel.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerModel customer;

    private Enum<OrderStatus> status;

    @NotNull(message = "Price HRK can't be null")
    @Min(value = 0,message = "Price must be positive")
    private float total_price_hrk;

    @NotNull(message = "Price EUR can't be null")
    @Min(value = 0,message = "Price must be positive")
    private float total_price_eur;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "order")
    private List<OrderItemModel> item;

    public Webshop_orderModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Enum<OrderStatus> getStatus() {
        return status;
    }

    public void setStatus(Enum<OrderStatus> status) {
        this.status = status;
    }

    public float getTotal_price_hrk() {
        return total_price_hrk;
    }

    public void setTotal_price_hrk(float total_price_hrk) {
        this.total_price_hrk = total_price_hrk;
    }

    public float getTotal_price_eur() {
        return total_price_eur;
    }

    public void setTotal_price_eur(float total_price_eur) {
        this.total_price_eur = total_price_eur;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}
