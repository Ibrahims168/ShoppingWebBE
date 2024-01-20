package com.shoppingWebsite.repository;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.model.CustomOrder;
import com.shoppingWebsite.model.CustomUser;
import com.shoppingWebsite.repository.mapper.ItemMapper;
import com.shoppingWebsite.repository.mapper.OrderMapper;
import com.shoppingWebsite.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String USER_TABLE_NAME = "user";
    private static final String ORDER_TABLE_NAME = "ORDER_TABLE";
    private static final String USER_FAVORITE_LIST = "USER_FAVORITE_LIST";
    private static final String USER_ORDER_ITEM_LIST = "USER_ORDER_ITEM_LIST";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long createUser(CustomUser customUser) {
        String sql = "INSERT INTO " + USER_TABLE_NAME + " (first_name, last_name, email, phone_number, address, username, password) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                customUser.getFirstName(),
                customUser.getLastName(),
                customUser.getEmail(),
                customUser.getPhoneNumber(),
                customUser.getAddress(),
                customUser.getUsername(),
                customUser.getPassword()
        );
        return jdbcTemplate.queryForObject("SELECT MAX(id) FROM " + USER_TABLE_NAME, Long.class);

    }
    @Override
    public void updateUser(CustomUser customUser) {
        String sql = "UPDATE " + USER_TABLE_NAME + " SET first_name=?, last_name=?, email=?, phone_number=?, address=?, username=?, password=? WHERE id=?";
        jdbcTemplate.update(
                sql,
                customUser.getFirstName(),
                customUser.getLastName(),
                customUser.getEmail(),
                customUser.getPhoneNumber(),
                customUser.getAddress(),
                customUser.getUsername(),
                customUser.getPassword(),
                customUser.getId()
        );
    }

    @Override
    public void deleteUserById(Long id) {
        try {
            jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");

            String deleteOrderListSql = "DELETE FROM " + USER_ORDER_ITEM_LIST + " WHERE user_id=?";
            jdbcTemplate.update(deleteOrderListSql, id);

            String deleteFavoriteListSql = "DELETE FROM " + USER_FAVORITE_LIST + " WHERE user_id=?";
            jdbcTemplate.update(deleteFavoriteListSql, id);

            String deleteOrderSql = "DELETE FROM " + ORDER_TABLE_NAME + " WHERE user_id=?";
            jdbcTemplate.update(deleteOrderSql, id);

            String deleteUserSql = "DELETE FROM " + USER_TABLE_NAME + " WHERE id=?";
            jdbcTemplate.update(deleteUserSql, id);
        } finally {
            jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
        }
    }


    @Override
    public CustomUser getUserById(Long id) {
        String sql = "SELECT * FROM " + USER_TABLE_NAME + " WHERE id=?";
        CustomUser customUser = jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        try {
            if (customUser != null) {
                List<CustomItem> itemList = getAllUserFavoriteItemsByUserId(id);
                customUser.setFavoriteItemList(itemList);
                List<CustomOrder> orders = getAllOrdersByUserId(id);
                customUser.setOrders(orders);
            }
            return customUser;
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }


    @Override
    public CustomUser findUserByUsername(String username) {
        String sql = "SELECT * FROM " + USER_TABLE_NAME + " WHERE username=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public CustomUser findUserByEmail(String email) {
        String sql = "SELECT * FROM " + USER_TABLE_NAME + " WHERE email=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), email);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }


    @Override
    public List<CustomItem> getAllUserFavoriteItemsByUserId(Long userId) {
        String sql = "SELECT item.* FROM USER_FAVORITE_LIST favList " +
                "JOIN item ON favList.item_id = item.item_id " +
                "WHERE favList.user_id=?";
        try {
            return jdbcTemplate.query(sql, new ItemMapper(), userId);
        } catch (EmptyResultDataAccessException error) {
            System.out.println("inside here");
            return Collections.emptyList();
        }
    }

    @Override
    public void addItemToUserFavoriteList(Long itemId, Long userId) {
        String sql = "INSERT INTO " + USER_FAVORITE_LIST + " (user_id, item_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, itemId);

    }

    @Override
    public void removeItemFromUserFavoriteList(Long itemId, Long userId) {
        String sql = "DELETE FROM " + USER_FAVORITE_LIST + " WHERE user_id=? AND item_id=?";
        jdbcTemplate.update(sql, userId, itemId);
    }

    @Override
    public List<CustomItem> getOrderItemListByOrderId(Long orderId) {
        String sql = "SELECT item.* FROM USER_ORDER_ITEM_LIST itemList " +
                "JOIN item ON itemList.item_id = item.item_id " +
                "WHERE itemList.order_id=?";
        try {
            return jdbcTemplate.query(sql, new ItemMapper(), orderId);
        } catch (EmptyResultDataAccessException error) {
            return Collections.emptyList();
        }
    }

    @Override
    public void addItemToUserOrder(Long orderId, Long itemId, Long userId) {
        String insertSql = "INSERT INTO " + USER_ORDER_ITEM_LIST + " (order_id, item_id, user_id) VALUES (?,?,?)";
        jdbcTemplate.update(insertSql, orderId, itemId, userId);
    }


    @Override
    public void removeItemFromUserOrder(Long orderId,Long itemId, Long userId) {
        String sql = "DELETE FROM " + USER_ORDER_ITEM_LIST + " WHERE order_id=? AND item_id=? AND user_id=?";
        jdbcTemplate.update(sql, orderId, itemId, userId);
    }

    @Override
    public List<CustomOrder> getAllOrdersByUserId(Long userId) {
        String sql = "SELECT * FROM " + ORDER_TABLE_NAME + " WHERE user_id=?";
        try {
            List<CustomOrder> orders = jdbcTemplate.query(sql, new OrderMapper(), userId);
            for (CustomOrder order : orders) {
                List<CustomItem> itemList = getOrderItemListByOrderId(order.getOrderId());
                order.setItemList(itemList);
            }
            return orders;
        } catch (EmptyResultDataAccessException error) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<CustomOrder> getClosedOrdersByUserId(Long userId) {
        String sql = "SELECT * FROM " + ORDER_TABLE_NAME + " WHERE user_id=? AND order_status='CLOSE'";
        try {
            List<CustomOrder> orders = jdbcTemplate.query(sql, new OrderMapper(), userId);
            for (CustomOrder order : orders) {
                List<CustomItem> itemList = getOrderItemListByOrderId(order.getOrderId());
                order.setItemList(itemList);
            }
            return orders;
        } catch (EmptyResultDataAccessException error) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<CustomOrder> getTempOrdersByUserId(Long userId) {
        String sql = "SELECT * FROM " + ORDER_TABLE_NAME + " WHERE user_id=? AND order_status='TEMP'";
        try {
            List<CustomOrder> orders = jdbcTemplate.query(sql, new OrderMapper(), userId);
            for (CustomOrder order : orders) {
                List<CustomItem> itemList = getOrderItemListByOrderId(order.getOrderId());
                order.setItemList(itemList);
            }
            return orders;
        } catch (EmptyResultDataAccessException error) {
            return Collections.emptyList();
        }
    }


}


