
package com.ua.flipPhone.specifications;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class SearchOperationTest {
    
    @Test
    public void testEnum(){
       assertThat(SearchOperation.valueOf("GREATER_THAN_EQUAL"), is(notNullValue()));
       assertThat(SearchOperation.valueOf("LESS_THAN_EQUAL"), is(notNullValue()));
       assertThat(SearchOperation.valueOf("EQUAL"), is(notNullValue()));
       assertThat(SearchOperation.valueOf("MATCH"), is(notNullValue()));
    } 
    
    
}