package com.demka.coffeecounter.db.tables;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "coffee")
public class Coffee extends BaseEntity {

    public String name;

    public Double mg;

    @ColumnInfo(name = "image_path")
    public String imagePath;
}
