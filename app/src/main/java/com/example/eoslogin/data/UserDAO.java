package com.example.eoslogin.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.eoslogin.model.Item;
import com.example.eoslogin.model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + UserDataBase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + UserDataBase.USER_TABLE + " WHERE userName = :username")
    User getUserByUsername(String username);

    @Insert
    void insert(Item... items);

    @Update
    void update(Item... items);

    @Delete
    void delete(Item item);

    @Query("SELECT * FROM " + UserDataBase.ITEMS_TABLE)
    List<Item> getAllItems();

    @Query("SELECT * FROM " + UserDataBase.ITEMS_TABLE + " WHERE name = :itemName")
    Item getItemByName(String itemName);
}
