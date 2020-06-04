
package com.ua.flipPhone.order;

import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserType;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class OrderRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    
    @Autowired
    private OrderRepository orderRepository;
       
     
    @Test
    public void testSave(){
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("2020.06.02 20:20:20", 400, client);
        
        orderRepository.save(order);
        Optional<Order> result = orderRepository.findById(order.getOrder_id());
        
        assertThat(Optional.of(order).equals(result));
    }
    
    @Test
    public void testFindById(){
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("2020.06.02 20:20:20", 400, client);
        entityManager.persistAndFlush(order);
        
        Optional<Order> result = orderRepository.findById(order.getOrder_id());
        
        assertThat(Optional.of(order).equals(result));
    }
    
    @Test
    public void testFindAll(){
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("2020.06.02 20:20:20", 400, client);
        entityManager.persistAndFlush(order);
        
        List<Order> expected = new ArrayList<Order>();
        expected.add(order);
        
        List<Order> result = orderRepository.findAll();
        
        assertThat(Optional.of(order).equals(result));
    }
    
    @Test
    public void testDeleteById(){
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("2020.06.02 20:20:20", 400, client);
        entityManager.persistAndFlush(order);
        
        orderRepository.deleteById(order.getOrder_id());

        Optional<Order> result = orderRepository.findById(order.getOrder_id());
        
        assertEquals(Optional.empty(), result);
    }
}
