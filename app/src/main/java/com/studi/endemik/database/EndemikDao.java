package com.studi.endemik.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.studi.endemik.model.Endemik;
import java.util.List;

@Dao
public interface EndemikDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Endemik> endemikList);

    @Query("SELECT * FROM endemik WHERE tipe = :tipe")
    LiveData<List<Endemik>> getByTipe(String tipe);

    @Query("SELECT * FROM endemik WHERE nama LIKE '%' || :keyword || '%' OR tipe LIKE '%' || :keyword || '%'")
    LiveData<List<Endemik>> search(String keyword);

    @Query("SELECT COUNT(*) FROM endemik")
    int getCount();
}