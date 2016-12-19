package com.juhanilammi.shoppedapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laemmi on 19.12.2016.
 */

public class ShoppingList {
    private int id;
    private String name;
    private List<ShoppingItem> items;

    public ShoppingList() {
        items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShoppingItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingItem> items) {
        this.items = items;
    }

    public void addItem(ShoppingItem item) {
        items.add(item);
    }

    public ShoppingItem getItem(String item) {
        int index = items.indexOf(item);

        if (index != -1) {
            return items.get(index);
        } else return null;
    }
}
