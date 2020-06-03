
package com.ua.flipPhone.payment;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class PaymentGatewayTest {
    
    @Test
    public void testEnum(){
       assertThat(PaymentGateway.valueOf("CREDIT_CARD"), is(notNullValue()));
       assertThat(PaymentGateway.valueOf("DEBIT_CARD"), is(notNullValue()));
       assertThat(PaymentGateway.valueOf("PAYPAL"), is(notNullValue()));
       assertThat(PaymentGateway.valueOf("MBWAY"), is(notNullValue()));
    } 
    
    
}