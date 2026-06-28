package com.studi.endemik.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.studi.endemik.model.Endemik;
import com.studi.endemik.model.Favorit;

@Database(entities = {Endemik.class, Favorit.class}, version = 2, exportSchema = false)
public abstract class EndemikDatabase extends RoomDatabase {

    private static EndemikDatabase instance;

    public abstract EndemikDao endemikDao();
    public abstract FavoritDao favoritDao();

    public static synchronized EndemikDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    EndemikDatabase.class,
                    "endemik_database"
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}