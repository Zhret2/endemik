package com.studi.endemik.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.studi.endemik.model.Favorit;
import java.util.List;

@Dao
public interface FavoritDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Favorit favorit);

    @Query("DELETE FROM favorit WHERE id = :id")
    void deleteById(String id);

    @Query("SELECT * FROM favorit")
    LiveData<List<Favorit>> getAll();

    @Query("SELECT COUNT(*) FROM favorit WHERE id = :id")
    int isFavorit(String id);
}