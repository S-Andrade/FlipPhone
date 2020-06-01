
package com.ua.flipPhone.order;

import com.google.common.base.Optional;
import com.ua.flipPhone.admin.Admin;
import com.ua.flipPhone.item.Item;
import com.ua.flipPhone.item.ItemGrade;
import com.ua.flipPhone.item.ItemRepository;
import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.product.ProductRepository;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserRepository;
import com.ua.flipPhone.user.UserType;
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
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderRepository orderRepository;
    
    @MockBean
    private UserRepository userRepository;
    
    @MockBean
    private ItemRepository itemRepository;
    
    @MockBean
    private ProductRepository productRepository;
    
    private Order order;
    
    private User client;
    
    private User seller;
    
    private Item item;
    
    private Product product;
    
    private Admin admin;
        
    @BeforeEach
    public void setUp(){
        Product product = new Product(1,"Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz", 
                                        "4 GB", 
                                        "300DPI", 
                                        "6,5 \"", 
                                        "sAMOLED Infinity-O FHD+ tátil capacitivo com multi-touch", 
                                        "Li-Ion 4000 mAh", 
                                        "Android 10.0", 
                                        "32.0 MP\n f/2.2",
                                        "f/2.0 Principal + f/2.2 Ultra Grande Angular + f/2.2 Profundidade + f/2.4 Macro",
                                        "Smartphone Samsung Galaxy A51 - A515F",
                                        "url/image",admin);
        client = new User(1,"password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        order = new Order(1,new Date(), 400, client);
        seller = new User(1,"password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        
        item = new Item(1,ItemGrade.NEW, "black" , 300.50, "v4", product, order ,seller);
        
        Date date = new Date(2020,5,31,21,30,30);
        order = new Order(1,date, 400, client);
    }
    
    @AfterEach
    public void tearDown() {
        order = null;
        client = null;
        seller = null;
        item = null;
        product = null;
        admin = null;
        reset(orderRepository);
        reset(userRepository);
        reset(itemRepository);
        reset(productRepository);

    }
    
    
    @Test
    public void givenOrders_whenGetAllOrders_thenReturnsJsonArray() throws Exception {
                
        List<Order> allOrder = Arrays.asList(order);
        
        given(orderRepository.findAll()).willReturn(allOrder);
        
        mvc.perform(get("/order/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].total", is(order.getTotal())));
        verify(orderRepository, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    public void whenPostOrder_thenCreateOrder() throws Exception {
        given(itemRepository.findById(item.getItem_id())).willReturn(java.util.Optional.of(item));

        given(userRepository.findById(client.getUser_id())).willReturn(java.util.Optional.of(client));
        
        given(orderRepository.save(Mockito.any())).willReturn(order);
        
        mvc.perform(post("/order/add?date=31.05.2020 21:30:30&total=400&client_id=1&item_id=1"))
                .andExpect(status().isOk());

        verify(orderRepository, VerificationModeFactory.times(1)).save(Mockito.any());
    }
    
    @Test
    public void givenOrder_whenGetOrderById_thenReturnJson() throws Exception {
        
        given(orderRepository.findById(order.getOrder_id())).willReturn(java.util.Optional.of(order));
        
        mvc.perform(get("/order/{order_id}",order.getOrder_id())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("total", is(order.getTotal())));
        verify(orderRepository, VerificationModeFactory.times(1)).findById(order.getOrder_id());
    }
    
    @Test
    public void testDeleteById() throws Exception {
              
        mvc.perform(MockMvcRequestBuilders.delete("/order/delete")
                .param("order_id", String.valueOf(order.getOrder_id())))
                .andExpect(status().isOk());
    }
    
    @Test
    public void whenPostItemAndOrder_UpdateItem() throws Exception {
        given(itemRepository.findById(item.getItem_id())).willReturn(java.util.Optional.of(item));

        given(orderRepository.findById(order.getOrder_id())).willReturn(java.util.Optional.of(order));
        
        given(itemRepository.save(Mockito.any())).willReturn(item);
        
        mvc.perform(post("/order/addItem?item_id=1&order_id=1"))
                .andExpect(status().isOk());

        verify(itemRepository, VerificationModeFactory.times(1)).save(Mockito.any());
    }
    
}
