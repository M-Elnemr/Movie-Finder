package com.example.getmovies.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.getmovies.R;
import com.example.getmovies.SharedPrefUtil;
import com.example.getmovies.adapter.MoviesAdapter;
import com.example.getmovies.ui.fragments.FavFragment;
import com.example.getmovies.ui.fragments.movies.MovieContainerFragment;
import com.example.getmovies.ui.fragments.tv.TVContainerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.frameContainer)
    FrameLayout frameContainer;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (SharedPrefUtil.hasKey(this, "VID")) {
            SharedPrefUtil.removeString(this, "VID");
            SharedPrefUtil.removeString(this, "TITLE");
        }

        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new MovieContainerFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefUtil.hasKey(this, "VID")) {
            SharedPrefUtil.removeString(this, "VID");
        }
    }

    private void loadFragment(Fragment fragmentq){

//        if(!getSupportFragmentManager().beginTransaction().isEmpty()){
//            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
//                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
//                }
//        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameContainer, fragmentq)
                .disallowAddToBackStack()
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.movies:
                loadFragment(new MovieContainerFragment());
                return true;

            case R.id.tv:
                loadFragment(new TVContainerFragment());
                return true;

            case R.id.fav:
                loadFragment(new FavFragment());
                return true;

            default:
                return false;
        }
    }
}