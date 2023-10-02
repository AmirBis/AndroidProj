package com.example.amirproj.user;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amirproj.R;
import com.example.amirproj.gallery;
import com.example.amirproj.home;
import com.example.amirproj.info;
import com.example.amirproj.profile;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    TextView username,email;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        fauth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FirebaseUser user = fauth.getCurrentUser();
        if(user != null){
            View header = navigationView.getHeaderView(0);
            username = header.findViewById(R.id.ttUserName);
            email = findViewById(R.id.etemail);
            username.setText(user.getDisplayName());
            email.setText(user.getEmail());
        }
        else {
            Intent i = new Intent(MainActivity.this, logInActivity.class);
            startActivity(i);
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home()).commit();
            navigationView.setCheckedItem(R.id.home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(R.id.home==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home()).commit();

        }
        else if(R.id.shoppingcart==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new gallery()).commit();
        }
        else if(R.id.info==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new info()).commit();
        }
        else if(R.id.login==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new profile()).commit();
        }
        else if(R.id.logout==item.getItemId()){
            startActivity(new Intent(this,logInActivity.class));
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
       }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}