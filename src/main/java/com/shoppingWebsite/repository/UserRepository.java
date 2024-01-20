package com.shoppingWebsite.repository;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.model.CustomOrder;
import com.shoppingWebsite.model.CustomUser;

import java.util.List;

public interface UserRepository {
    Long createUser(CustomUser customUser);
    void updateUser(CustomUser customUser);
    void deleteUserById(Long id);
    CustomUser getUserById(Long id);

    CustomUser findUserByUsername(String username);
    CustomUser findUserByEmail(String email);

    List<CustomItem> getAllUserFavoriteItemsByUserId(Long userId);
    void addItemToUserFavoriteList(Long itemId, Long userId);
    void removeItemFromUserFavoriteList(Long itemId, Long userId);

    List<CustomItem> getOrderItemListByOrderId(Long orderId);
    void addItemToUserOrder(Long orderId,Long itemId, Long userId);
    void removeItemFromUserOrder(Long orderId, Long itemId, Long userId);
    List<CustomOrder> getAllOrdersByUserId(Long userId);
    List<CustomOrder> getClosedOrdersByUserId(Long userId);
    List<CustomOrder> getTempOrdersByUserId(Long userId);


}
