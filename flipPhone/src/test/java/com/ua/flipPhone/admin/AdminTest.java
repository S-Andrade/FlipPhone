
package com.ua.flipPhone.admin;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminTest {
    
    private Admin admin;
    
    public AdminTest(){}
    
    @BeforeEach
    public void setUp() {
        admin = new Admin(1,"password", "asdfsdfxzv", "maria@email.com");
    }
    
    @AfterEach
    public void tearDown() {
        admin = null;
    }
    
    @Test
    public void testGetAdmin_id(){
        assertEquals(1,admin.getAdmin_id());
    }
    
    @Test
    public void testGetPassword(){
        assertEquals("password", admin.getPassword());
    }
    
    @Test
    public void testGetHash(){
        assertEquals("asdfsdfxzv", admin.getHash());
    }
    
    @Test
    public void testGetEmail(){
        assertEquals("maria@email.com", admin.getEmail());
    }
    
    @Test
    public void testSetAdmin_id(){
        admin.setAdmin_id(1);
        assertEquals(1,admin.getAdmin_id());
    }
    
    @Test
    public void testSetPassword(){
        admin.setPassword("newpassword");
        assertEquals("newpassword",admin.getPassword());
    }
    
    @Test
    public void testSetHash(){
        admin.setHash("dmjhgfkuaswy");
        assertEquals("dmjhgfkuaswy",admin.getHash());
    }
    
    @Test
    public void testSetEmail(){
        admin.setEmail("carla@email.com");
        assertEquals("carla@email.com",admin.getEmail());
    }
}
