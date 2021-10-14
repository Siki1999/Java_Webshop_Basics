package com.example.zadatak.Zadatak.Product;

import com.example.zadatak.Zadatak.OrderItem.OrderItemModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, length = 10)
    @Size(min=10, max=10, message = "Code must be 10 characters")
    @NotNull(message = "Code can't be null")
    @NotEmpty(message = "Please enter code")
    private String code;

    @NotNull(message = "Name can't be null")
    @NotEmpty(message = "Please enter name")
    private String name;

    @NotNull(message = "Price can't be null")
    @Min(value = 0,message = "Price must be positive")
    private float price_hrk;

    @NotNull(message = "Description can't be null")
    @NotEmpty(message = "Please enter description")
    private String description;

    @NotNull(message = "Is available can't be null")
    private boolean is_available;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "product")
    private OrderItemModel item;

    public ProductModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice_hrk() {
        return price_hrk;
    }

    public void setPrice_hrk(float price_hrk) {
        this.price_hrk = price_hrk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }
}
