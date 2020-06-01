
package com.ua.flipPhone.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class UserTypeTest {
    
    @Test
    public void testEnum(){
       assertThat(UserType.valueOf("PROFESSIONAL"), is(notNullValue()));
       assertThat(UserType.valueOf("PARTICULAR"), is(notNullValue()));
    } 
    
    
}
