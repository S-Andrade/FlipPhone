
package com.ua.flipPhone.order;

import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserType;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {
    
    private Order order;
    
    private User client;
    
    private Date date;
    
    public OrderTest(){}
    
    @BeforeEach
    public void setUp() {
        client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        date = new Date();
        order = new Order(1, date, 400, client);
    }
    
    @AfterEach
    public void tearDown() {
        order = null;
        client = null;
    }
    
    @Test
    public void testGetOrder_id(){
        assertEquals(1,order.getOrder_id());
    }
    
    @Test
    public void testGetDate(){
        assertEquals(date, order.getDate());
    }
    
    @Test
    public void testGetTotal(){
        assertEquals(400, order.getTotal());
    }
    
    @Test
    public void testGetClient_id() {
       assertEquals(client, order.getClient_id());
    }
    
    @Test
    public void testSetOrder_id(){
        order.setOrder_id(3);
        assertEquals(3, order.getOrder_id());
    }
    
    @Test
    public void testSetDate(){
        order.setDate(new Date());
        assertEquals(new Date(), order.getDate());
    }
    
    @Test
    public void testSetTotal(){
        order.setTotal(200);
        assertEquals(200, order.getTotal());
    }
    
    @Test
    public void testSetClient_id() {
        User u = new User("password", "Maria", "aswfdasf", "maria@email.com", "Maia", "52346134", UserType.PROFESSIONAL);
        order.setClient_id(u);
       assertEquals(u, order.getClient_id());
    }
}