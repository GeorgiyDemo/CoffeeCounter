package com.demka.coffeecounter.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.demka.coffeecounter.db.dao.CoffeeDao;
import com.demka.coffeecounter.db.dao.RecordDao;
import com.demka.coffeecounter.db.tables.Coffee;
import com.demka.coffeecounter.db.tables.Record;


@Database(entities = {Coffee.class, Record.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "db").allowMainThreadQueries().addCallback(new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    db.beginTransaction();
                    for (int i = 0; i < CoffeeData.length; i++) {
                        ContentValues value = CoffeeData.getCoffeeItem(i);
                        db.insert("coffee", SQLiteDatabase.CONFLICT_ABORT, value);
                    }
                    db.setTransactionSuccessful();
                    db.endTransaction();

                }
            }).build();
        }
        return INSTANCE;
    }

    public abstract CoffeeDao coffeeDao();

    public abstract RecordDao recordDao();
}
