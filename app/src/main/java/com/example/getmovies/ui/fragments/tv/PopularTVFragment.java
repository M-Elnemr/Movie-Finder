package com.example.getmovies.ui.fragments.tv;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.getmovies.adapter.TVPagedAdapter;
import com.example.getmovies.dataSource.tv.PopularTVFactory;
import com.example.getmovies.model.responses.MoviesResponse;
import com.example.getmovies.model.responses.tvResponse.Result;
import com.example.getmovies.viewModel.PopularMoviesViewModel;
import com.example.getmovies.viewModel.PopularTVViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularTVFragment extends Fragment {

    @BindView(R.id.recycler_viewPF)
    RecyclerView recyclerViewPF;
    @BindView(R.id.swipePopularF)
    SwipeRefreshLayout swipePopularF;
    private PopularTVViewModel publicModel;
    private TVPagedAdapter adapter;
    ProgressDialog pd;

    public PopularTVFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_popular_tv, container, false);

        ButterKnife.bind(this, v);

        initView();

        swipePopularF.setColorSchemeColors(Color.RED);
        swipePopularF.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                recyclerViewPF.setLayoutManager(new GridLayoutManager(getContext(), 2));
            } else {
                recyclerViewPF.setLayoutManager(new GridLayoutManager(getContext(), 4));
            }
        }

        recyclerViewPF.setItemAnimator(new DefaultItemAnimator());

        loadData();
    }


    private void loadData() {
        publicModel = ViewModelProviders.of(this).get(PopularTVViewModel.class);
        adapter = new TVPagedAdapter(getContext());
        recyclerViewPF.setAdapter(adapter);

        publicModel.getPopularTV().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                if(results != null){
                    Log.d("fragment", "onChanged: ok" + results.size());

                    adapter.submitList(results);
                }
                pd.dismiss();
                swipePopularF.setRefreshing(false);
            }
        });
    }

}
