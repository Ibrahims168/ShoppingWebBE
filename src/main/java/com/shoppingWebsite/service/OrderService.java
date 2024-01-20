package com.shoppingWebsite.service;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.model.CustomOrder;
import java.util.List;

public interface OrderService {
    Long createOrder(CustomOrder customOrder);
    void updateOrder(CustomOrder customOrder);
    void deleteOrderById(Long orderId);

    CustomOrder getOrderById(Long orderId);
    CustomOrder getOrderByUserId(Long userId);
    CustomOrder createNewOrder(Long userId);

    void submitOrder(Long userId, Long orderId) throws Exception;
    void handleStockForOrder(CustomOrder userOrder) throws Exception ;
    List<CustomItem> getOrderItemListByOrderId(Long orderId);

}
