package com.demka.coffeecounter.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coffee")
public class Coffee {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public Double mg;

    @ColumnInfo(name = "image_path")
    public String imagePath;
}
