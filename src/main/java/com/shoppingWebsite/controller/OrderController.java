package com.shoppingWebsite.controller;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.model.CustomOrder;
import com.shoppingWebsite.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/public/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody CustomOrder customOrder) {
        try {
            orderService.createOrder(customOrder);
            return ResponseEntity.ok("Order Created Successfully");
        } catch (Exception e) {
            // Handle exceptions or errors appropriately
            return ResponseEntity.ok("Failed To Create Order: " + e.getMessage());
        }
    }

    @GetMapping("/{orderId}")
    public CustomOrder getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @DeleteMapping("/delete/{orderId}")
    public void deleteOrderById(@PathVariable Long orderId){
        orderService.deleteOrderById(orderId);
    }

    @PostMapping("/submitOrder/{userId}/{orderId}")
    @CrossOrigin
    public ResponseEntity<String> submitOrder(@PathVariable Long userId,@PathVariable Long orderId) {
        try {
            orderService.submitOrder(userId,orderId);
            return ResponseEntity.ok("Order Submitted Successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Or Order ID Incorrect.");
        }
    }

    @GetMapping("/getOrderItemListByOrderId/{orderId}")
    public ResponseEntity<?> getOrderItemListByOrderId(@PathVariable Long orderId) {
        try {
            List<CustomItem> customItemList = orderService.getOrderItemListByOrderId(orderId);
            return ResponseEntity.ok(customItemList);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
