package com.uc.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addUser;
    RecyclerView recyclerView;
    static ArrayList<User> dataUser = new ArrayList<>();
    UserRVAdapter adapter;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUser = findViewById(R.id.addUser);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddUser.class);
                intent.putExtra("title", "Add User");
                startActivity(intent);
            }
        });

        if (getIntent().getParcelableExtra("newUser") != null) {
            User newUser = getIntent().getParcelableExtra("newUser");
            dataUser.add(newUser);
        }

        if (dataUser.isEmpty()) {
            findViewById(R.id.textView3).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.textView3).setVisibility(View.GONE);
        }

        initView();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new UserRVAdapter(dataUser);
    }

    @Override
    public void onBackPressed() {
        if (counter == 0) {
            counter++;
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        } else if (counter == 1) {
            finish();
        }
    }
}