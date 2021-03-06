package io.exponential.androidnavigationdrawer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        // Set the app_bar Toolbar as the ActionBar
        // Note: Unlike MainActivity, we avoid creating a single use variable named 'appbar'.
        setSupportActionBar((Toolbar) findViewById(R.id.app_bar));

        // Add the BFragment Fragment via chaining so that we don't have to create single use
        // variables.
        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.container, BFragment.newInstance())
            .commit();

        // Enable up button on ActionBar to navigate to parent Activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_b, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
        Toast.makeText(BActivity.this, "Clicked search", Toast.LENGTH_SHORT).show();
    }
}
