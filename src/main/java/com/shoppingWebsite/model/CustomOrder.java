package com.shoppingWebsite.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomOrder {
    private Long orderId;
    private Long userId;
    private Date orderDate;
    private String shippingAddress;
    private Double totalPrice;
    private OrderStatus orderStatus;
    private List<CustomItem> itemList;
    public CustomOrder(){};
    public CustomOrder(Long orderId, Long userId, Date orderDate, String shippingAddress, Double totalPrice, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.itemList = new ArrayList<>();
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<CustomItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<CustomItem> itemList) {
        this.itemList = itemList;
    }

    public void addItemToItemList(CustomItem item){
        this.itemList.add(item);
    }




}
