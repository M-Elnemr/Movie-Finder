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
import com.example.getmovies.R;
import com.example.getmovies.model.responses.tvResponse.Result;
import com.example.getmovies.ui.activities.DetailActivity;

public class TVPagedAdapter extends PagedListAdapter<Result, TVPagedAdapter.TVHolder> {

    private static DiffUtil.ItemCallback<Result> DIFF_CALLBACK = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return (oldItem.getId().equals(newItem.getId()));
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return (oldItem.getOriginalName().equals(newItem.getOriginalName()));
        }
    };

    private Context context;

    public TVPagedAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public class TVHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView title, userRating;

        public TVHolder(@NonNull View itemView) {
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
                        intent.putExtra("Type", "TV");
                        intent.putExtra("Title", movie.getOriginalName());
                        intent.putExtra("Path", movie.getPosterPath());
                        intent.putExtra("OverView", movie.getOverview());
                        intent.putExtra("Vote",movie.getVoteAverage());
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
    public TVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent,false);
        return new TVHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TVHolder holder, int position) {
        Result movie = getItem(position);

        assert movie != null;
        holder.title.setText(movie.getOriginalName());
        holder.userRating.setText(movie.getVoteAverage().toString());
        Glide.with(context).load(movie.getPosterPath()).placeholder(R.drawable.loading).into(holder.thumbnail);
    }
}
