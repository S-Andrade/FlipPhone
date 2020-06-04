
package com.ua.flipPhone.item;

import com.ua.flipPhone.admin.Admin;
import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserType;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemTest {
    
    private Item item;
    
    private Product product;
    
    private Order order;
    
    private User seller;
    
    private Admin admin;
    
    public ItemTest(){}
    
    @BeforeEach
    public void setUp() {
        admin = new Admin(1,"password", "asdfsdfxzv", "barbara@email.com"); 
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
                                        "url/image",
                                        admin);
        User client = new User("password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", UserType.PARTICULAR);
        order = new Order("2020.06.02 20:20:20", 400, client);
        seller = new User("password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);
        
        item = new Item(1,ItemGrade.NEW, "black" , 300.50, "v4", product, order ,seller);
    }
    
    @AfterEach
    public void tearDown() {
        item =null;
        product = null;
        order = null;
        seller = null;
        admin = null;
    }
    
    @Test
    public void testGetItem_id(){
        assertEquals(1,item.getItem_id());
    }
    
    @Test
    public void testGetGrade(){
        assertEquals(ItemGrade.NEW, item.getGrade());
    }
    
    @Test
    public void testGetColor(){
        assertEquals("black", item.getColor());
    }
    
    @Test
    public void testGetPrice(){
        assertEquals(300.50, item.getPrice());
    }
    
    @Test
    public void testGetVersion(){
        assertEquals("v4", item.getVersion());
    }
    
    @Test
    public void testGetProduct_id(){
        assertEquals(product, item.getProductId());
    }
    
    @Test
    public void testGetOrder_id(){
        assertEquals(order, item.getOrder_id());
    }
    
    @Test
    public void testGetSeller_id(){
        assertEquals(seller, item.getSeller_id());
    }
    
    @Test
    public void testSetItem_id(){
        item.setItem_id(2);
        assertEquals(2,item.getItem_id());
    }
    
    @Test
    public void testSetGrade(){
        item.setGrade(ItemGrade.GOOD_STATE);
        assertEquals(ItemGrade.GOOD_STATE,item.getGrade());
    }
 
    @Test
    public void testSetColor(){
        item.setColor("pink");
        assertEquals("pink",item.getColor());
    }
    
    @Test
    public void testSetPrice(){
        item.setPrice(400.0);
        assertEquals(400.0,item.getPrice());
    }
    
    @Test
    public void testSetVersion(){
        item.setVersion("v6");
        assertEquals("v6",item.getVersion());
    }
    
    @Test
    public void testSetProduct_id(){
        Product p = new Product("Qualcomm Snapdragon 865", 
                                        "8 GB", 
                                        "300DPI", 
                                        "6,5 \"", 
                                        "Fluid AMOLED tátil capacitivo com multi-touch\n Vidro 3D Corning Gorilla Glass", 
                                        "Li-Ion 4000 mAh", 
                                        "Android 10.0", 
                                        "32.0 MP\n f/2.2",
                                        "f/2.0 Principal + f/2.2 Ultra Grande Angular + f/2.2 Profundidade + f/2.4 Macro",
                                        "Smartphone OnePlus 8",
                                        "url/image",
                                        new Admin(1,"password", "asdfaefcwedcf", "carl@email.com"));
        item.setProductId(p);
        assertEquals(p,item.getProductId());
    }
    
    @Test
    public void testSetOrder_id(){
        User client = new User("password", "Joana", "afwwefwQEF", "joana@email.com", "Cucujães", "2544748", UserType.PARTICULAR);
        Order o;
        o = new Order("2020.06.02 20:20:20", 500, client);
        item.setOrder_id(o);
        assertEquals(o,item.getOrder_id());
    }
    
    @Test
    public void testSetSeller_id(){
        User s = new User("password", "Joaquim", "adsfqe", "joaquim@email.com", "Coimbra", "14447747", UserType.PARTICULAR);
        item.setSeller_id(s);
        assertEquals(s,item.getSeller_id());
    }
}