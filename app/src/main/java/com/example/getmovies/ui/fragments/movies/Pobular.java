package com.example.getmovies.ui.fragments.movies;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.getmovies.R;
import com.example.getmovies.adapter.AllMoviesPagedAdapter;
import com.example.getmovies.adapter.MoviesAdapter;
import com.example.getmovies.model.responses.MoviesResponse;
import com.example.getmovies.model.responses.Result;
import com.example.getmovies.viewModel.PopularMoviesViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Pobular extends Fragment {

    @BindView(R.id.recycler_viewP)
    RecyclerView recyclerViewP;
    @BindView(R.id.swipePopular)
    SwipeRefreshLayout swipePopular;

    private PopularMoviesViewModel publicModel;
    private AllMoviesPagedAdapter adapter;
    ProgressDialog pd;

    public Pobular() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_pobular, container, false);

        ButterKnife.bind(this, v);

        initView();

        swipePopular.setColorSchemeColors(Color.RED);
        swipePopular.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        return v;
    }

    private void initView() {
        pd = new ProgressDialog(getContext());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        if (getActivity() != null) {
            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                recyclerViewP.setLayoutManager(new GridLayoutManager(getContext(), 2));
            } else {
                recyclerViewP.setLayoutManager(new GridLayoutManager(getContext(), 4));
            }
        }

        recyclerViewP.setItemAnimator(new DefaultItemAnimator());

        loadData();
    }


    private void loadData() {
        publicModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);
        adapter = new AllMoviesPagedAdapter(getContext());
        recyclerViewP.setAdapter(adapter);

        publicModel.getPublicMovies().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                if (results != null){
                    adapter.submitList(results);
                }
                pd.dismiss();
                swipePopular.setRefreshing(false);
            }
        });
    }
}
