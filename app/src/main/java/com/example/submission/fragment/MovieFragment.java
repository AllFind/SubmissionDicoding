package com.example.submission.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission.R;
import com.example.submission.ViewModel.MovieViewModel;
import com.example.submission.adapter.CardMovieAdapter;
import com.example.submission.model.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private CardMovieAdapter adapter;
    private MovieViewModel movieViewModel;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {

        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null) {
                adapter.setListMovie(movies);
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    };


    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView.setVisibility(View.INVISIBLE);

        adapter = new CardMovieAdapter(getContext());
        adapter.notifyDataSetChanged();

        movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);

        movieViewModel.getMovies().observe(this, getMovie);
        movieViewModel.setMovies();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);


    }

}
