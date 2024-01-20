package com.shoppingWebsite.repository;

import com.shoppingWebsite.model.CustomItem;

import java.util.List;

public interface ItemRepository {
    Long createItem(CustomItem customItem);
    void updateItem(CustomItem customItem);
    void deleteItemById(Long id);
    CustomItem getItemById(Long id);
    void updateItemStock(Long itemId, Long newStock);
    List<CustomItem> getAllItems();
    List<CustomItem> getItemsByLetters(String letters);
}
