package com.example.eoslogin.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.eoslogin.data.UserDataBase;

import java.util.Objects;

@Entity(tableName = UserDataBase.ITEMS_TABLE)
public class Item {

    private String name;
    private int quantity;
    private double price;

    @PrimaryKey(autoGenerate = true)
    private int itemId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getQuantity() == item.getQuantity() &&
                Double.compare(item.getPrice(), getPrice()) == 0 &&
                getItemId() == item.getItemId() &&
                getName().equals(item.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getQuantity(), getPrice(), getItemId());
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", itemId=" + itemId +
                '}';
    }
}
