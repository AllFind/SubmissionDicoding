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
import com.example.submission.adapter.ListFavShowAdapter;
import com.example.submission.db.ShowHelper;
import com.example.submission.model.Show;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTVFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Show> list = new ArrayList<>();
    private ShowHelper showHelper;
    private ProgressBar progressBar;


    public FavoriteTVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.rv_show);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);

        showHelper = ShowHelper.getInstance(view.getContext());
        showHelper.open();
        list = showHelper.getAllFavShow();


        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ListFavShowAdapter adapter = new ListFavShowAdapter(list, view.getContext());
        recyclerView.setAdapter(adapter);

        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
