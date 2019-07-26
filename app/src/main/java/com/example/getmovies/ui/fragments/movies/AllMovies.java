package com.example.getmovies.ui.fragments.movies;

import android.app.ProgressDialog;
import android.content.res.Configuration;
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
import com.example.getmovies.model.responses.Result;
import com.example.getmovies.viewModel.AllMoviesViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllMovies extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipeAll)
    SwipeRefreshLayout swipeAll;

    private AllMoviesPagedAdapter adapter;
    private AllMoviesViewModel model;
     ProgressDialog pd;

    public AllMovies() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_movies, container, false);
        ButterKnife.bind(this, v);

        init();

        swipeAll.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        return v;
    }

    public void init(){
        pd = new ProgressDialog(getContext());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadData();
    }

    public void loadData(){

        model = ViewModelProviders.of(this).get(AllMoviesViewModel.class);
        adapter = new AllMoviesPagedAdapter(getContext());

        recyclerView.setAdapter(adapter);

        model.getMoviesLiveList().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                if(results!= null){
                    adapter.submitList(results);
                }
                pd.dismiss();
                swipeAll.setRefreshing(false);
            }
        });
    }
}
