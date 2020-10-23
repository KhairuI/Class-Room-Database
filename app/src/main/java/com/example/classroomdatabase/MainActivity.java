package com.example.classroomdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        adapter.setOnItemListener(new PlayerAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {

                Player player= playerList.get(position);
                Intent intent= new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("details",player);
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view) {


            }
        });
    }

    private void loadData() {

        playerList= playerDB.playerDAO().readData();
        adapter.getPlayerList(playerList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.normal_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.deleteAllId){
            playerDB.playerDAO().deleteAll();
            playerList.clear();
            adapter.notifyDataSetChanged();
        }
        else if(item.getItemId()==R.id.allTypeId){

        }



        return super.onOptionsItemSelected(item);
    }

    private void setUpDB() {
        playerDB= Room.databaseBuilder(MainActivity.this,PlayerDB.class,"player_database")
                .allowMainThreadQueries().build();
    }
}