package ua.grupo7.pi.prettycloud.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.SharedValues;
import ua.grupo7.pi.prettycloud.communication.RestApi;
import ua.grupo7.pi.prettycloud.fragments.LoginFragment;
import ua.grupo7.pi.prettycloud.fragments.ProfileFragment;
import ua.grupo7.pi.prettycloud.fragments.SalonsFragment;
import ua.grupo7.pi.prettycloud.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private SharedValues sharedValues;
    private SharedPreferences sharedPreferences;
    private RestApi restApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        sharedValues = new SharedValues();
        sharedPreferences = getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        //sharedPreferences.edit().putString("USER","jose@hotmail.com").commit();
        //sharedPreferences.edit().putString("PASSWORD","jose_pedro").commit();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(drawerToggle);
        NavigationView navigationView = findViewById(R.id.nav_view);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Logged?",String.valueOf(sharedPreferences.getBoolean(sharedValues.isLoggedIn,false)));
        if (!sharedPreferences.getBoolean(sharedValues.isLoggedIn,false)){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = new LoginFragment();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new SalonsFragment();
        int id = item.getItemId();
        if (id == R.id.nav_salons) {
            fragment = new SalonsFragment();
        } else if (id == R.id.nav_profile) {
            fragment = new ProfileFragment();
        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();
        }
        else if (id == R.id.nav_logout){
            sharedPreferences.edit().putBoolean(sharedValues.isLoggedIn,false).apply();
        }
        if (!clientLoggedIn()) {
            fragment = new LoginFragment();
        }
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean clientLoggedIn(){
        return sharedPreferences.getBoolean(sharedValues.isLoggedIn,false);
    }

}
