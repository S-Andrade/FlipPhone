
package com.ua.flipPhone.item;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ItemGradeTest {
    
    @Test
    public void testEnum(){
       assertThat(ItemGrade.valueOf("NEW"), is(notNullValue()));
       assertThat(ItemGrade.valueOf("LIKE_NEW"), is(notNullValue()));
       assertThat(ItemGrade.valueOf("GOOD_STATE"), is(notNullValue()));
       assertThat(ItemGrade.valueOf("FUNCTIONAL"), is(notNullValue()));
    } 
    
    
}