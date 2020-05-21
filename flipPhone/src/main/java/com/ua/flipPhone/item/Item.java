package com.ua.flipPhone.item;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.user.User;

@Entity
@Table(name ="item")
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="item_id")
    private Integer item_id;
    
    @Column(name="grade")
    private String grade;
    
    @Column(name="color")
    private String color;
    
    @Column(name="price")
    private Double price;
    
    @Column(name="version")
    private String version;
    
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product_id;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order_id;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller_id;

    public Item(){}
    
    public Item(String grade, String color, Double price, String version, Product product_id, Order order_id, User seller_id) {
        this.grade = grade;
        this.color = color;
        this.price = price;
        this.version = version;
        this.product_id = product_id;
        this.order_id = order_id;
        this.seller_id = seller_id;
    }
    
    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Product getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Product product_id) {
        this.product_id = product_id;
    }

    public Order getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Order order_id) {
        this.order_id = order_id;
    }

    public User getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(User seller_id) {
        this.seller_id = seller_id;
    }    
    
}