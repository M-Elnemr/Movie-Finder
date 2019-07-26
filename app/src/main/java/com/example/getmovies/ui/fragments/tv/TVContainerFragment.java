package com.example.getmovies.ui.fragments.tv;

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

public class TVContainerFragment extends Fragment {

    @BindView(R.id.viewpagertabT)
    SmartTabLayout viewpagertabT;
    @BindView(R.id.viewPagerT)
    ViewPager viewPagerT;

    public TVContainerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tvcontainer, container, false);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), 2);
        viewPagerT.post(() -> {
//            adapter.addFragment(new AllTV(), "All TV");
            adapter.addFragment(new PopularTVFragment(), "Popular");
            adapter.addFragment(new TopRatedTVFragment(), "Top Rated");

            viewPagerT.setAdapter(adapter);
            viewpagertabT.setViewPager(viewPagerT);
        });
    }

}
