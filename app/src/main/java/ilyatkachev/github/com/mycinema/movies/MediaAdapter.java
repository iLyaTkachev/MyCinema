package ilyatkachev.github.com.mycinema.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.data.remote.gson.BaseMediaObject;

public class MediaAdapter extends RecyclerView.Adapter<MediaHolder> {

    private List<BaseMediaObject> mMovies;
    private Context mContext;
    private IMediaCardListener mMovieCardListener;

    public MediaAdapter(final Context pContext, final List<BaseMediaObject> pMediaObjects, final IMediaCardListener pMovieCardListener) {
        mMovies = pMediaObjects;
        mContext = pContext;
        mMovieCardListener = pMovieCardListener;
    }

    @Override
    public MediaHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.movie_card, parent, false);
        return new MediaHolder(view);
    }

    @Override
    public void onBindViewHolder(final MediaHolder holder, final int position) {
        final BaseMediaObject movie = mMovies.get(position);
        holder.bindMovie(movie, mMovieCardListener);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
