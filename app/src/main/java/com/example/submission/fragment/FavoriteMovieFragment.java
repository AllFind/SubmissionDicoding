package com.example.submission.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission.R;
import com.example.submission.adapter.ListFavMovieAdapter;
import com.example.submission.db.MovieHelper;
import com.example.submission.model.Movie;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Movie> list = new ArrayList<>();
    private MovieHelper movieHelper;
    private ProgressBar progressBar;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);

        movieHelper = MovieHelper.getInstance(view.getContext());
        movieHelper.open();
        list = movieHelper.getAllFavMovie();


        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ListFavMovieAdapter adapter = new ListFavMovieAdapter(list, view.getContext());
        recyclerView.setAdapter(adapter);

        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);


    }
}
