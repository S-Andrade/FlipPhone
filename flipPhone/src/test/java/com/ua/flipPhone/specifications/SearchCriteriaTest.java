
package com.ua.flipPhone.specifications;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SearchCriteriaTest {
    
    private SearchCriteria sc;
    
    private SearchCriteria scinicial;
    
       
    @BeforeEach
    public void setUp() {
        sc = new SearchCriteria("color","pink", SearchOperation.MATCH);
        scinicial = new SearchCriteria();
    }
    
    @AfterEach
    public void tearDown() {
        sc = null;
    }
    
    @Test
    public void testGetKey(){
        assertEquals("color", sc.getKey());
    }
    
    @Test
    public void testGetValue(){
        assertEquals("pink", sc.getValue());
    }
    
    @Test
    public void testGetOperation(){
        assertEquals(SearchOperation.MATCH, sc.getOperation());
    }
    
   
    @Test
    public void testSetKey(){
        sc.setKey("price");
        assertEquals("price",sc.getKey());
    }
    
    @Test
    public void testSetValue(){
        sc.setValue(500);
        assertEquals(500,sc.getValue());
    }
    
    @Test
    public void testSetOperation(){
        sc.setOperation(SearchOperation.EQUAL);
        assertEquals(SearchOperation.EQUAL,sc.getOperation());
    }
    
    
}
