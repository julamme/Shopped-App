package com.juhanilammi.shoppedapp.model;

/**
 * Created by Laemmi on 19.12.2016.
 */

public class ShoppingItem {
    private String name;
    private int id;
    private String description;

    public ShoppingItem() {
    }

    public ShoppingItem(String name, int id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }

    public ShoppingItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
