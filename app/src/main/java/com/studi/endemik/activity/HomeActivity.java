package com.studi.endemik.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.studi.endemik.R;
import com.studi.endemik.fragment.HewanFragment;
import com.studi.endemik.fragment.TumbuhanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadFragment(new HewanFragment());

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment;
            if (item.getItemId() == R.id.nav_hewan) {
                fragment = new HewanFragment();
            } else {
                fragment = new TumbuhanFragment();
            }
            loadFragment(fragment);
            return true;
        });

        findViewById(R.id.btn_search).setOnClickListener(v ->
                startActivity(new Intent(this, PencarianActivity.class)));

        findViewById(R.id.btn_favorit).setOnClickListener(v ->
                startActivity(new Intent(this, FavoritActivity.class)));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}