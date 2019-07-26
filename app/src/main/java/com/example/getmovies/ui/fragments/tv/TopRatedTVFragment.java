package com.example.getmovies.ui.fragments.tv;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Color;
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
import com.example.getmovies.viewModel.TopRatedTVViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TopRatedTVFragment extends Fragment {

    @BindView(R.id.recycler_viewTF)
    RecyclerView recyclerViewTF;
    @BindView(R.id.swipeTopF)
    SwipeRefreshLayout swipeTopF;
    private TopRatedTVViewModel topModel;
    private TVPagedAdapter adapter;
    ProgressDialog pd;

    public TopRatedTVFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top_rated_tv, container, false);

        ButterKnife.bind(this, v);

        initView();
        swipeTopF.setColorSchemeColors(Color.RED);
        swipeTopF.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                recyclerViewTF.setLayoutManager(new GridLayoutManager(getContext(), 2));
            } else {
                recyclerViewTF.setLayoutManager(new GridLayoutManager(getContext(), 4));
            }
        } else {
            recyclerViewTF.setLayoutManager(new GridLayoutManager(getContext(), 3));

        }

        recyclerViewTF.setItemAnimator(new DefaultItemAnimator());

        loadData();
    }

    public void loadData() {

        topModel = ViewModelProviders.of(this).get(TopRatedTVViewModel.class);

        adapter = new TVPagedAdapter(getContext());
        recyclerViewTF.setAdapter(adapter);


        topModel.getTopRatedTV().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                if(results != null){
                    adapter.submitList(results);
                }
                pd.dismiss();
                swipeTopF.setRefreshing(false);
            }
        });
    }
}
