
package com.ua.flipPhone.item;

import com.ua.flipPhone.admin.Admin;
import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.product.Product;
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
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class ItemRepositoryTest {
    
    @Mock
    private ItemRepository itemRepository;
       
    private Item item;
    
    private Product product;
    
    private Admin admin;
       
    @BeforeEach
    public void setUp(){
        product = new Product("Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz", 
                                        "4 GB", 
                                        "300DPI", 
                                        "6,5 \"", 
                                        "sAMOLED Infinity-O FHD+ tátil capacitivo com multi-touch", 
                                        "Li-Ion 4000 mAh", 
                                        "Android 10.0", 
                                        "32.0 MP\n f/2.2",
                                        "f/2.0 Principal + f/2.2 Ultra Grande Angular + f/2.2 Profundidade + f/2.4 Macro",
                                        "Smartphone Samsung Galaxy A51 - A515F",
                                        "url/image", admin);
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        Order order = new Order(new Date(), 400, client);
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        
        item = new Item(1,ItemGrade.NEW, "black" , 300.50, "v4", product, order ,seller);
    }
    
    @AfterEach
    public void tearDown() {
        item = null;
        product =null;
        admin = null;
    }
    
    @Test
    public void testSave(){
        when(itemRepository.save(item)).thenReturn(item);
    }
    
    @Test
    public void testFindById(){
        when(itemRepository.findById(item.getItem_id())).thenReturn(Optional.of(item));
    }
    
    @Test
    public void testFindAll(){
        List<Item> listItem = new ArrayList<Item>();
        listItem.add(item);
        when(itemRepository.findAll()).thenReturn(listItem);
    }
    
    @Test
    public void testDeleteById(){
        itemRepository.deleteById(item.getItem_id());
        when(itemRepository.findById(item.getItem_id())).thenReturn(null);
    }
    
    @Test
    public void testFindByProductId(){
        List<Item> listItem = new ArrayList<Item>();
        listItem.add(item);
        when(itemRepository.findByProductId(product)).thenReturn(listItem);
    }
}
