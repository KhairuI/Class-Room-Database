package com.example.classroomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolBar;
    private TextView toolbarTextView;
    private FloatingActionButton insertButton;
    private RecyclerView recyclerView;
    private PlayerDB playerDB;
    private PlayerAdapter adapter;
    private List<Player> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDB();

        insertButton= findViewById(R.id.insertButtonId);
        mainToolBar= findViewById(R.id.mainToolbarId);
        toolbarTextView= findViewById(R.id.toolbarTextId);
        toolbarTextView.setText("Player List");
        setSupportActionBar(mainToolBar);

        recyclerView= findViewById(R.id.recycleViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        adapter= new PlayerAdapter();
        playerList= new ArrayList<>();

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,InsertActivity.class);
                startActivity(intent);
            }
        });
        loadData();
    }

    private void loadData() {

        playerList= playerDB.playerDAO().readData();
        adapter.getPlayerList(playerList);
        recyclerView.setAdapter(adapter);
    }

    private void setUpDB() {
        playerDB= Room.databaseBuilder(MainActivity.this,PlayerDB.class,"player_database")
                .allowMainThreadQueries().build();
    }
}