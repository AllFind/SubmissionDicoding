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

public class ListFavShowAdapter extends RecyclerView.Adapter<ListFavShowAdapter.ListViewHolder> {
    private ArrayList<Show> listShow;
    private Context context;
    private ShowHelper showHelper;

    public ListFavShowAdapter(ArrayList<Show> list, Context context) {
        listShow = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        final Show show = listShow.get(position);

        showHelper = ShowHelper.getInstance(context);

        holder.favBtn.setImageResource(R.drawable.favorite_red);

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.favBtn.setImageResource(R.drawable.favorite_black);
                showHelper.deleteFavShow(show.getID());
                listShow.remove(position);
                notifyItemRemoved(position);
                notifyItemChanged(position, listShow.size());
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
                view.getContext().startActivity(i);
            }
        });
        holder.ratingBar.setRating(((float) show.getRating() / 2));
        holder.txtRating.setText(Double.toString(show.getRating()));
        holder.txtDate.setText(show.getDate());

    }

    @Override
    public int getItemCount() {
        return listShow.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto, favBtn;
        TextView txtTitle, txtDesc, txtDate, txtRating;
        RatingBar ratingBar;


        public ListViewHolder(@NonNull View itemView) {
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
