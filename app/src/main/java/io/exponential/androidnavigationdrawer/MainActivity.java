package io.exponential.androidnavigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
    implements MainFragmentCallbacks, NavigationDrawerFragment.Callbacks {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ActionBar / Toolbar / app bar
        Toolbar appbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(appbar);

        // Add fragments
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        AMainFragment aMainFragment = AMainFragment.newInstance("Hi from MainActivity.onCreate");
        ft.add(R.id.container, aMainFragment);

        NavigationDrawerFragment navigationDrawer = NavigationDrawerFragment.newInstance("placeholder");
        ft.add(R.id.navigation_drawer, navigationDrawer);

        ft.commit();

        // NavigationDrawer
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, appbar,
            R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // Force onPrepareOptionsMenu() to be called
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Force onPrepareOptionsMenu() to be called
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Allows you to modify the options menu on the ActionBar when the NavigationDrawer is toggled.
     * Specifically, hide option menu icons when the NavigationDrawer is open.
     *
     * @param menu The Activity's menu.
     * @return Return true to display the menu.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // ref: https://developer.android.com/training/implementing-navigation/nav-drawer.html#OpenClose
        // TODO: Implement the show/hide of option menu icons

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search) {
            handleSearch();
            // Return true to inform Android that the event has been handled
            return true;
        }

        return super.onOptionsItemSelected(item);
        */

        // Shorter form of the above logic using a switch statement
        switch (item.getItemId()) {
            case R.id.action_settings:
                // handleSettings();
                return true;
            case R.id.action_search:
                handleSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleSearch() {
        Toast.makeText(MainActivity.this, "Clicked search", Toast.LENGTH_SHORT).show();
    }

    // MainFragmentCallbacks

    @Override
    public void displayBActivity() {
        // Avoid creating a single use variable named "intent" as we are not passing any values in
        // a bundle.
        startActivity(new Intent(MainActivity.this, BActivity.class));
    }

    // NavigationDrawerFragment.Callbacks

    @Override
    public void placeholderCallback(String placeholderArg) {
        // do nothing for now
    }
}
