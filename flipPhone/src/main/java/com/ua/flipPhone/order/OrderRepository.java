package com.ua.flipPhone.order;


import org.springframework.data.repository.CrudRepository;

import com.ua.flipPhone.order.Order;


public interface OrderRepository extends CrudRepository<Order, Integer> {

}