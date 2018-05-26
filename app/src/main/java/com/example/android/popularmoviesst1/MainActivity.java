package com.example.android.popularmoviesst1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private final static String MOVIE_POPULAR = "popular";
    private final static String MOVIE_TOP_RATED = "top_rated";
    private MovieFragment moviesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int mainActivityLayout = R.layout.activity_main;
        setContentView(mainActivityLayout);

        //Get Movie Fragment ID inflated in the Main Activity
        moviesFragment = (MovieFragment) getFragmentManager().findFragmentById(R.id.movie_fragment);
    }

    //Handling Menus
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort_popularity:
                moviesFragment.setSortChoice(MOVIE_POPULAR);
                moviesFragment.loadMovieData();
                break;
            case R.id.menu_sort_top_rated:
                moviesFragment.setSortChoice(MOVIE_TOP_RATED);
                moviesFragment.loadMovieData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
