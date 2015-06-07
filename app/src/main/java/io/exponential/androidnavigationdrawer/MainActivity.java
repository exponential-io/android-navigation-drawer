package io.exponential.androidnavigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements MainFragmentCallbacks {

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

        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    @Override
    public void displayBActivity() {
        // Avoid creating a single use variable named "intent" as we are not passing any values in
        // a bundle.
        startActivity(new Intent(MainActivity.this, BActivity.class));
    }
}
