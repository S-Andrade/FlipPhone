
package com.ua.flipPhone.payment;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class PaymentStatusTest {
    
    @Test
    public void testEnum(){
       assertThat(PaymentStatus.valueOf("PENDING"), is(notNullValue()));
       assertThat(PaymentStatus.valueOf("RECEIVED"), is(notNullValue()));
       assertThat(PaymentStatus.valueOf("SENT"), is(notNullValue()));
    } 
    
    
}