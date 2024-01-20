package com.shoppingWebsite.controller;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.model.CustomOrder;
import com.shoppingWebsite.model.CustomUser;
import com.shoppingWebsite.model.CustomUserRequest;
import com.shoppingWebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin

@RequestMapping("api/public/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @CrossOrigin
    public ResponseEntity<?> createUser(@RequestBody CustomUserRequest customUser){
        try{
           userService.createUser(customUser);
           return ResponseEntity.ok("User Signed-up Successfully.");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody CustomUserRequest customUserRequest){
        try {
            userService.updateUser(customUserRequest.toCustomUser());
            return ResponseEntity.ok("User Updated Successfully.");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
          CustomUser customUser = userService.getUserById(id);
          return ResponseEntity.ok(customUser);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User Deleted Successfully.");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
    @GetMapping("/getUserFavoriteList/{userId}")
    @CrossOrigin
    public ResponseEntity<?> getAllUserFavoriteItemsByUserId(@PathVariable Long userId) {
        try {
            List<CustomItem> customItemList = userService.getAllUserFavoriteItemsByUserId(userId);
            return ResponseEntity.ok(customItemList);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
    @PostMapping("/addItemToFavoriteList/{itemId}/{userId}")
    @CrossOrigin
    public ResponseEntity<?> createItemAndAddToFavoriteList(@PathVariable Long itemId, @PathVariable Long userId) throws Exception {
        try {
            userService.addItemToUserFavoriteList(itemId, userId);
            return ResponseEntity.ok( "Item ID: " + itemId + " ADDED To Favorite List.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item Or User Incorrect");
        }

    }

    @DeleteMapping("/removeItemFromFavoriteList/{itemId}/{userId}")
    @CrossOrigin
    public ResponseEntity<?> removeItemFromUserFavoriteList(@PathVariable Long itemId, @PathVariable Long userId) throws Exception {
        userService.removeItemFromUserFavoriteList(itemId, userId);
        return ResponseEntity.ok( "Item ID: " + itemId + " REMOVED Successfully From Favorite List.");

    }

    @PostMapping("/addItemToUserOrder/{itemId}/{userId}")
    @CrossOrigin
    public ResponseEntity<?> addItemToUserOrder(@PathVariable Long itemId,
                                                @PathVariable Long userId) {
        try {
            userService.addItemToUserOrder(itemId,userId);
            return ResponseEntity.ok("Item ID: " + itemId + " ADDED To User Order Successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    @DeleteMapping("/removeItemFromUserOrder/{orderId}/{itemId}/{userId}")
    @CrossOrigin
    public ResponseEntity<?> deleteItemFromUserOrder(@PathVariable Long orderId,
                                                     @PathVariable Long itemId,
                                                     @PathVariable Long userId){
        try {
            userService.removeItemFromUserOrder(orderId,itemId, userId);
            return ResponseEntity.ok("Item ID: " + itemId + " Removed From Order ID: " + orderId + " Successfully.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getAllOrdersByUserId/{userId}")
    public List<CustomOrder> getAllOrdersByUserId(@PathVariable Long userId){
        return userService.getAllOrdersByUserId(userId);
    }

    @GetMapping("/getClosedOrdersByUserId/{userId}")
    public List<CustomOrder> getClosedOrdersByUserId(@PathVariable Long userId){
        return userService.getClosedOrdersByUserId(userId);
    }

    @GetMapping("/getTempOrdersByUserId/{userId}")
    public List<CustomOrder> getTempOrdersByUserId(@PathVariable Long userId){
        return userService.getTempOrdersByUserId(userId);
    }


}



