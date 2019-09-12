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
import com.example.submission.ViewModel.ShowViewModel;
import com.example.submission.adapter.CardShowAdapter;
import com.example.submission.model.Show;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {


    private CardShowAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ShowViewModel showViewModel;
    private Observer<ArrayList<Show>> getShow = new Observer<ArrayList<Show>>() {
        @Override
        public void onChanged(ArrayList<Show> shows) {
            adapter.setListShow(shows);
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    };


    public ShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.rv_show);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView.setVisibility(View.INVISIBLE);

        adapter = new CardShowAdapter(getContext());
        adapter.notifyDataSetChanged();

        showViewModel = ViewModelProviders.of(getActivity()).get(ShowViewModel.class);
        showViewModel.getShow().observe(this, getShow);
        showViewModel.setShow();

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

}
