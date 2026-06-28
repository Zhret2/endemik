package com.studi.endemik.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.studi.endemik.R;
import com.studi.endemik.adapter.EndemikAdapter;
import com.studi.endemik.viewmodel.EndemikViewModel;
import java.util.ArrayList;

public class PencarianActivity extends AppCompatActivity {

    private EndemikAdapter adapter;
    private EndemikViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian);

        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        RecyclerView rv = findViewById(R.id.rv_pencarian);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new EndemikAdapter(this, new ArrayList<>());
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(endemik -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", endemik.getId());
            intent.putExtra("nama", endemik.getNama());
            intent.putExtra("foto", endemik.getFoto());
            intent.putExtra("deskripsi", endemik.getDeskripsi());
            intent.putExtra("tipe", endemik.getTipe());
            intent.putExtra("namaLatin", endemik.getNamaLatin());
            intent.putExtra("famili", endemik.getFamili());
            intent.putExtra("genus", endemik.getGenus());
            intent.putExtra("asal", endemik.getAsal());
            intent.putExtra("sebaran", endemik.getSebaran());
            intent.putExtra("status", endemik.getStatus());
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(EndemikViewModel.class);

        EditText etSearch = findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.search(s.toString()).observe(PencarianActivity.this, list -> {
                    adapter.setList(list);
                });
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }
}