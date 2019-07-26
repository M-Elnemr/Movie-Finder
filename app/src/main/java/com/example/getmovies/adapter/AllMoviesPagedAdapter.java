package com.example.getmovies.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.getmovies.BuildConfig;
import com.example.getmovies.R;
import com.example.getmovies.model.responses.Result;
import com.example.getmovies.ui.activities.DetailActivity;

public class AllMoviesPagedAdapter extends PagedListAdapter<Result, AllMoviesPagedAdapter.MoviesHolder> {

    private static DiffUtil.ItemCallback<Result> DIFF_CALLBACK = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return (oldItem.getId().equals(newItem.getId()));
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return (oldItem.getOriginalTitle().equals(newItem.getOriginalTitle()));
        }
    };

    private Context context;

    public AllMoviesPagedAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public class MoviesHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView title, userRating;

        public MoviesHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            userRating = itemView.findViewById(R.id.userRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Result movie = getItem(position);
                        Intent intent = new Intent(context, DetailActivity.class);
                        assert movie != null;
                        intent.putExtra("Type", "Movie");
                        intent.putExtra("Title", movie.getOriginalTitle());
                        intent.putExtra("Path", movie.getPosterPath());
                        intent.putExtra("OverView", movie.getOverview());
                        intent.putExtra("Vote",movie.getVoteAverage());
                        intent.putExtra("Release", movie.getReleaseDate());
                        intent.putExtra("ID", movie.getId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent,false);
        return new MoviesHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int position) {
        Result movie = getItem(position);

        assert movie != null;
        holder.title.setText(movie.getOriginalTitle());
        holder.userRating.setText(movie.getVoteAverage().toString());
        Glide.with(context).load(movie.getPosterPath()).placeholder(R.drawable.loading).into(holder.thumbnail);
    }
}