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
import com.example.getmovies.viewModel.TopRatedMoviesViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopRated extends Fragment {

    @BindView(R.id.recycler_viewT)
    RecyclerView recyclerViewT;
    @BindView(R.id.swipeTop)
    SwipeRefreshLayout swipeTop;

    private TopRatedMoviesViewModel topModel;
    private AllMoviesPagedAdapter adapter;
    ProgressDialog pd;

    public TopRated() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_top_rated, container, false);

        ButterKnife.bind(this, v);

        initView();
        swipeTop.setColorSchemeColors(Color.RED);
        swipeTop.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        return v;
    }

    public void initView() {
        pd = new ProgressDialog(getContext());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        if (getActivity() != null) {
            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                recyclerViewT.setLayoutManager(new GridLayoutManager(getContext(), 2));
            } else {
                recyclerViewT.setLayoutManager(new GridLayoutManager(getContext(), 4));
            }
        } else {
            recyclerViewT.setLayoutManager(new GridLayoutManager(getContext(), 3));

        }

        recyclerViewT.setItemAnimator(new DefaultItemAnimator());

        loadData();
    }

    public void loadData() {

        topModel = ViewModelProviders.of(this).get(TopRatedMoviesViewModel.class);
        adapter = new AllMoviesPagedAdapter(getContext());
        recyclerViewT.setAdapter(adapter);


        topModel.getTopRatedMovies().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                adapter.submitList(results);
                pd.dismiss();
                swipeTop.setRefreshing(false);

            }
        });
    }
}
