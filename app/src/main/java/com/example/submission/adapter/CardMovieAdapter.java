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
import com.example.submission.db.MovieHelper;
import com.example.submission.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardMovieAdapter extends RecyclerView.Adapter<CardMovieAdapter.CardViewViewHolder> {
    private Context context;
    private ArrayList<Movie> listMovie = new ArrayList<>();
    private MovieHelper movieHelper;

    public CardMovieAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie.clear();
        this.listMovie = listMovie;
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
        final Movie movie = getListMovie().get(position);
        movieHelper = MovieHelper.getInstance(context);
        movieHelper.open();
        Movie favMovie = movieHelper.getMovie(movie.getID());

        if (favMovie == null) {
            holder.favBtn.setImageResource(R.drawable.favorite_black);
            movie.setFavorite(false);
        } else {
            holder.favBtn.setImageResource(R.drawable.favorite_red);
            movie.setFavorite(true);
        }
        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movie.isFavorite()) {
                    movie.setFavorite(false);
                    holder.favBtn.setImageResource(R.drawable.favorite_black);
                    movieHelper.deleteFavMovie(movie.getID());
                } else {
                    movie.setFavorite(true);
                    holder.favBtn.setImageResource(R.drawable.favorite_red);
                    movieHelper.insertFavMovie(movie);
                }
            }
        });
        Picasso.get().load(movie.getPhoto()).into(holder.imgPhoto);
        holder.txtTitle.setText(movie.getTitle());
        holder.txtDesc.setText(movie.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailActivity.class);
                i.putExtra("OBJECT", movie);
                context.startActivity(i);
            }
        });
        holder.ratingBar.setRating(((float) movie.getRating() / 2));
        holder.txtRating.setText(Double.toString(movie.getRating()));
        holder.txtDate.setText(movie.getDate());

    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto, favBtn;
        TextView txtTitle, txtDesc, txtDate, txtRating;
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
