package com.studi.endemik.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.studi.endemik.model.Endemik;
import com.studi.endemik.repository.EndemikRepository;
import java.util.List;

public class EndemikViewModel extends AndroidViewModel {

    private final EndemikRepository repository;

    public EndemikViewModel(@NonNull Application application) {
        super(application);
        repository = new EndemikRepository(application);
    }

    public void fetchAndSave(Runnable onSuccess, Runnable onError) {
        repository.fetchAndSave(onSuccess, onError);
    }

    public LiveData<List<Endemik>> getByTipe(String tipe) {
        return repository.getByTipe(tipe);
    }

    public LiveData<List<Endemik>> search(String keyword) {
        return repository.search(keyword);
    }
}