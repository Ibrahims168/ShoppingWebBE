package com.shoppingWebsite.repository;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.model.CustomOrder;
import com.shoppingWebsite.repository.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private static final String ORDER_TABLE_NAME = "ORDER_TABLE";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Long createOrder(CustomOrder customOrder) {
        String sql = "INSERT INTO " + ORDER_TABLE_NAME + " (user_id, order_date, order_address, total_price, order_status) VALUES (?,?,?,?,?)";
         jdbcTemplate.update(sql,
                customOrder.getUserId(),
                customOrder.getOrderDate(),
                customOrder.getShippingAddress(),
                customOrder.getTotalPrice(),
                customOrder.getOrderStatus().name()
        );
        return jdbcTemplate.queryForObject("SELECT MAX(order_id) FROM " + ORDER_TABLE_NAME, Long.class);

    }

    @Override
    public void updateOrder(CustomOrder customOrder) {
        String sql = "UPDATE " + ORDER_TABLE_NAME + " SET user_id=?, order_date=?, order_address=?, total_price=?, order_status=? WHERE order_id=?";
        jdbcTemplate.update(sql,
                customOrder.getUserId(),
                customOrder.getOrderDate(),
                customOrder.getShippingAddress(),
                customOrder.getTotalPrice(),
                customOrder.getOrderStatus().name(),
                customOrder.getOrderId()
        );
    }

    @Override
    public void deleteOrderById(Long orderId) {
        String sql = "DELETE " + ORDER_TABLE_NAME + " WHERE order_id=?";
        jdbcTemplate.update(sql, orderId);
    }

    @Override
    public CustomOrder getOrderById(Long orderId) {
        String sql = "SELECT * FROM " + ORDER_TABLE_NAME + " WHERE order_id=?";
        CustomOrder customOrder = jdbcTemplate.queryForObject(sql, new OrderMapper(), orderId);
        try {
            if (customOrder != null) {
                List<CustomItem> itemList = userRepository.getOrderItemListByOrderId(orderId);
                customOrder.setItemList(itemList);
            }
            return customOrder;

        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public CustomOrder getOrderByUserId(Long userId) {
        String sql = "SELECT * FROM " + ORDER_TABLE_NAME + " WHERE user_id=? AND (order_status='CLOSE' OR order_status='TEMP') ORDER BY order_id DESC LIMIT 1";
        try {
            CustomOrder customOrder = jdbcTemplate.queryForObject(sql, new OrderMapper(), userId);
            if (customOrder != null) {
                List<CustomItem> itemList = userRepository.getOrderItemListByOrderId(customOrder.getOrderId());
                customOrder.setItemList(itemList);
            }
            return customOrder;

        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }




}
