
package com.ua.flipPhone.order;

import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserType;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class OrderRepositoryTest {
    
    @Mock
    private OrderRepository orderRepository;
       
    private Order order;
    
    
    @BeforeEach
    public void setUp(){
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        order = new Order("2020.06.02 20:20:20", 400, client);
    }
    
    @AfterEach
    public void tearDown() {
        order = null;
        reset(orderRepository);
    }
    
    @Test
    public void testSave(){
        when(orderRepository.save(order)).thenReturn(order);
    }
    
    @Test
    public void testFindById(){
        when(orderRepository.findById(order.getOrder_id())).thenReturn(Optional.of(order));
    }
    
    @Test
    public void testFindAll(){
        List<Order> listOrder = new ArrayList<Order>();
        listOrder.add(order);
        when(orderRepository.findAll()).thenReturn(listOrder);
    }
    
    @Test
    public void testDeleteById(){
        orderRepository.deleteById(order.getOrder_id());
        when(orderRepository.findById(order.getOrder_id())).thenReturn(null);
    }
}
