package com.example.getmovies.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getmovies.R;
import com.example.getmovies.adapter.FavAdapter;
import com.example.getmovies.model.MovieShortDetails;
import com.example.getmovies.viewModel.DBViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavFragment extends Fragment {

    @BindView(R.id.favRecycler)
    RecyclerView favRecycler;

    private FavAdapter adapter;
    private DBViewModel model;


    public FavFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fav, container, false);
        ButterKnife.bind(this, view);
        init();

        return view;
    }


    private void init(){

        model = ViewModelProviders.of(this).get(DBViewModel.class);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            favRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }else {
            favRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        }

        model.getMovies().observe(this, new Observer<List<MovieShortDetails>>() {
            @Override
            public void onChanged(List<MovieShortDetails> movieShortDetails) {

                adapter = new FavAdapter(getActivity(), movieShortDetails);
                favRecycler.setAdapter(adapter);
                favRecycler.smoothScrollToPosition(0);

                adapter.setListner(new FavAdapter.FavFace() {
                    @Override
                    public void onDelete(int position) {
                        model.deleteMovie(movieShortDetails.get(position));
                        Toast.makeText(getActivity(), "Movie Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
