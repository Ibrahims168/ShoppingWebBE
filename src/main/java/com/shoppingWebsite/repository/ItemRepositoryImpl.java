package com.shoppingWebsite.repository;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.repository.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository{
    private static final String ITEM_TABLE_NAME = "item";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Long createItem(CustomItem customItem) {
        String sql = "INSERT INTO " + ITEM_TABLE_NAME + " (item_title, item_img, item_price, item_stock) values (?,?,?,?)";
        jdbcTemplate.update(
                sql,
                customItem.getItemTitle(),
                customItem.getItemImg(),
                customItem.getItemPrice(),
                customItem.getItemStock()
        );
        return jdbcTemplate.queryForObject("SELECT MAX(item_id) FROM " + ITEM_TABLE_NAME, Long.class);
    }

    @Override
    public void updateItem(CustomItem customItem) {
        String sql = "UPDATE " + ITEM_TABLE_NAME + " SET item_title=?, item_img=?, item_price=?, item_stock=?  WHERE item_id=?";
        jdbcTemplate.update(
                sql,
                customItem.getItemTitle(),
                customItem.getItemImg(),
                customItem.getItemPrice(),
                customItem.getItemStock(),
                customItem.getItemId()
        );
    }

    @Override
    public void deleteItemById(Long id) {
        String sql = "DELETE " + ITEM_TABLE_NAME + " WHERE item_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public CustomItem getItemById(Long itemId) {
        try {
            String sql = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE item_id=?";
            return jdbcTemplate.queryForObject(sql, new ItemMapper(), itemId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateItemStock(Long itemId, Long newStock) {
        String sql = "UPDATE " + ITEM_TABLE_NAME + " SET item_stock = ? WHERE item_id = ?";
        jdbcTemplate.update(sql, newStock, itemId);
    }

    @Override
    public List<CustomItem> getAllItems() {
        String sql = "SELECT * FROM " + ITEM_TABLE_NAME ;
        return jdbcTemplate.query(sql, new ItemMapper());
    }

    @Override
    public List<CustomItem> getItemsByLetters(String letters) {
        String sql = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE LOWER(item_title) LIKE LOWER(?)";
        String searchTerm = letters + "%";
        return jdbcTemplate.query(sql, new ItemMapper(), searchTerm);
    }

}
