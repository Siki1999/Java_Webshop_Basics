package com.example.zadatak.Zadatak.OrderItem;

import com.example.zadatak.Zadatak.Product.ProductModel;
import com.example.zadatak.Zadatak.Webshop_order.Webshop_orderModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "orderitem")
public class OrderItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Webshop_orderModel.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Webshop_orderModel order;

    @OneToOne(targetEntity = ProductModel.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductModel product;

    @NotNull(message = "Ordered quantity can't be null")
    private int quantity;

    public OrderItemModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Webshop_orderModel getOrder() {
        return order;
    }

    public void setOrder(Webshop_orderModel order) {
        this.order = order;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
