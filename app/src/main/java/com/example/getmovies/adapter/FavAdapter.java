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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.getmovies.R;
import com.example.getmovies.model.MovieShortDetails;
import com.example.getmovies.model.responses.MoviesResponse;
import com.example.getmovies.ui.activities.DetailActivity;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavHolder> {

    private List<MovieShortDetails> movies ;
    private Context context;
    private FavFace listner;

    public interface FavFace{
        void onDelete(int position);
    }

    public void setListner(FavFace listner) {
        this.listner = listner;
    }

    public FavAdapter(Context context, List<MovieShortDetails> movies) {
        this.movies = movies;
        this.context = context;
    }

    public class FavHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail, imgDelete;
        TextView title, userRating;

        public FavHolder(@NonNull View itemView, FavFace listner) {
            super(itemView);
            imgDelete = itemView.findViewById(R.id.fImgDelete);
            thumbnail = itemView.findViewById(R.id.fThumbnail);
            title = itemView.findViewById(R.id.fTitle);
            userRating = itemView.findViewById(R.id.fUserRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        MovieShortDetails movie = movies.get(position);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("Type", movie.type());
                        intent.putExtra("Title", movie.mOriginalTitle());
                        intent.putExtra("Path", movie.mPosterPath());
                        intent.putExtra("OverView", movie.mOverview());
                        intent.putExtra("Vote",movie.mVoteAverage());
                        intent.putExtra("Release", movie.mReleaseDate());
                        intent.putExtra("ID", movie.movieID());

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = getAdapterPosition();
                    if(i != RecyclerView.NO_POSITION){
                        listner.onDelete(i);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public FavHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_movie_card, parent, false);
        return new FavHolder(view, listner);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FavHolder holder, int position) {

        Glide.with(context).load(movies.get(position).mPosterPath()).placeholder(R.drawable.load).into(holder.thumbnail);
        holder.title.setText(movies.get(position).mOriginalTitle());
        holder.userRating.setText(movies.get(position).mVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return (movies != null ? movies.size() : 0);
    }
}
