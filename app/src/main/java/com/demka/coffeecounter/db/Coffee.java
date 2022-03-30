package com.demka.coffeecounter.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coffee")
public class Coffee {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public String name;

    public Double mg;

    @ColumnInfo(name = "image_path")
    public String imagePath;
}
