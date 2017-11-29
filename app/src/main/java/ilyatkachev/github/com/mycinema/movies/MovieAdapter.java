package ilyatkachev.github.com.mycinema.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {

    private List<IMovie> mMovies;
    private Context mContext;
    private IMovieCardListener mMovieCardListener;

    public MovieAdapter(Context pContext, List<IMovie> pMovies, IMovieCardListener pMovieCardListener) {
        mMovies = pMovies;
        mContext = pContext;
        mMovieCardListener = pMovieCardListener;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_card, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        IMovie movie = mMovies.get(position);
        holder.bindMovie(movie, mMovieCardListener);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
