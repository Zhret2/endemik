package com.studi.endemik.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.studi.endemik.R;
import com.studi.endemik.activity.DetailActivity;
import com.studi.endemik.adapter.EndemikAdapter;
import com.studi.endemik.viewmodel.EndemikViewModel;
import java.util.ArrayList;

public class TumbuhanFragment extends Fragment {

    private EndemikAdapter adapter;
    private EndemikViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tumbuhan, container, false);

        RecyclerView rv = view.findViewById(R.id.rv_tumbuhan);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        adapter = new EndemikAdapter(getContext(), new ArrayList<>());
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(endemik -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
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

        viewModel = new ViewModelProvider(requireActivity()).get(EndemikViewModel.class);
        viewModel.getByTipe("Tumbuhan").observe(getViewLifecycleOwner(), list -> {
            adapter.setList(list);
        });

        return view;
    }
}