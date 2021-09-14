package com.uc.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DetailUser extends AppCompatActivity {

    TextView full_name, age, address;
    ImageButton back_button, editButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        back_button = findViewById(R.id.back_button);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);

        full_name = findViewById(R.id.full_name);
        age = findViewById(R.id.age);
        address = findViewById(R.id.address);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        String full_name_value = intent.getStringExtra("name");
        String age_value = intent.getStringExtra("age");
        String address_value = intent.getStringExtra("address");

        full_name.setText(full_name_value);
        age.setText(age_value + " years old");
        address.setText(address_value);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailUser.this, AddUser.class);

                intent.putExtra("title", "Edit User");
                intent.putExtra("id", id);
                intent.putExtra("name", full_name_value);
                intent.putExtra("age", age_value);
                intent.putExtra("address", address_value);

                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dataUser.remove(Integer.parseInt(id));
                Intent intent = new Intent(DetailUser.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

                Toast.makeText(getBaseContext(), "User Deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}