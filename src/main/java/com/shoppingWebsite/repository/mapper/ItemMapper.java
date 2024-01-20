package com.shoppingWebsite.repository.mapper;

import com.shoppingWebsite.model.CustomItem;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ItemMapper implements RowMapper<CustomItem> {
    @Override
    public CustomItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long itemId = resultSet.getLong("item_id");
        String itemTitle = resultSet.getString("item_title");
        String itemImg = resultSet.getString("item_img");
        Double itemPrice = resultSet.getDouble("item_price");
        Long itemStock = resultSet.getLong("item_stock");

        return new CustomItem(itemId, itemTitle, itemImg, itemPrice, itemStock);
    }
}