package com.shoppingWebsite.repository;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.model.CustomOrder;
import com.shoppingWebsite.model.OrderStatus;

import java.util.List;

public interface OrderRepository {
    Long createOrder(CustomOrder customOrder);
    void updateOrder(CustomOrder customOrder);
    void deleteOrderById(Long orderId);
    CustomOrder getOrderById(Long orderId);
    CustomOrder getOrderByUserId(Long userId);

}
