package ilyatkachev.github.com.mycinema.movies;

import android.content.DialogInterface;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
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
import ilyatkachev.github.com.mycinema.util.Constants;
import ilyatkachev.github.com.mycinema.util.ImageLoaderWrapper;

public class MediaActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private MediaType mMediaType;
    private ViewPagerWrapper mViewPagerWrapper;
    private TextView mToolbarTitle;

    String[] movieGenresList;
    boolean[] checkedGenres;
    List<Integer> mUserGenres = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ImageLoaderWrapper.setConfig(getCacheDir());

        if (savedInstanceState != null) {
            mMediaType = (MediaType) savedInstanceState.getSerializable(Constants.TYPE_KEY);
        } else {
            mMediaType = MediaType.MOVIE;
        }
        setUpViewPagerWrapper();

        // Set up the custom toolbar.
        final Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setupToolbar(toolbar);
        }

        // Set up the navigation drawer.
        mDrawerLayout = findViewById(R.id.drawer_layout);

        final NavigationView nvDrawer = findViewById(R.id.nav_view);
        if (nvDrawer != null) {
            setupDrawerContent(nvDrawer);
        }

        //replaceFragmentInContainer(R.id.fragments_cont, new ViewPagerWrapper());

        movieGenresList = getResources().getStringArray(R.array.movie_genres);
        checkedGenres = new boolean[movieGenresList.length];
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(final MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private void setupToolbar(final Toolbar pToolbar) {
        pToolbar.setTitle(R.string.empty_string);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(mMediaType.toString());
        setSupportActionBar(pToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_actions, menu);
        final MenuItem searchItem = menu.findItem(R.id.search_item);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(final String query) {
                // perform query here
                searchView.clearFocus();
                Toast.makeText(getApplicationContext(), "OnQueryTextSubmit", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                Toast.makeText(getApplicationContext(), "OnQueryTextChanged", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose genres");
        builder.setMultiChoiceItems(movieGenresList, checkedGenres, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(final DialogInterface pDialogInterface, final int pPosition, final boolean pIsCheched) {
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
            public void onClick(final DialogInterface pDialogInterface, final int pI) {
                String result = "";
                for (int i = 0; i < mUserGenres.size(); i++) {
                    result += movieGenresList[mUserGenres.get(i)];
                }
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface pDialogInterface, final int pI) {
                pDialogInterface.dismiss();
            }
        });
        builder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface pDialogInterface, final int pI) {
                for (int i = 0; i < checkedGenres.length; i++) {
                    checkedGenres[i] = false;
                    mUserGenres.clear();
                }
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void selectDrawerItem(final MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.movies_navigation_menu_item:
                mMediaType = MediaType.MOVIE;
                updateActivity();
                setUpViewPagerWrapper();
                break;
            case R.id.tv_shows_navigation_menu_item:
                //Intent intent = new Intent(this, MediaActivity.class);
                //intent.putExtra(Constants.TYPE_KEY, MediaType.TV);
                //startActivity(intent);

                mMediaType = MediaType.TV;
                updateActivity();
                setUpViewPagerWrapper();

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

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }

    private void updateActivity() {
        mToolbarTitle.setText(mMediaType.toString());
    }

    private void setUpViewPagerWrapper(){
        mViewPagerWrapper = (ViewPagerWrapper) getSupportFragmentManager().findFragmentByTag(mMediaType.toString());
        if (mViewPagerWrapper == null){
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.TYPE_KEY, mMediaType);
            mViewPagerWrapper = new ViewPagerWrapper();
            mViewPagerWrapper.setArguments(bundle);
        }
        replaceFragmentInContainer(R.id.fragments_cont, mViewPagerWrapper);
    }

    private void replaceFragmentInContainer(final int pI, final Fragment pFragment) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(pI, pFragment, mMediaType.toString());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        outState.putSerializable(Constants.TYPE_KEY, mMediaType);
        super.onSaveInstanceState(outState);
    }

}
