
package com.ua.flipPhone.item;

import com.ua.flipPhone.admin.Admin;
import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.specifications.SearchCriteria;
import com.ua.flipPhone.specifications.SearchOperation;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserType;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ItemSpecificationTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private ItemRepository itemRepository;
    
    
      
    
    @Test
    public void testGreater_than_Equal(){
        Admin admin = new Admin("pass","sdifg","toze@mail.com");
        entityManager.persistAndFlush(admin);
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
        entityManager.persistAndFlush(product);
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("2020.06.02 20:20:20", 400, client);
        entityManager.persistAndFlush(order);
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        Item item1 = new Item(ItemGrade.NEW, "black" , 300.50, "v4", product, order ,seller);
        entityManager.persistAndFlush(item1);
        Item item2 = new Item(ItemGrade.FUNCTIONAL, "pink" , 150.50, "v7", product, order ,seller);
        entityManager.persistAndFlush(item2);
     
        ItemSpecification spec = new ItemSpecification();
        spec.add(new SearchCriteria("price", 200, SearchOperation.GREATER_THAN_EQUAL));
        
        List<Item> expected = new ArrayList<Item>();
        expected.add(item1);
        List<Item> result = itemRepository.findAll(spec);
        
        assertThat(expected.equals(result));
        
        
        
    } 
    
    @Test
    public void testLess_than_Equal(){
        Admin admin = new Admin("pass","sdifg","toze@mail.com");
        entityManager.persistAndFlush(admin);
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
        entityManager.persistAndFlush(product);
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("2020.06.02 20:20:20", 400, client);
        entityManager.persistAndFlush(order);
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        Item item1 = new Item(ItemGrade.NEW, "black" , 300.50, "v4", product, order ,seller);
        entityManager.persistAndFlush(item1);
        Item item2 = new Item(ItemGrade.FUNCTIONAL, "pink" , 150.50, "v7", product, order ,seller);
        entityManager.persistAndFlush(item2);
     
        ItemSpecification spec = new ItemSpecification();
        spec.add(new SearchCriteria("price", 200, SearchOperation.LESS_THAN_EQUAL));
        
        List<Item> expected = new ArrayList<Item>();
        expected.add(item2);
        List<Item> result = itemRepository.findAll(spec);
        
        assertThat(expected.equals(result));
                
    } 
    
    @Test
    public void testEqual() {
        Admin admin = new Admin("pass","sdifg","toze@mail.com");
        entityManager.persistAndFlush(admin);
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
        entityManager.persistAndFlush(product);
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("2020.06.02 20:20:20", 400, client);
        entityManager.persistAndFlush(order);
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        Item item1 = new Item(ItemGrade.NEW, "black" , 300.50, "v4", product, order ,seller);
        entityManager.persistAndFlush(item1);
        Item item2 = new Item(ItemGrade.FUNCTIONAL, "pink" , 150.50, "v7", product, order ,seller);
        entityManager.persistAndFlush(item2);
     
        ItemSpecification spec = new ItemSpecification();
       spec.add(new SearchCriteria("grade", ItemGrade.NEW, SearchOperation.EQUAL));
        
        List<Item> expected = new ArrayList<Item>();
        expected.add(item1);
        List<Item> result = itemRepository.findAll(spec);
        
        assertThat(expected.equals(result));
    }
    
    @Test
    public void testMatch() {
        Admin admin = new Admin("pass","sdifg","toze@mail.com");
        entityManager.persistAndFlush(admin);
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
        entityManager.persistAndFlush(product);
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(client);
        Order order = new Order("2020.06.02 20:20:20", 400, client);
        entityManager.persistAndFlush(order);
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        Item item1 = new Item(ItemGrade.NEW, "black" , 300.50, "v4", product, order ,seller);
        entityManager.persistAndFlush(item1);
        Item item2 = new Item(ItemGrade.FUNCTIONAL, "pink" , 150.50, "v7", product, order ,seller);
        entityManager.persistAndFlush(item2);
     
        ItemSpecification spec = new ItemSpecification();
        spec.add(new SearchCriteria("color", "bla", SearchOperation.MATCH));
        
        List<Item> expected = new ArrayList<Item>();
        expected.add(item1);
        List<Item> result = itemRepository.findAll(spec);
        
        assertThat(expected.equals(result));
    }
}
