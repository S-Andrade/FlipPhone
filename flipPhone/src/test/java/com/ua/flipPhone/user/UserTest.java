package com.ua.flipPhone.user;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    
    private User user;
    
    public UserTest(){}
    
    @BeforeEach
    public void setUp() {
        user = new User(1,"password", "João", "adfqewrewq", "joao@email.com", "Porto", "52346134", "client");
    }
    
    @AfterEach
    public void tearDown() {
        user = null;
    }
            
    @Test
    public void testGetUser_id(){
        assertEquals(1, user.getUser_id());
    }
    
    @Test
    public void testGetPassword(){
        assertEquals("password", user.getPassword());
    }
    
    @Test
    public void testGetName(){
        assertEquals("João", user.getName());
    }
    
    @Test
    public void testGetSalt(){
        assertEquals("adfqewrewq", user.getSalt());
    }
    
    @Test
    public void testGetEmail(){
        assertEquals("joao@email.com", user.getEmail());
    }
    
    @Test
    public void testGetAdress(){
        assertEquals("Porto", user.getAddress());
    }
    
    @Test
    public void testGetNif(){
        assertEquals("52346134", user.getNif());
    }
    
    @Test
    public void testGetType(){
        assertEquals("client", user.getType());
    }
 
    @Test
    public void testSetUser_id(){
        user.setUser_id(2);
        assertEquals(2, user.getUser_id());
    }
    
    @Test
    public void testSetPassword(){
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }
    
    @Test
    public void testSetName(){
        user.setName("Joca");
        assertEquals("Joca", user.getName());
    }
    
    @Test
    public void testSetSalt(){
        user.setSalt("akjuwshgfd");
        assertEquals("akjuwshgfd", user.getSalt());
    }
    
    @Test
    public void testSetEmail(){
        user.setEmail("joca@email.com");
        assertEquals("joca@email.com", user.getEmail());
    }
    
    @Test
    public void testSetAdress(){
        user.setAddress("Beja");
        assertEquals("Beja", user.getAddress());
    }
    
    @Test
    public void testSetNif(){
        user.setNif("325746845364");
        assertEquals("325746845364", user.getNif());
    }
    
    @Test
    public void testSetType(){
        user.setType("seller");
        assertEquals("seller", user.getType());
    }
}