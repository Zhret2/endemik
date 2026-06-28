package com.studi.endemik.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.studi.endemik.R;
import com.studi.endemik.viewmodel.EndemikViewModel;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button btnLanjut = findViewById(R.id.btn_lanjut);

        EndemikViewModel viewModel = new ViewModelProvider(this).get(EndemikViewModel.class);
        viewModel.fetchAndSave(
                () -> runOnUiThread(() -> {
                    btnLanjut.setVisibility(View.VISIBLE);
                    btnLanjut.setOnClickListener(v -> {
                        startActivity(new Intent(this, HomeActivity.class));
                        finish();
                    });
                }),
                () -> runOnUiThread(() -> {
                    btnLanjut.setVisibility(View.VISIBLE);
                    btnLanjut.setText("Gagal, coba lagi");
                })
        );
    }
}