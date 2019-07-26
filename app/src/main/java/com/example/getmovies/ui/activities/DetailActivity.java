package com.example.getmovies.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.getmovies.BuildConfig;
import com.example.getmovies.R;
import com.example.getmovies.SharedPrefUtil;
import com.example.getmovies.model.MovieShortDetails;
import com.example.getmovies.model.responses.MovieVideosResponse;
import com.example.getmovies.movieInterface.LoadData;
import com.example.getmovies.viewModel.DBViewModel;
import com.example.getmovies.viewModel.MovieVideosViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements LoadData.MovieInterFace {

    @BindView(R.id.image_header)
    ImageView imageHeader;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.summary)
    TextView summary;
    @BindView(R.id.userRating)
    TextView userRating;
    @BindView(R.id.releaseDate)
    TextView releaseDate;
    @BindView(R.id.Collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.youtube_container)
    LinearLayout youtubeContainer;
    @BindView(R.id.image_container)
    RelativeLayout imageContainer;
    @BindView(R.id.videoBtn)
    Button videoBtn;
    @BindView(R.id.releaseDateView)
    TextView releaseDateView;

    private DBViewModel model;
    private MovieVideosViewModel videoModel;
    private int isAdded = 0;

    String mTitle, path, overView, release, tybe;
    long id;
    double vote;
    private Menu menu;
    List<MovieShortDetails> movieList;

    private YouTubePlayerFragment youTubePlayerFragment;
    private String vid;
    private String youTubeLink;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        model = ViewModelProviders.of(this).get(DBViewModel.class);
        videoModel = ViewModelProviders.of(this).get(MovieVideosViewModel.class);

        if (getIntent().hasExtra("Title")) {
            if (getIntent().getExtras().getString("Type").equals("TV")) {

                tybe = getIntent().getExtras().getString("Type");
                mTitle = getIntent().getExtras().getString("Title");
                path = getIntent().getExtras().getString("Path");
                overView = getIntent().getExtras().getString("OverView");
                vote = getIntent().getExtras().getDouble("Vote");
                id = getIntent().getExtras().getLong("ID");


                videoModel.vidTV(id);
                videoModel.getVideos().observe(this, new Observer<List<MovieVideosResponse.Result>>() {
                    @Override
                    public void onChanged(List<MovieVideosResponse.Result> results) {
                        if (results.size() > 0) {

                            if (SharedPrefUtil.hasKey(DetailActivity.this, "VID") && SharedPrefUtil.hasKey(DetailActivity.this, "TITLE")) {
                                if (mTitle.equals(SharedPrefUtil.getString(DetailActivity.this, "TITLE"))) {
                                    youtubeContainer.setVisibility(View.VISIBLE);
                                    videoBtn.setVisibility(View.GONE);
                                    appbar.setExpanded(false);
//
                                } else {
                                    youtubeContainer.setVisibility(View.GONE);
                                    videoBtn.setVisibility(View.VISIBLE);
                                }
                            } else {
                                youtubeContainer.setVisibility(View.GONE);
                                videoBtn.setVisibility(View.VISIBLE);
                            }

                            videoBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    youTubeLink = results.get(0).getKey();
                                    initYouTube(youTubeLink);
                                }
                            });
                        }
                    }
                });

                Glide.with(this).load(path).placeholder(R.drawable.load).into(imageHeader);
                title.setText(mTitle);
                summary.setText(overView);
                userRating.setText(vote + "");
                releaseDate.setVisibility(View.GONE);
                releaseDateView.setVisibility(View.GONE);

                initCollapsingBar(mTitle);


                ///////////////////////////////////////////////////////////////////////////////////////////////
            } else {

                tybe = getIntent().getExtras().getString("Type");
                mTitle = getIntent().getExtras().getString("Title");
                path = getIntent().getExtras().getString("Path");
                overView = getIntent().getExtras().getString("OverView");
                vote = getIntent().getExtras().getDouble("Vote");
                release = getIntent().getExtras().getString("Release");
                id = getIntent().getExtras().getLong("ID");


                videoModel.vid(id);
                videoModel.getVideos().observe(this, new Observer<List<MovieVideosResponse.Result>>() {
                    @Override
                    public void onChanged(List<MovieVideosResponse.Result> results) {
                        if (results.size() > 0) {

                            if (SharedPrefUtil.hasKey(DetailActivity.this, "VID") && SharedPrefUtil.hasKey(DetailActivity.this, "TITLE")) {
                                if (mTitle.equals(SharedPrefUtil.getString(DetailActivity.this, "TITLE"))) {
                                    youtubeContainer.setVisibility(View.VISIBLE);
                                    videoBtn.setVisibility(View.GONE);
                                    appbar.setExpanded(false);
//
                                } else {
                                    youtubeContainer.setVisibility(View.GONE);
                                    videoBtn.setVisibility(View.VISIBLE);
                                }
                            } else {
                                youtubeContainer.setVisibility(View.GONE);
                                videoBtn.setVisibility(View.VISIBLE);
                            }

                            videoBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    youTubeLink = results.get(0).getKey();
                                    initYouTube(youTubeLink);
                                }
                            });
                        }
                    }
                });

                Glide.with(this).load(path).placeholder(R.drawable.load).into(imageHeader);
                title.setText(mTitle);
                summary.setText(overView);
                userRating.setText(vote + "");
                releaseDate.setText(release);

                initCollapsingBar(mTitle);

            }

        }
    }


    private void initCollapsingBar(String title) {

        collapsingToolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        appbar.setExpanded(true);
        appbar.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(title);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle("");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.fav_menu, menu);
        this.menu = menu;

        model.getMovies().observe(this, new Observer<List<MovieShortDetails>>() {
            @Override
            public void onChanged(List<MovieShortDetails> movieShortDetails) {
                for (MovieShortDetails movies : movieShortDetails) {
                    if (mTitle.equals(movies.mOriginalTitle())) {
                        menu.getItem(0).setIcon(R.drawable.ic_favorite);
                        break;
                    }
                }
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.fav) {
            LoadData loadData = new LoadData(getApplication());
            loadData.getFavMovies(this);

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSucceed(List<MovieShortDetails> movieShortDetails) {

        movieList = movieShortDetails;
        isAdded = 0;

        for (MovieShortDetails movies : movieList) {
            if (movies.mOriginalTitle().equals(mTitle)) {
                isAdded = 1;
                break;
            }
        }
        if (isAdded == 0) {
            model.addMovie(new MovieShortDetails(id,tybe, mTitle, overView, path, release, vote));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    menu.getItem(0).setIcon(R.drawable.ic_favorite);
                }
            });
        } else {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    model.deleteMovieTitle(mTitle);
                    menu.getItem(0).setIcon(R.drawable.ic_favorite_border_black_24dp);
                }
            });
        }
    }

    public void initYouTube(String link) {


        youTubePlayerFragment = (YouTubePlayerFragment)
                getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(BuildConfig.Youtube_API, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    videoBtn.setVisibility(View.GONE);
                    youtubeContainer.setVisibility(View.VISIBLE);
                    appbar.setExpanded(false);
                    youTubePlayer.loadVideo(link);
                    youTubePlayer.play();
                    vid = "1";
                }
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefUtil.hasKey(this, "VID") && SharedPrefUtil.hasKey(this, "TITLE")) {
            if (mTitle.equals(SharedPrefUtil.getString(this, "TITLE"))) {
                initYouTube(SharedPrefUtil.getString(this, "VID"));
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (vid != null) {
            if (vid.equals("1")) {
                if (youTubeLink != null) {
                    SharedPrefUtil.saveString(this, "VID", youTubeLink);
                    SharedPrefUtil.saveString(this, "TITLE", mTitle);
                }
            }
        }
    }
}
