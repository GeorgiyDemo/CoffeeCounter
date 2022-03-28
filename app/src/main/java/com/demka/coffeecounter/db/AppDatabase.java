package com.demka.coffeecounter.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Coffee.class, Record.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CoffeeDao coffeeDao();

    public abstract RecordDao recordDao();

    private static AppDatabase INSTANCE;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("INSERT INTO coffee (name, mg, image_path) VALUES ('Lazy eye', 112.4, 'test');");

        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            /*
            database.execSQL("ALTER TABLE Book "
                    + " ADD COLUMN pub_year INTEGER");

             */
        }
    };

    public static AppDatabase getDbInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "db")
                   .addMigrations(MIGRATION_1_2, MIGRATION_2_3).allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
