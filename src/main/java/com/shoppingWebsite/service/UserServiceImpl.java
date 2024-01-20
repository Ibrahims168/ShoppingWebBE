package com.shoppingWebsite.service;

import com.shoppingWebsite.model.*;
import com.shoppingWebsite.repository.ItemRepository;
import com.shoppingWebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderService orderService;

    @Override
    public Long createUser(CustomUserRequest customUserRequest) throws Exception {
        CustomUser existingCustomUser = userRepository.findUserByUsername(customUserRequest.getUsername());
        if(existingCustomUser != null){
            throw new Exception("Username " + customUserRequest.getUsername() + " is already taken");
        }
        CustomUser existingCustomUserEmail = userRepository.findUserByEmail(customUserRequest.getEmail());
        if(existingCustomUserEmail != null){
            throw new Exception("Email " + customUserRequest.getEmail() + " already have account");
        }
       return userRepository.createUser(customUserRequest.toCustomUser());
    }

    @Override
    public void updateUser(CustomUser customUser) throws Exception {
        Long userId = customUser.getId();
        CustomUser existingUser = userRepository.getUserById(userId);
        if (existingUser != null) {
            userRepository.updateUser(customUser);
        } else {
            throw new Exception("User ID " + userId + " Does Not Exist");
        }
    }

    @Override
    public void deleteUserById(Long id) throws Exception {
        CustomUser user = userRepository.getUserById(id);
        if (user != null) {
            userRepository.deleteUserById(id);
        } else {
            throw new Exception("User ID: "+ id + " Does Not Exist") ;
        }
    }

    @Override
    public CustomUser getUserById(Long id) throws Exception{
        CustomUser user = userRepository.getUserById(id);
        if (user != null) {
            return userRepository.getUserById(id);
        } else {
            throw new Exception("User ID: "+ id + " Does Not Exist") ;
        }
    }

    @Override
    public CustomUser findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public CustomUser findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<CustomItem> getAllUserFavoriteItemsByUserId(Long userId) throws Exception {
        List<CustomItem> customItemList = userRepository.getAllUserFavoriteItemsByUserId(userId);
        if (customItemList.isEmpty()){
            throw new Exception("There Are No Items In The Favorite-List.");
        } else {
            return userRepository.getAllUserFavoriteItemsByUserId(userId);
        }
    }


    @Override
    public void addItemToUserFavoriteList(Long itemId, Long userId) throws Exception {
        CustomUser user = userRepository.getUserById(userId);
        CustomItem item = itemRepository.getItemById(itemId);

        if (user == null || item == null) {
            throw new Exception("User or Item Not Found");
        }

        List<CustomItem> favoriteItemList = user.getFavoriteItemList();
            if (item.getItemId() != null) {
                favoriteItemList.add(item);
                user.setFavoriteItemList(favoriteItemList);
                userRepository.addItemToUserFavoriteList(itemId, userId);
        }
    }


    @Override
    public void removeItemFromUserFavoriteList(Long itemId, Long userId) {
        userRepository.removeItemFromUserFavoriteList(itemId,userId);
    }


    @Override
    public void addItemToUserOrder(Long itemId, Long userId) throws Exception {
        CustomItem item = itemRepository.getItemById(itemId);
        if (item == null) {
            throw new Exception("Item ID: " + itemId + " Not Found.");
        }
       CustomOrder userOrder = orderService.getOrderByUserId(userId);

        if (item.getItemStock() > 0 ){
        if (userOrder == null || userOrder.getOrderStatus() == OrderStatus.CLOSE) {
            userOrder = orderService.createNewOrder(userId);
            Long newOrderId = orderService.createOrder(userOrder);
            userOrder.setOrderId(newOrderId);
            orderService.updateOrder(userOrder);
        }
            userOrder.addItemToItemList(item);
            userRepository.addItemToUserOrder(userOrder.getOrderId(), itemId, userId);
            orderService.updateOrder(userOrder);
        } else {
            throw new Exception("NO STOCK FOR ITEM ID: " + itemId);
        }
    }


    @Override
    public void removeItemFromUserOrder(Long orderId, Long itemId, Long userId) throws Exception {
        CustomOrder userOrder = orderService.getOrderByUserId(userId);

        if (userOrder.getOrderStatus() == OrderStatus.TEMP) {
            CustomItem itemToRemove = itemRepository.getItemById(itemId);
            if (itemToRemove == null) {
                throw new Exception("Item ID: " + itemId + " Not Found.");
            } else {
                userOrder.getItemList().remove(itemToRemove);
                userRepository.removeItemFromUserOrder(userOrder.getOrderId(), itemId, userId);
                orderService.updateOrder(userOrder);

                userOrder = orderService.getOrderByUserId(userId);
                if (userOrder.getItemList().isEmpty()) {
                    orderService.deleteOrderById(orderId);
                }
            }
        } else {
            throw new Exception("Can't Remove Item From CLOSED Order.");
        }
    }

    @Override
    public List<CustomOrder> getAllOrdersByUserId(Long userId) {
        return userRepository.getAllOrdersByUserId(userId);
    }

    @Override
    public List<CustomOrder> getClosedOrdersByUserId(Long userId) {
        return userRepository.getClosedOrdersByUserId(userId);
    }

    @Override
    public List<CustomOrder> getTempOrdersByUserId(Long userId) {
        return userRepository.getTempOrdersByUserId(userId);
    }

}





