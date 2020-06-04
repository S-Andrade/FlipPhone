
package com.ua.flipPhone.payment;

import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserType;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class PaymentRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    
    @Autowired
    private PaymentRepository paymentRepository;
       
    
    @Test
    public void testSave(){
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("31.05.2020 21:30:30", 400, client);
        entityManager.persistAndFlush(order);
        User seller = new User("password", "Joana", "sadfwv", "joana@email.com", "Faro", "5687687468", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        List<User> sellers =new ArrayList<>();
        sellers.add(seller);
        Payment payment = new Payment(PaymentStatus.PENDING, PaymentGateway.CREDIT_CARD, "31.05.2020 21:30:30", order,  client, sellers);

        paymentRepository.save(payment);
        
        Optional<Payment> result = paymentRepository.findById(payment.getPayment_id());
        
        assertThat(Optional.of(payment).equals(result));

    }
    
    @Test
    public void testFindById(){
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("31.05.2020 21:30:30", 400, client);
        entityManager.persistAndFlush(order);
        User seller = new User("password", "Joana", "sadfwv", "joana@email.com", "Faro", "5687687468", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        List<User> sellers =new ArrayList<>();
        sellers.add(seller);
        Payment payment = new Payment(PaymentStatus.PENDING, PaymentGateway.CREDIT_CARD, "31.05.2020 21:30:30", order,  client, sellers);
        entityManager.persistAndFlush(payment);
        
        Optional<Payment> result = paymentRepository.findById(payment.getPayment_id());
        
        assertThat(Optional.of(payment).equals(result));
    }
    
    @Test
    public void testFindAll(){
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("31.05.2020 21:30:30", 400, client);
        entityManager.persistAndFlush(order);
        User seller = new User("password", "Joana", "sadfwv", "joana@email.com", "Faro", "5687687468", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        List<User> sellers =new ArrayList<>();
        sellers.add(seller);
        Payment payment = new Payment(PaymentStatus.PENDING, PaymentGateway.CREDIT_CARD, "31.05.2020 21:30:30", order,  client, sellers);
        entityManager.persistAndFlush(payment);
        
        List<Payment> expected = new ArrayList<Payment>();
        expected.add(payment);

        Optional<Payment> result = paymentRepository.findById(payment.getPayment_id());
        
        assertThat(expected.equals(result));

    }
    
    @Test
    public void testDeleteById(){
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("31.05.2020 21:30:30", 400, client);
        entityManager.persistAndFlush(order);
        User seller = new User("password", "Joana", "sadfwv", "joana@email.com", "Faro", "5687687468", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        List<User> sellers =new ArrayList<>();
        sellers.add(seller);
        Payment payment = new Payment(PaymentStatus.PENDING, PaymentGateway.CREDIT_CARD, "31.05.2020 21:30:30", order,  client, sellers);
        entityManager.persistAndFlush(payment);
        
        paymentRepository.deleteById(payment.getPayment_id());

        Optional<Payment> result = paymentRepository.findById(payment.getPayment_id());
        
        assertThat(Optional.of(payment).equals(result));
    }
}
