package com.shoppingWebsite.service;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.model.CustomOrder;
import com.shoppingWebsite.model.CustomUser;
import com.shoppingWebsite.model.OrderStatus;
import com.shoppingWebsite.repository.ItemRepository;
import com.shoppingWebsite.repository.OrderRepository;
import com.shoppingWebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Long createOrder(CustomOrder customOrder) {
        CustomUser user = userRepository.getUserById(customOrder.getUserId());
        if (user == null) {
            return null;
        }
        if (customOrder.getItemList() == null) {
            customOrder.setItemList(new ArrayList<>());
        }
        customOrder.setTotalPrice(0.0);
        customOrder.setShippingAddress(user.getAddress());
        customOrder.setOrderDate(new Date());
        customOrder.setOrderStatus(OrderStatus.TEMP);

        Long newOrderId = orderRepository.createOrder(customOrder);
        if (newOrderId != null) {
            customOrder.setOrderId(newOrderId);
            orderRepository.updateOrder(customOrder);
        }
        return newOrderId;
    }

    @Override
    public void updateOrder(CustomOrder customOrder) {
        orderRepository.updateOrder(customOrder);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteOrderById(orderId);
    }

    @Override
    public CustomOrder getOrderById(Long orderId) {
        return orderRepository.getOrderById(orderId);
    }

    @Override
    public CustomOrder getOrderByUserId(Long userId) {
        return orderRepository.getOrderByUserId(userId);
    }

    @Override
    public CustomOrder createNewOrder(Long userId) {
        CustomOrder newOrder = new CustomOrder();
        newOrder.setUserId(userId);
        return newOrder;
    }

    @Override
    public void submitOrder(Long userId, Long orderId) throws Exception {
        CustomOrder userOrder = orderRepository.getOrderById(orderId);
        if (userOrder == null) {
            throw new Exception("Order Not Found");
        } else {
            handleStockForOrder(userOrder);
            userOrder.setOrderStatus(OrderStatus.CLOSE);
            List<CustomOrder> closedOrders = userRepository.getClosedOrdersByUserId(userId);
            closedOrders.add(userOrder);
            orderRepository.updateOrder(userOrder);
        }
    }

    @Override
    public void handleStockForOrder(CustomOrder userOrder) throws Exception {
        for (CustomItem orderItem : userOrder.getItemList()) {
            CustomItem item = itemRepository.getItemById(orderItem.getItemId());
            if (item == null) {
                throw new Exception("Item with ID " + orderItem.getItemId() + " not found in the database.");
            }
            Long itemStock = item.getItemStock();
            itemRepository.updateItemStock(orderItem.getItemId(), itemStock -1);
        }
    }

    @Override
    public List<CustomItem> getOrderItemListByOrderId(Long orderId){
        return userRepository.getOrderItemListByOrderId(orderId);
    }

}
