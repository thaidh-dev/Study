package com.example.demo.business;

import com.example.demo.data.ItemRepository;
import com.example.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemBusinessService {
    @Autowired
    private ItemRepository repository;

    public Item retreiveHardcodedItem() {
        return new Item(1, "Ball", 10, 100);
    }

    public List<Item> retrieveAllItems() {
        List<Item> items = repository.findAll();

        for (Item i : items) {
            i.setValue(i.getPrice() * i.getQuantity());
        }

        return items;
    }
}
