package com.demka.coffeecounter.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {Coffee.class, Record.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CoffeeDao coffeeDao();

    public abstract RecordDao recordDao();

    private static AppDatabase INSTANCE;


    public static AppDatabase getDbInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
