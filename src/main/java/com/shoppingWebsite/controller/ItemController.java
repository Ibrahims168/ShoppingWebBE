package com.shoppingWebsite.controller;

import com.shoppingWebsite.model.CustomItem;
import com.shoppingWebsite.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/public/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    @CrossOrigin
    public Long createItem(@RequestBody CustomItem customItem) {
        return itemService.createItem(customItem);
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<CustomItem> getItemById(@PathVariable Long id) {
        CustomItem item = itemService.getItemById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllItems")
    @CrossOrigin
    public ResponseEntity<List<CustomItem>> getAllItems() {
        List<CustomItem> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/searchItemsByLetter")
    public ResponseEntity<List<CustomItem>> searchItemsByLetter(@RequestParam String letter) {
        System.out.println("Received letter: " + letter);
        List<CustomItem> items = itemService.getItemsByLetters(letter);
        return ResponseEntity.ok(items);
    }

}