package com.ninestartsvinaytask.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninestartsvinaytask.PaginationModel.PaginationActivity;
import com.ninestartsvinaytask.R;
import com.ninestartsvinaytask.jsonResponse.MoviesModel.DataResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.ViewHolder> {
    private Context context;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w150";

    private List<DataResult> movieResults= new ArrayList<>();

    private boolean isLoadingAdded = false;
    public PaginationAdapter(Context context) {
        this.context =context;

    }

    public List<DataResult> getMovieResults() {
        return movieResults;
    }

    public void setMovieResults(List<DataResult> movieResults) {
        this.movieResults = movieResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
        return new PaginationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso
                .with(context)
                .load(BASE_URL_IMG+movieResults.get(position).getPosterPath())
                .into(holder.mPosterImg);
        holder.mMovieTitle.setText(movieResults.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
         return movieResults == null ? 0 : movieResults.size();
    }

    public void addAll(List<DataResult> moveResults) {
        for (DataResult result : moveResults) {
            add(result);
        }
    }

    private void add(DataResult r) {
        movieResults.add(r);
        notifyItemInserted(movieResults.size() - 1);
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new DataResult());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movieResults.size() - 1;
        DataResult result = getItem(position);

        if (result != null) {
            movieResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    private DataResult getItem(int position) {
        return movieResults.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mMovieTitle;
        private TextView mMovieDesc;
        private TextView mYear; // displays "year | language"
        private ImageView mPosterImg;
        private ProgressBar mProgress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mMovieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            mMovieDesc = (TextView) itemView.findViewById(R.id.movie_desc);
            mYear = (TextView) itemView.findViewById(R.id.movie_year);
            mPosterImg = (ImageView) itemView.findViewById(R.id.movie_poster);
           // mProgress = (ProgressBar) itemView.findViewById(R.id.movie_progress);
        }
    }
}
