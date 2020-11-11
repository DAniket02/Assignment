package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.UserInfoHolder> {

    Context mContext;
    ArrayList<ResponseModel.Search> movieList;
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

    public MovieListAdapter(Context mContext, ArrayList<ResponseModel.Search> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieListAdapter.UserInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new UserInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoHolder holder, final int position) {

        Picasso.get().load(movieList.get(position).getPoster()).into(holder.imageViewPoster);
        holder.textViewYear.setText(movieList.get(position).getYear());
        holder.textViewName.setText(movieList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class UserInfoHolder extends RecyclerView.ViewHolder {

        ImageView imageViewPoster;
        TextView textViewYear, textViewName;
        CardView cardView;


        public UserInfoHolder(@NonNull View itemView) {
            super(itemView);

            imageViewPoster = (ImageView)itemView.findViewById(R.id.imageViewMoviePic);
            textViewYear = (TextView)itemView.findViewById(R.id.textViewMovieYear);
            textViewName = (TextView)itemView.findViewById(R.id.textViewMovieName);
            cardView = (CardView) itemView.findViewById(R.id.cardViewUser);
        }
    }
}
