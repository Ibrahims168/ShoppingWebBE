package com.shoppingWebsite.service;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.repository.ItemRepository;
import com.shoppingWebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Long createItem(CustomItem customItem) {
        return itemRepository.createItem(customItem);
    }

    @Override
    public void updateItem(CustomItem customItem) {}

    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteItemById(id);
    }

    @Override
    public CustomItem getItemById(Long id) {
        return itemRepository.getItemById(id);
    }

    @Override
    public void updateItemStock(Long itemId, Long newStock) {
        itemRepository.updateItemStock(itemId, newStock);
    }

    @Override
    public List<CustomItem> getAllItems() {
        return itemRepository.getAllItems();
    }

    @Override
    public List<CustomItem> getItemsByLetters(String letters) {
        return itemRepository.getItemsByLetters(letters);
    }

}
