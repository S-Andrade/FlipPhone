
package com.ua.flipPhone.payment;

import com.ua.flipPhone.order.Order;
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
public class PaymentRepositoryTest {
    
    @Mock
    private PaymentRepository paymentRepository;
       
    private Payment payment;
    
    
    @BeforeEach
    public void setUp(){
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        Order order = new Order(new Date(), 400, client);
        User seller = new User("password", "Joana", "sadfwv", "joana@email.com", "Faro", "5687687468", UserType.PARTICULAR);
        payment = new Payment(PaymentStatus.PENDING, PaymentGateway.CREDIT_CARD, new Date(), order,  client, seller);
    }
    
    @AfterEach
    public void tearDown() {
        payment = null;
        reset(paymentRepository);
    }
    
    @Test
    public void testSave(){
        when(paymentRepository.save(payment)).thenReturn(payment);
    }
    
    @Test
    public void testFindById(){
        when(paymentRepository.findById(payment.getPayment_id())).thenReturn(Optional.of(payment));
    }
    
    @Test
    public void testFindAll(){
        List<Payment> listPayment = new ArrayList<Payment>();
        listPayment.add(payment);
        when(paymentRepository.findAll()).thenReturn(listPayment);
    }
    
    @Test
    public void testDeleteById(){
        paymentRepository.deleteById(payment.getPayment_id());
        when(paymentRepository.findById(payment.getPayment_id())).thenReturn(null);
    }
}
