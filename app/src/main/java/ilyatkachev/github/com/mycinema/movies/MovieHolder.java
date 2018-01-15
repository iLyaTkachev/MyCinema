package ilyatkachev.github.com.mycinema.movies;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.util.Constants;
import ilyatkachev.github.com.mycinema.util.ImageLoaderWrapper;

public class MovieHolder extends RecyclerView.ViewHolder {

    private IMovie mMovie;

    private final TextView mTitleTextView;
    private final TextView mDateTextView;
    private final ImageView mOverflow;
    private final ImageView mPosterImageView;
    private final CardView mCardView;

    public MovieHolder(View itemView) {
        super(itemView);
        mTitleTextView = itemView.findViewById(R.id.card_movie_title_text_view);
        mDateTextView = itemView.findViewById(R.id.card_movie_year_text_view);
        mOverflow = itemView.findViewById(R.id.overflow);
        mPosterImageView = itemView.findViewById(R.id.movie_card_poster_image_view);
        mCardView = itemView.findViewById(R.id.card_view);
    }

    public void bindMovie(final IMovie pMovie, final IMovieCardListener pMovieCardListener) {
        mMovie = pMovie;
        mTitleTextView.setText(mMovie.getTitle());
        mDateTextView.setText(mMovie.getReleaseYear());
        mOverflow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View pView) {
                pMovieCardListener.onCardOverflowClick(pMovie, pView);
            }
        });
        mCardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View pView) {
                pMovieCardListener.onCardClick(pMovie);
            }
        });

        ImageLoaderWrapper.loadImage(Constants.ApiValues.IMAGE_LOADING_BASE_URL_342+pMovie.getPosterPath(),mPosterImageView);
    }
}
