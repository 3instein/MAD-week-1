package com.uc.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AddUser extends AppCompatActivity {
    TextView CreateUpdateTitle;
    Button saveData;
    ImageButton back_button;
    TextInputLayout user_full_name, user_age, user_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        back_button = findViewById(R.id.back_button);
        CreateUpdateTitle = findViewById(R.id.CreateUpdateTitle);
        saveData = findViewById(R.id.saveData);
        user_full_name = findViewById(R.id.user_full_name);
        user_age = findViewById(R.id.user_age);
        user_address = findViewById(R.id.user_address);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        CreateUpdateTitle.setText(title);
        if (title.equalsIgnoreCase("Add User")) {
            saveData.setText("Save Data");
        } else {
            saveData.setText("Update Data");
            user_full_name.getEditText().setText(intent.getStringExtra("name"));
            user_age.getEditText().setText(intent.getStringExtra("age"));
            user_address.getEditText().setText(intent.getStringExtra("address"));
        }

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.equalsIgnoreCase("Add User")) {
                    String full_name = user_full_name.getEditText().getText().toString().trim();
                    String age = user_age.getEditText().getText().toString().trim();
                    String address = user_address.getEditText().getText().toString().trim();

                    findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

                    User newUser = new User(full_name, age, address);

                    Intent intent = new Intent(AddUser.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("newUser", newUser);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getBaseContext(), "User Created!", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);
                } else {
                    String id = intent.getStringExtra("id");
                    String full_name = user_full_name.getEditText().getText().toString().trim();
                    String age = user_age.getEditText().getText().toString().trim();
                    String address = user_address.getEditText().getText().toString().trim();

                    findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

                    MainActivity.dataUser.get(Integer.parseInt(id)).setFullName(full_name);
                    MainActivity.dataUser.get(Integer.parseInt(id)).setAge(age);
                    MainActivity.dataUser.get(Integer.parseInt(id)).setAddress(address);

                    Intent resetIntent = new Intent(AddUser.this, MainActivity.class);
                    resetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(resetIntent);
                    finish();

                    Toast.makeText(getBaseContext(), "User Updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}