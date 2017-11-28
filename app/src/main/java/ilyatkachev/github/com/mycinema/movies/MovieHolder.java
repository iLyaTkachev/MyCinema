package ilyatkachev.github.com.mycinema.movies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;

public class MovieHolder extends RecyclerView.ViewHolder {

    private IMovie mMovie;

    private TextView mTitleTextView;

    public MovieHolder(View itemView) {
        super(itemView);
        mTitleTextView = itemView.findViewById(R.id.text_view_1);
    }

    public void bindMovie(IMovie pMovie){
        mMovie = pMovie;
        mTitleTextView.setText(mMovie.getTitle());
    }
}
