package com.example.eoslogin.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.eoslogin.model.Item;
import com.example.eoslogin.model.User;

@Database(entities = {User.class, Item.class}, version = 1)

public abstract class UserDataBase extends RoomDatabase {
    public static final String DB_NAME = "USER_DATABASE";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String ITEMS_TABLE = "ITEMS_TABLE";

    public abstract UserDAO getUserDao();

}
