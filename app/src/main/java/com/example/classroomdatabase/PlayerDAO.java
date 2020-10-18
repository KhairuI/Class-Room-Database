package com.example.classroomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Insert
    public long insertData(Player player);

    @Query("SELECT * FROM  player_table")
    public List<Player> readData();




}
