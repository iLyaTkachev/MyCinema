package ilyatkachev.github.com.mycinema.movieDetails;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.movies.domain.model.IMovie;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.Constants;

public class MovieDetailsActivity extends AppCompatActivity {

    private IMovie mMovie;

    private ImageView mPosterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        mMovie = (IMovie)intent.getSerializableExtra(Constants.MOVIE_OBJECT);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.movie_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.movie_details_collapsing_toolbar);
        collapsingToolbar.setTitle(mMovie.getTitle());

        loadBackdrop();

    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.movie_detail_backdrop_image_view);
        imageView.setImageResource(R.drawable.image_nav_drawer_account_background);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
