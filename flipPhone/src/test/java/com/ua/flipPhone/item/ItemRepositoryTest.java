
package com.ua.flipPhone.item;

import com.ua.flipPhone.admin.Admin;
import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.specifications.SearchCriteria;
import com.ua.flipPhone.specifications.SearchOperation;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserType;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ItemRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private ItemRepository itemRepository;
   
       
       
      
    
    @Test
    public void testSave(){
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
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        Item item = new Item(ItemGrade.NEW, "black" , 300.50, "v4", product, null ,seller);
        
        itemRepository.save(item);
        
        Optional<Item> result = itemRepository.findById(item.getItem_id());
        
        assertThat(Optional.of(item).equals(result));
        
    }
    
    @Test
    public void testFindById(){
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
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        Item item = new Item(ItemGrade.NEW, "black" , 300.50, "v4", product, null ,seller);
        entityManager.persistAndFlush(item);
        
        Optional<Item> result = itemRepository.findById(item.getItem_id());
        
        assertThat(Optional.of(item).equals(result));
        
        
    }
    
    @Test
    public void testFindAll(){
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
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        Item item = new Item(ItemGrade.NEW, "black" , 300.50, "v4", product, null ,seller);
        entityManager.persistAndFlush(item);
        
        List<Item> expected = new ArrayList<Item>();
        expected.add(item);
        
         List<Item> result = itemRepository.findAll();
        
        assertThat(expected.equals(result));

    }
    
    @Test
    public void testDeleteById(){
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
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        Item item = new Item(ItemGrade.NEW, "black" , 300.50, "v4", product, null ,seller);
        entityManager.persistAndFlush(item);

        itemRepository.deleteById(item.getItem_id());
        
        Optional<Item> result = itemRepository.findById(item.getItem_id());
        
        assertEquals(Optional.empty(), result);
        
    }
    
    @Test
    public void testFindByProductId(){
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
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        Item item = new Item(ItemGrade.NEW, "black" , 300.50, "v4", product, null ,seller);
        entityManager.persistAndFlush(item);
        
        
        List<Item> expected = new ArrayList<Item>();
        expected.add(item);
        
        Optional<Item> result = itemRepository.findById(item.getItem_id());

        
        assertThat(expected.equals(result));
    }
    
    @Test
    public void testFilter() {
        
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
        User seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        entityManager.persistAndFlush(seller);
        Item item = new Item(ItemGrade.NEW, "black" , 300.50, "v4", product, null ,seller);
        entityManager.persistAndFlush(item);
        
        ItemSpecification filter = new ItemSpecification();
        filter.add(new SearchCriteria("grade", ItemGrade.NEW, SearchOperation.EQUAL));
        filter.add(new SearchCriteria("color", "black", SearchOperation.MATCH));
        filter.add(new SearchCriteria("price", 500, SearchOperation.LESS_THAN_EQUAL));
        filter.add(new SearchCriteria("version", "4", SearchOperation.MATCH));
        filter.add(new SearchCriteria("productId", product, SearchOperation.EQUAL));
        filter.add(new SearchCriteria("seller_id", seller, SearchOperation.EQUAL));

        entityManager.persistAndFlush(item);
        
        List<Item> expected = new ArrayList<Item>();
        expected.add(item);
        
         List<Item> result = itemRepository.findAll(filter);
        
        assertThat(expected.equals(result));
    }
}