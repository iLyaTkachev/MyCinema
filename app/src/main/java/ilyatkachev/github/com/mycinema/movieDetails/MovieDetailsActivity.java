package ilyatkachev.github.com.mycinema.movieDetails;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.data.ICinemaDataSource;
import ilyatkachev.github.com.mycinema.data.remote.CinemaRemoteDataSource;
import ilyatkachev.github.com.mycinema.movies.domain.model.Movie;
import ilyatkachev.github.com.mycinema.util.Constants;
import ilyatkachev.github.com.mycinema.util.executors.AppExecutors;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        final String activityTitle = intent.getStringExtra(Constants.ACTIVITY_TITLE);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.movie_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.movie_details_collapsing_toolbar);
        collapsingToolbar.setTitle(activityTitle);

        loadBackdrop();

        AppExecutors ex = new AppExecutors();
        CinemaRemoteDataSource.getINSTANCE(ex).getMovies(1, new ICinemaDataSource.LoadObjectsCallback<Movie>() {

            @Override
            public void onObjectsLoaded(List<Movie> pMovies) {
                Toast.makeText(getApplicationContext(),pMovies.get(0).getTitle(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDataNotAvailable() {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.movie_detail_backdrop);
        imageView.setImageResource(R.drawable.image_nav_drawer_account_background);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
