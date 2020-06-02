
package com.ua.flipPhone.item;

import com.ua.flipPhone.admin.Admin;
import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.specifications.SearchCriteria;
import com.ua.flipPhone.specifications.SearchOperation;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class ItemSpecificationTest {
    
    @MockBean
    private ItemRepository itemRepository;
    
    private Item item1;
    
    private Item item2;
    
    
    @BeforeEach
    public void setUp(){
        Admin admin = new Admin("pass","sdifg","toze@mail.com");
        Product product = new Product("Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz", 
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
        Order order = new Order("2020.06.02 20:20:20", 400, client);
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        
        item1 = new Item(1,ItemGrade.NEW, "black" , 300.50, "v4", product, order ,seller);
        
        item2 = new Item(2,ItemGrade.FUNCTIONAL, "pink" , 150.50, "v7", product, order ,seller);
    }
    
    @AfterEach
    public void tearDown() {
        item1 = null;
        item2 = null;
        reset(itemRepository);
    }
    
   
    
    @Test
    public void testGreater_than_Equal(){
     
        ItemSpecification spec = new ItemSpecification();
        spec.add(new SearchCriteria("price", 200, SearchOperation.GREATER_THAN_EQUAL));
        
        List<Item> listItem = new ArrayList<Item>();
        listItem.add(item1);
        given(itemRepository.findAll(spec)).willReturn(listItem);
        
        
        
    } 
    
    @Test
    public void testLess_than_Equal(){
     
        ItemSpecification spec = new ItemSpecification();
        spec.add(new SearchCriteria("price", 200, SearchOperation.LESS_THAN_EQUAL));
        
        List<Item> listItem = new ArrayList<Item>();
        listItem.add(item2);
        given(itemRepository.findAll(spec)).willReturn(listItem);
        
    } 
    
    @Test
    public void testEqual() {
        ItemSpecification spec = new ItemSpecification();
        spec.add(new SearchCriteria("grade", ItemGrade.NEW, SearchOperation.EQUAL));
        
        List<Item> listItem = new ArrayList<Item>();
        listItem.add(item1);
        given(itemRepository.findAll(spec)).willReturn(listItem);
    }
    
    @Test
    public void testMatch() {
        ItemSpecification spec = new ItemSpecification();
        spec.add(new SearchCriteria("color", "bla", SearchOperation.MATCH));
        
        List<Item> listItem = new ArrayList<Item>();
        listItem.add(item1);
        given(itemRepository.findAll(spec)).willReturn(listItem);
    }
}
