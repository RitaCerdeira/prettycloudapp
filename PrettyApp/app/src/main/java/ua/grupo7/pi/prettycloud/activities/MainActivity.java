package ua.grupo7.pi.prettycloud.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import ua.grupo7.pi.prettycloud.SharedValues;

import ua.grupo7.pi.prettycloud.fragments.Login;
import ua.grupo7.pi.prettycloud.fragments.Profile;
import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.fragments.Salon;
import ua.grupo7.pi.prettycloud.fragments.Settings;

public class MainActivity extends AppCompatActivity    implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private SharedValues sharedValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        sharedValues = new SharedValues();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(drawerToggle);
        NavigationView navigationView = findViewById(R.id.nav_view);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

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
        Fragment fragment;
        int id = item.getItemId();

        if (id == R.id.nav_salons) {
            fragment = new Salon();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
        } else if (id == R.id.nav_profile) {
            if (clientLoggedIn()) {fragment = new Profile();}
            else{ fragment = new Login(); }
            fragmentTransaction.replace(R.id.fragment_container,fragment);
        } else if (id == R.id.nav_settings) {
            fragment = new Settings();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean clientLoggedIn(){
        return sharedValues.isLoggedIn;
    }
}
