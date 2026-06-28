package com.studi.endemik.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.studi.endemik.model.Favorit;
import com.studi.endemik.repository.EndemikRepository;
import java.util.List;

public class FavoritViewModel extends AndroidViewModel {

    private final EndemikRepository repository;

    public FavoritViewModel(@NonNull Application application) {
        super(application);
        repository = new EndemikRepository(application);
    }

    public void insert(Favorit favorit) {
        repository.insertFavorit(favorit);
    }

    public void delete(String id) {
        repository.deleteFavorit(id);
    }

    public LiveData<List<Favorit>> getAll() {
        return repository.getAllFavorit();
    }

    public void isFavorit(String id, EndemikRepository.FavoritCallback callback) {
        repository.isFavorit(id, callback);
    }
}