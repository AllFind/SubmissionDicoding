package com.example.submission.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission.DetailActivity;
import com.example.submission.R;
import com.example.submission.db.ShowHelper;
import com.example.submission.model.Show;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardShowAdapter extends RecyclerView.Adapter<CardShowAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Show> listShow = new ArrayList<>();
    private ShowHelper showHelper;

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
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        final Show show = getListShow().get(position);
        showHelper = ShowHelper.getInstance(context);
        showHelper.open();
        Show favShow = showHelper.getShow(show.getID());

        if (favShow == null) {
            holder.favBtn.setImageResource(R.drawable.favorite_black);
            show.setFavorite(false);
        } else {
            holder.favBtn.setImageResource(R.drawable.favorite_red);
            show.setFavorite(true);
        }
        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (show.isFavorite()) {
                    show.setFavorite(false);
                    holder.favBtn.setImageResource(R.drawable.favorite_black);
                    showHelper.deleteFavShow(show.getID());
                } else {
                    show.setFavorite(true);
                    holder.favBtn.setImageResource(R.drawable.favorite_red);
                    showHelper.insertFavShow(show);
                }
            }
        });

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
        holder.ratingBar.setRating(((float) show.getRating() / 2));
        holder.txtRating.setText(Double.toString(show.getRating()));
        holder.txtDate.setText(show.getDate());
    }

    @Override
    public int getItemCount() {
        return getListShow().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto, favBtn;
        TextView txtTitle, txtDesc, txtRating, txtDate;
        RatingBar ratingBar;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.poster);
            txtTitle = itemView.findViewById(R.id.title);
            txtDesc = itemView.findViewById(R.id.desc);
            txtDate = itemView.findViewById(R.id.date);
            ratingBar = itemView.findViewById(R.id.MyRating);
            txtRating = itemView.findViewById(R.id.rating);
            favBtn = itemView.findViewById(R.id.favBtn);
        }
    }
}
