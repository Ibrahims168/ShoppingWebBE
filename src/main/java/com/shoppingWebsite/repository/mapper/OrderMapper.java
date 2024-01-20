package com.shoppingWebsite.repository.mapper;

import com.shoppingWebsite.model.CustomOrder;
import com.shoppingWebsite.model.OrderStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderMapper implements RowMapper<CustomOrder> {
    @Override
    public CustomOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomOrder customOrder = new CustomOrder();
        customOrder.setOrderId(rs.getLong("order_id"));
        customOrder.setUserId(rs.getLong("user_id"));
        customOrder.setOrderDate(rs.getDate("order_date"));
        customOrder.setShippingAddress(rs.getString("order_address"));
        customOrder.setTotalPrice(rs.getDouble("total_price"));

        String statusValue = rs.getString("order_status");
        if (statusValue != null) {
            customOrder.setOrderStatus(OrderStatus.valueOf(statusValue));
        } else {
            customOrder.setOrderStatus(OrderStatus.TEMP);
        }
        return customOrder;
    }
}

