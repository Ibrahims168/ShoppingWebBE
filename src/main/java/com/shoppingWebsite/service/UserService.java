package com.shoppingWebsite.service;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.model.CustomOrder;
import com.shoppingWebsite.model.CustomUser;
import com.shoppingWebsite.model.CustomUserRequest;

import java.util.List;

public interface UserService {
    Long createUser(CustomUserRequest customUser) throws Exception;
    void updateUser(CustomUser customUser) throws Exception;
    void deleteUserById(Long id) throws Exception;
    CustomUser getUserById(Long id) throws Exception;
    CustomUser findUserByUsername(String username);
    CustomUser findUserByEmail(String email);

    List<CustomItem> getAllUserFavoriteItemsByUserId(Long userId) throws Exception;
    void addItemToUserFavoriteList(Long itemId, Long userId) throws Exception;
    void removeItemFromUserFavoriteList(Long itemId, Long userId);

    void addItemToUserOrder(Long itemId, Long userId) throws Exception;
    void removeItemFromUserOrder(Long orderId, Long itemId, Long userId) throws Exception;
    List<CustomOrder> getAllOrdersByUserId(Long userId);
    List<CustomOrder> getClosedOrdersByUserId(Long userId);
    List<CustomOrder> getTempOrdersByUserId(Long userId);

}
