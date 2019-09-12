package com.example.submission.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission.DetailActivity;
import com.example.submission.R;
import com.example.submission.model.Show;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardShowAdapter extends RecyclerView.Adapter<CardShowAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Show> listShow = new ArrayList<>();

    public CardShowAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Show> getListShow() {
        return listShow;
    }

    public void setListShow(ArrayList<Show> listShow) {
        this.listShow.clear();
        this.listShow = listShow;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Show show = getListShow().get(position);


        Picasso.get().load(show.getPhoto()).into(holder.imgPhoto);
        holder.txtTitle.setText(show.getTitle());
        holder.txtDesc.setText(show.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailActivity.class);
                i.putExtra("OBJECT", show);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getListShow().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView txtTitle, txtDesc;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.poster);
            txtTitle = itemView.findViewById(R.id.title);
            txtDesc = itemView.findViewById(R.id.desc);
        }
    }
}
