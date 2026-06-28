package com.studi.endemik.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.studi.endemik.api.ApiClient;
import com.studi.endemik.database.EndemikDatabase;
import com.studi.endemik.database.EndemikDao;
import com.studi.endemik.database.FavoritDao;
import com.studi.endemik.model.Endemik;
import com.studi.endemik.model.Favorit;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndemikRepository {

    private final EndemikDao endemikDao;
    private final FavoritDao favoritDao;
    private final ExecutorService executor;

    public EndemikRepository(Application application) {
        EndemikDatabase db = EndemikDatabase.getInstance(application);
        endemikDao = db.endemikDao();
        favoritDao = db.favoritDao();
        executor = Executors.newFixedThreadPool(4);
    }

    public void fetchAndSave(Runnable onSuccess, Runnable onError) {
        executor.execute(() -> {
            int count = endemikDao.getCount();
            if (count > 0) {
                if (onSuccess != null) onSuccess.run();
                return;
            }
            ApiClient.getApiService().getEndemik().enqueue(new Callback<List<Endemik>>() {
                @Override
                public void onResponse(Call<List<Endemik>> call, Response<List<Endemik>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        executor.execute(() -> {
                            endemikDao.insertAll(response.body());
                            if (onSuccess != null) onSuccess.run();
                        });
                    } else {
                        if (onError != null) onError.run();
                    }
                }

                @Override
                public void onFailure(Call<List<Endemik>> call, Throwable t) {
                    android.util.Log.e("ENDEMIK", "onFailure: " + t.getMessage());
                    if (onError != null) onError.run();
                }
            });
        });
    }

    public LiveData<List<Endemik>> getByTipe(String tipe) {
        return endemikDao.getByTipe(tipe);
    }

    public LiveData<List<Endemik>> search(String keyword) {
        return endemikDao.search(keyword);
    }

    public void insertFavorit(Favorit favorit) {
        executor.execute(() -> favoritDao.insert(favorit));
    }

    public void deleteFavorit(String id) {
        executor.execute(() -> favoritDao.deleteById(id));
    }

    public LiveData<List<Favorit>> getAllFavorit() {
        return favoritDao.getAll();
    }

    public void isFavorit(String id, FavoritCallback callback) {
        executor.execute(() -> {
            boolean result = favoritDao.isFavorit(id) > 0;
            callback.onResult(result);
        });
    }

    public interface FavoritCallback {
        void onResult(boolean isFavorit);
    }
}