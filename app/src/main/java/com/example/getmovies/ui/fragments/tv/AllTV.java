package com.example.getmovies.ui.fragments.tv;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
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
import com.example.getmovies.adapter.TVPagedAdapter;
import com.example.getmovies.model.responses.tvResponse.Result;
import com.example.getmovies.viewModel.AllTVViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllTV extends Fragment {

    @BindView(R.id.recycler_viewF)
    RecyclerView recyclerViewF;
    @BindView(R.id.swipeAllF)
    SwipeRefreshLayout swipeAllF;

    private TVPagedAdapter adapter;
    private AllTVViewModel model;
    ProgressDialog pd;

    public AllTV() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_tv, container, false);
        ButterKnife.bind(this, v);

        init();

        swipeAllF.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
            recyclerViewF.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }else {
            recyclerViewF.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }

        recyclerViewF.setItemAnimator(new DefaultItemAnimator());

        loadData();
    }

    public void loadData(){

        model = ViewModelProviders.of(this).get(AllTVViewModel.class);
        adapter = new TVPagedAdapter(getContext());

        recyclerViewF.setAdapter(adapter);

        model.getAllTV().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                if(results != null){
                    adapter.submitList(results);
                }
                pd.dismiss();
                swipeAllF.setRefreshing(false);
            }
        });
    }
}
