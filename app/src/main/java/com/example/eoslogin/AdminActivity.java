package com.example.eoslogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eoslogin.data.UserDAO;
import com.example.eoslogin.data.UserDataBase;
import com.example.eoslogin.model.Item;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private TextView databaseView;

    private EditText itemName;
    private EditText itemQuantity;
    private EditText itemPrice;

    private Button buttonAddItem;
    private Button buttonBack;

    private UserDAO userDAO;
    private List<Item> items;
    private Item item = new Item();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        getDatabase();
        wireUpDisplay();
        refreshDisplay();
    }

    private void refreshDisplay() {
        items = userDAO.getAllItems();

        if (items.size() <= 0){
            return;
        }

        StringBuilder sd = new StringBuilder();
        for (Item item : items) {
            sd.append(item.toString());
            sd.append("\n");
            sd.append("=-=-=-=-=-=-=-=-=");
            sd.append("\n");
        }
        databaseView.setText(sd.toString());
    }

    private void wireUpDisplay() {

        databaseView = findViewById(R.id.listOfItems);

        itemName = findViewById(R.id.itemName);
        itemQuantity = findViewById(R.id.itemQuantity);
        itemPrice = findViewById(R.id.itemPrice);

        buttonAddItem = findViewById(R.id.addItem);
        buttonBack = findViewById(R.id.back);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
//                if (userDAO.getAllItems().size() > 1){
//                    if (userDAO.getItemByName(item.getName()).equals(item)) {
//                        userDAO.delete(item);
//                        item.setQuantity(1 + item.getQuantity());
//                    }
//                }
                userDAO.insert(item);
                refreshDisplay();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getValuesFromDisplay() {
        item.setName(itemName.getText().toString());
        item.setQuantity(Integer.parseInt(itemQuantity.getText().toString()));
        item.setPrice(Double.parseDouble(itemPrice.getText().toString()));
    }


    private void getDatabase() {
        userDAO = Room.databaseBuilder(this, UserDataBase.class, "User")
                .allowMainThreadQueries()
                .build()
                .getUserDao();
    }
}