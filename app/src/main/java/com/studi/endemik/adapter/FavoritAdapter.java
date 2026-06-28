package com.studi.endemik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.studi.endemik.R;
import com.studi.endemik.model.Favorit;
import java.util.List;

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.ViewHolder> {

    private final Context context;
    private List<Favorit> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Favorit favorit);
    }

    public FavoritAdapter(Context context, List<Favorit> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setList(List<Favorit> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_endemik, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Favorit favorit = list.get(position);
        holder.tvNama.setText(favorit.getNama());
        Glide.with(context)
                .load(favorit.getFoto())
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.ivFoto);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(favorit);
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvNama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            tvNama = itemView.findViewById(R.id.tv_nama);
        }
    }
}