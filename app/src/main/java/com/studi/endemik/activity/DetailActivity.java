package com.studi.endemik.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.studi.endemik.R;
import com.studi.endemik.model.Favorit;
import com.studi.endemik.viewmodel.FavoritViewModel;

public class DetailActivity extends AppCompatActivity {

    private FavoritViewModel favoritViewModel;
    private boolean isFavorit = false;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        id = getIntent().getStringExtra("id");
        String nama = getIntent().getStringExtra("nama");
        String foto = getIntent().getStringExtra("foto");
        String deskripsi = getIntent().getStringExtra("deskripsi");

        TextView tvJudul = findViewById(R.id.tv_judul);
        ImageView ivFoto = findViewById(R.id.iv_foto);
        TextView tvDeskripsi = findViewById(R.id.tv_deskripsi);
        ImageButton btnBack = findViewById(R.id.btn_back);
        ImageButton btnFavorit = findViewById(R.id.btn_favorit);

        tvJudul.setText(nama);
        tvDeskripsi.setText(deskripsi);
        Glide.with(this).load(foto).into(ivFoto);

        btnBack.setOnClickListener(v -> finish());

        favoritViewModel = new ViewModelProvider(this).get(FavoritViewModel.class);

        favoritViewModel.isFavorit(id, result -> runOnUiThread(() -> {
            isFavorit = result;
            btnFavorit.setImageResource(isFavorit ?
                    android.R.drawable.btn_star_big_on :
                    android.R.drawable.btn_star_big_off);
        }));

        btnFavorit.setOnClickListener(v -> {
            if (isFavorit) {
                favoritViewModel.delete(id);
                isFavorit = false;
                btnFavorit.setImageResource(android.R.drawable.btn_star_big_off);
            } else {
                Favorit favorit = new Favorit();
                favorit.setId(id);
                favorit.setNama(getIntent().getStringExtra("nama"));
                favorit.setFoto(getIntent().getStringExtra("foto"));
                favorit.setDeskripsi(getIntent().getStringExtra("deskripsi"));
                favorit.setTipe(getIntent().getStringExtra("tipe"));
                favorit.setNamaLatin(getIntent().getStringExtra("namaLatin"));
                favorit.setFamili(getIntent().getStringExtra("famili"));
                favorit.setGenus(getIntent().getStringExtra("genus"));
                favorit.setAsal(getIntent().getStringExtra("asal"));
                favorit.setSebaran(getIntent().getStringExtra("sebaran"));
                favorit.setStatus(getIntent().getStringExtra("status"));
                favoritViewModel.insert(favorit);
                isFavorit = true;
                btnFavorit.setImageResource(android.R.drawable.btn_star_big_on);
            }
        });
    }
}