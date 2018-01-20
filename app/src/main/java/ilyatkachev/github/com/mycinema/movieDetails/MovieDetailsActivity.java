package ilyatkachev.github.com.mycinema.movieDetails;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.Constants;
import ilyatkachev.github.com.mycinema.util.ImageLoaderWrapper;

public class MovieDetailsActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private Movie mMovie;

    private ImageView mPosterImageView;
    private ImageView mBackdropImageView;

    private TextView mTitleTextView;
    private TextView mYearTextView;
    private TextView mRatingTextView;
    private TextView mOverviewTextView;
    private TextView mOverviewReadMoreTextView;
    private TextView mDetailsTextView;

    private RecyclerView mTrailerRecyclerView;
    //private TrailerAdapter mTrailerAdapter;

    private RecyclerView mCastRecyclerView;
    //private CastsAdapter mCastAdapter;

    private RecyclerView mSimilarMoviesRecyclerView;
    //private BriefsSmallAdapter mSimilarMoviesAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_details);

        final Intent intent = getIntent();
        mMovie = (Movie) intent.getSerializableExtra(Constants.MOVIE_OBJECT);

        final Toolbar toolbar = findViewById(R.id.movie_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCollapsingToolbarLayout = findViewById(R.id.movie_details_collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle("");

        mAppBarLayout = findViewById(R.id.movie_details_appbar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(final AppBarLayout appBarLayout, final int verticalOffset) {
                if (appBarLayout.getTotalScrollRange() + verticalOffset == 0) {
                    mCollapsingToolbarLayout.setTitle(mMovie.getTitle());
                } else {
                    mCollapsingToolbarLayout.setTitle("");
                }
            }
        });

        mPosterImageView = findViewById(R.id.movie_detail_poster_image_view);
        mBackdropImageView = findViewById(R.id.movie_detail_backdrop_image_view);

        final int mPosterWidth = (int) (getResources().getDisplayMetrics().widthPixels * 0.25);
        final int mPosterHeight = (int) (mPosterWidth / 0.66);
        final int mBackdropWidth = getResources().getDisplayMetrics().widthPixels;
        final int mBackdropHeight = (int) (mBackdropWidth / 1.77);

        mPosterImageView.getLayoutParams().width = mPosterWidth;
        mPosterImageView.getLayoutParams().height = mPosterHeight;

        mBackdropImageView.getLayoutParams().height = mBackdropHeight;

        mTitleTextView = findViewById(R.id.text_view_title_movie_detail);
        mYearTextView = findViewById(R.id.text_view_year_movie_detail);
        mRatingTextView = findViewById(R.id.text_view_rating_movie_detail);
        mOverviewTextView = findViewById(R.id.text_view_overview_movie_detail);
        mOverviewReadMoreTextView = findViewById(R.id.text_view_read_more_movie_detail);
        mDetailsTextView = findViewById(R.id.text_view_release_date);

        loadActivity();

    }

    private void loadActivity() {
        mTitleTextView.setText(mMovie.getTitle());
        mYearTextView.setText(mMovie.getReleaseYear());
        mRatingTextView.setText(String.valueOf(mMovie.getVoteAverage()));
        mOverviewTextView.setText(mMovie.getOverview());
        mDetailsTextView.setText(mMovie.getReleaseDate());
        ImageLoaderWrapper.loadImage(Constants.ApiValues.IMAGE_LOADING_BASE_URL_342 + mMovie.getPosterPath(), mPosterImageView);
        ImageLoaderWrapper.loadImage(Constants.ApiValues.IMAGE_LOADING_BASE_URL_1000 + mMovie.getBackdropPath(), mBackdropImageView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
