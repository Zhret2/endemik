package com.studi.endemik.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.studi.endemik.R;
import com.studi.endemik.adapter.FavoritAdapter;
import com.studi.endemik.viewmodel.FavoritViewModel;
import java.util.ArrayList;

public class FavoritActivity extends AppCompatActivity {

    private FavoritAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        RecyclerView rv = findViewById(R.id.rv_favorit);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new FavoritAdapter(this, new ArrayList<>());
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(favorit -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", favorit.getId());
            intent.putExtra("nama", favorit.getNama());
            intent.putExtra("foto", favorit.getFoto());
            intent.putExtra("deskripsi", favorit.getDeskripsi());
            intent.putExtra("tipe", favorit.getTipe());
            intent.putExtra("namaLatin", favorit.getNamaLatin());
            intent.putExtra("famili", favorit.getFamili());
            intent.putExtra("genus", favorit.getGenus());
            intent.putExtra("asal", favorit.getAsal());
            intent.putExtra("sebaran", favorit.getSebaran());
            intent.putExtra("status", favorit.getStatus());
            startActivity(intent);
        });

        FavoritViewModel viewModel = new ViewModelProvider(this).get(FavoritViewModel.class);
        viewModel.getAll().observe(this, list -> adapter.setList(list));
    }
}