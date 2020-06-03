
package com.ua.flipPhone.payment;

import com.google.common.base.Optional;
import com.ua.flipPhone.item.Item;
import com.ua.flipPhone.item.ItemGrade;
import com.ua.flipPhone.item.ItemRepository;
import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.order.OrderRepository;
import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.product.ProductRepository;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserRepository;
import com.ua.flipPhone.user.UserType;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PaymentRepository paymentRepository;
    
    @MockBean
    private UserRepository userRepository;
    
    @MockBean
    private OrderRepository orderRepository;

    private Payment payment;
    
    private User client;
    
    private User seller;
    
    private Order order;
        
    @BeforeEach
    public void setUp(){
        client = new User(1,"password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        order = new Order(1,"31.05.2020 21:30:30", 400, client);
        seller = new User(1,"password", "Joana", "sadfwv", "joana@email.com", "Faro", "5687687468", UserType.PARTICULAR);
        List<User> sellers =new ArrayList<>();
        sellers.add(seller);
        payment = new Payment(1,PaymentStatus.PENDING, PaymentGateway.CREDIT_CARD, "31.05.2020 21:30:30", order,  client, sellers);
    }
    
    @AfterEach
    public void tearDown() {
        payment = null;
        client = null;
        order = null;
        seller = null;
        reset(paymentRepository);
        reset(userRepository);
        reset(orderRepository);

    }
    
    
    @Test
    public void givenPayments_whenGetAllPayments_thenReturnsJsonArray() throws Exception {
                
        List<Payment> allPayment = Arrays.asList(payment);
        
        given(paymentRepository.findAll()).willReturn(allPayment);
        
        mvc.perform(get("/payment/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].gateway", is(payment.getGateway().toString())));
        verify(paymentRepository, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    public void whenPostPayment_thenCreatePayment() throws Exception {
        
        given(orderRepository.findById(order.getOrder_id())).willReturn(java.util.Optional.of(order));
        given(userRepository.findById(client.getUser_id())).willReturn(java.util.Optional.of(client));
        given(userRepository.findById(seller.getUser_id())).willReturn(java.util.Optional.of(seller));
        
        
        given(paymentRepository.save(Mockito.any())).willReturn(payment);
        
        mvc.perform(post("/payment/add?status=PENDING&gateway=CREDIT_CARD&date=31.05.2020 21:30:30&order_id=1&client_id=1&seller_id=1"))
                .andExpect(status().isOk());

        verify(paymentRepository, VerificationModeFactory.times(1)).save(Mockito.any());
    }
    
    @Test
    public void givenPayment_whenGetPaymentById_thenReturnJson() throws Exception {
        
        given(paymentRepository.findById(payment.getPayment_id())).willReturn(java.util.Optional.of(payment));
        
        mvc.perform(get("/payment/{payment_id}",payment.getPayment_id())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("gateway", is(payment.getGateway().toString())));
        verify(paymentRepository, VerificationModeFactory.times(1)).findById(payment.getPayment_id());
    }
    
    @Test
    public void testDeleteById() throws Exception {
              
        mvc.perform(MockMvcRequestBuilders.delete("/payment/delete")
                .param("payment_id", String.valueOf(payment.getPayment_id())))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetPaymentStatus() throws Exception{
        mvc.perform(get("/payment/status")
            .contentType(MediaType.ALL))
            .andExpect(status().isOk());
    }
    
    @Test
    public void testGetPaymentGateway() throws Exception{
        mvc.perform(get("/payment/gateway")
            .contentType(MediaType.ALL))
            .andExpect(status().isOk());
    }
}
