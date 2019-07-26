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
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.getmovies.db.AppDatabase;
import com.example.getmovies.db.MovieDao;
import com.example.getmovies.model.MovieShortDetails;
import com.example.getmovies.model.responses.Result;
import com.example.getmovies.ui.activities.DetailActivity;
import com.example.getmovies.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder> {

    private Context context;
    private List<Result> movies;

    public MoviesAdapter(Context context, List<Result> movies) {
        this.context = context;
        this.movies = movies;
    }

    public class MoviesHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail, imageFav;
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
                        Result movie = movies.get(position);
                        Intent intent = new Intent(context, DetailActivity.class);
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MoviesHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int i) {

        holder.title.setText(movies.get(i).getOriginalTitle());
        holder.userRating.setText(movies.get(i).getVoteAverage().toString());
        Glide.with(context).load(movies.get(i).getPosterPath()).placeholder(R.drawable.loading).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return (movies != null ? movies.size() : 0);
    }
}
