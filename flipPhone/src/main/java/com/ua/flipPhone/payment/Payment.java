package com.ua.flipPhone.payment;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;


import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.user.User;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="payment_id")
    private Integer payment_id;
    
    @Column(name="status")
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus status;
    
    @Column(name="gateway")
    @Enumerated(EnumType.ORDINAL)
    private PaymentGateway gateway;
    
    @Column(name="date")
    private Date date;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order_id;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private User client_id;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller_id;

    public Payment(){}
    
    public Payment( PaymentStatus status, PaymentGateway gateway, Date date, Order order_id, User client_id, User seller_id) {
        this.status = status;
        this.gateway = gateway;
        this.date = date;
        this.order_id = order_id;
        this.client_id = client_id;
        this.seller_id = seller_id;
    }

    public Payment(Integer payment_id, PaymentStatus status, PaymentGateway gateway, Date date, Order order_id, User client_id, User seller_id) {
        this.payment_id = payment_id;
        this.status = status;
        this.gateway = gateway;
        this.date = date;
        this.order_id = order_id;
        this.client_id = client_id;
        this.seller_id = seller_id;
    }
    
    public Integer getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Integer payment_id) {
        this.payment_id = payment_id;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentGateway getGateway() {
        return gateway;
    }

    public void setGateway(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Order getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Order order_id) {
        this.order_id = order_id;
    }

    public User getClient_id() {
        return client_id;
    }

    public void setClient_id(User client_id) {
        this.client_id = client_id;
    }

    public User getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(User seller_id) {
        this.seller_id = seller_id;
    }
    
           
}