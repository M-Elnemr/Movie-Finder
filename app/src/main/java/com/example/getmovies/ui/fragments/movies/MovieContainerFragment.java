package com.example.getmovies.ui.fragments.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.getmovies.R;
import com.example.getmovies.adapter.ViewPagerAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieContainerFragment extends Fragment {

    @BindView(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public MovieContainerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_container, container, false);
        ButterKnife.bind(this, view);
        initView();


        return view;
    }

    private void initView() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), 2);
        viewPager.post(() -> {
            adapter.addFragment(new AllMovies(), "All Movies");
            adapter.addFragment(new Pobular(), "Popular");
            adapter.addFragment(new TopRated(), "Top Rated");

            viewPager.setAdapter(adapter);
            viewpagertab.setViewPager(viewPager);
        });
    }

}
