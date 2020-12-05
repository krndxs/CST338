package com.example.eoslogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eoslogin.data.UserDAO;
import com.example.eoslogin.data.UserDataBase;
import com.example.eoslogin.model.User;

import java.util.List;

import static androidx.room.Room.databaseBuilder;

public class MainActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;

    private String username;
    private String password;

    private Button logIn;
    private Button signUp;

    private UserDAO userDAO;
    private List<User> users;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDatabase();
        wireUpDisplay();
        String currentDBPath=getDatabasePath("USER_DATABASE").getAbsolutePath();
        System.out.println(currentDBPath);



    }

    private void wireUpDisplay() {
        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);

        logIn = findViewById(R.id.buttonLogin);
        signUp = findViewById(R.id.buttonSignUp);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                users = userDAO.getAllUsers();
                if (username.equals("admin") && password.equals("admin")){
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                }else if (checkUsername(false)){
                    if (checkPassword()){
//                      TODO:
//                      You need to pass user as extra to next activity.
                        System.out.println("Not implemented");
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                users = userDAO.getAllUsers();
                user = new User(username, password);
                System.out.println(user);
                if (!checkUsername(true)){
                    if (!(user == null)){
                        userDAO.insert(user);
                        return;
                    }else{
                        Toast.makeText(getApplicationContext(), "ERROR USER IS NULL", Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(getApplicationContext(), "User already exist", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkPassword() {
        return user.getPassword().equals(password);
    }

    private boolean checkUsername(boolean signUp) {
        User user = userDAO.getUserByUsername(username);
        if (user == null){
            if (!signUp){
                Toast.makeText(this, "No such user", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
        return true;
    }

    private void getValuesFromDisplay() {
        username = usernameField.getText().toString();
        password = passwordField.getText().toString();
    }


    private void getDatabase() {
        userDAO = Room.databaseBuilder(this, UserDataBase.class, "User")
                .allowMainThreadQueries()
                .build()
                .getUserDao();
    }

}