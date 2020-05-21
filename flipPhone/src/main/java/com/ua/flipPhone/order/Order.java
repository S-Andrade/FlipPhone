package com.ua.flipPhone.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ua.flipPhone.user.User;

@Entity
@Table(name="order")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="order_id")
    private Integer order_id;

    @Column(name="date")
    private Date date;
    
    @Column(name="total")
    private double total;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private User client_id;

    public Order(){}
    
    public Order(Date date, double total, User client_id) {
        this.order_id = order_id;
        this.date = date;
        this.total = total;
        this.client_id = client_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getClient_id() {
        return client_id;
    }

    public void setClient_id(User client_id) {
        this.client_id = client_id;
    }
    
    
}
