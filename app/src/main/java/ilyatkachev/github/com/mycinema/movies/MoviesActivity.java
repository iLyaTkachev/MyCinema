package ilyatkachev.github.com.mycinema.movies;

import android.content.DialogInterface;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.util.ImageLoaderWrapper;
import ilyatkachev.github.com.mycinema.util.Injection;
import ilyatkachev.github.com.mycinema.util.ViewPagerAdapter;

public class MoviesActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    String[] movieGenresList;
    boolean[] checkedGenres;
    List<Integer> mUserGenres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ImageLoaderWrapper.setConfig(getCacheDir());

        // Set up the custom toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setupToolbar(toolbar);
        }

        // Set up the navigation drawer.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nav_view);
        if (nvDrawer != null) {
            setupDrawerContent(nvDrawer);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        movieGenresList = getResources().getStringArray(R.array.movie_genres);
        checkedGenres = new boolean[movieGenresList.length];
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private void setupToolbar(Toolbar pToolbar) {
        pToolbar.setTitle(R.string.empty_string);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.movies_activity_title);
        setSupportActionBar(pToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_actions, menu);
        MenuItem searchItem = menu.findItem(R.id.search_item);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here
                searchView.clearFocus();
                Toast.makeText(getApplicationContext(), "OnQueryTextSubmit", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplicationContext(), "OnQueryTextChanged", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.filter_item:
                showFilteringAlertDialog();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public void showFilteringAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose genres");
        builder.setMultiChoiceItems(movieGenresList, checkedGenres, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface pDialogInterface, int pPosition, boolean pIsCheched) {
                if (pIsCheched) {
                    mUserGenres.add(pPosition);
                } else {
                    mUserGenres.remove((Integer.valueOf(pPosition)));
                }
            }
        });
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface pDialogInterface, int pI) {
                String result = "";
                for (int i = 0; i < mUserGenres.size(); i++) {
                    result += movieGenresList[mUserGenres.get(i)];
                }
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface pDialogInterface, int pI) {
                pDialogInterface.dismiss();
            }
        });
        builder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface pDialogInterface, int pI) {
                for (int i = 0; i < checkedGenres.length; i++) {
                    checkedGenres[i] = false;
                    mUserGenres.clear();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setupViewPager(ViewPager viewPager) {
        //during rotation adapter first check FragementManager, if there no such fragment, then it takes fragment from the list
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(createFragmentWithPresenter(MoviesFilterType.POPULAR), getString(R.string.popular_tab));
        adapter.addFragment(createFragmentWithPresenter(MoviesFilterType.NOW_PLAYING), getString(R.string.in_theaters_tab));
        adapter.addFragment(createFragmentWithPresenter(MoviesFilterType.UPCOMING), getString(R.string.upcoming_tab));
        adapter.addFragment(createFragmentWithPresenter(MoviesFilterType.TOP_RATED), getString(R.string.top_rated_tab));
        viewPager.setAdapter(adapter);
    }

    private Fragment createFragmentWithPresenter(MoviesFilterType pFilterType) {
        MovieListFragment fragment = new MovieListFragment();
        MoviesPresenter moviesPresenter = new MoviesPresenter(
                fragment,
                Injection.provideGetMovies(getApplicationContext()),
                Injection.provideUseCaseHandler(),
                pFilterType);

        return fragment;
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        //Fragment fragment = null;
        //Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.movies_navigation_menu_item:
                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                //fragmentClass = FirstFragment.class;
                break;
            case R.id.tv_shows_navigation_menu_item:
                //fragmentClass = SecondFragment.class;
                break;
            case R.id.people_navigation_menu_item:
                //fragmentClass = ThirdFragment.class;
                break;
            case R.id.favourites_navigation_menu_item:
                //fragmentClass = ThirdFragment.class;
                break;
            case R.id.my_lists_navigation_menu_item:
                //fragmentClass = ThirdFragment.class;
                break;
            case R.id.account_navigation_menu_item:
                //fragmentClass = ThirdFragment.class;
                break;
            case R.id.advanced_navigation_menu_item:
                //fragmentClass = ThirdFragment.class;
                break;
            default:
                //fragmentClass = FirstFragment.class;
        }

        try {
            //fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        //FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
