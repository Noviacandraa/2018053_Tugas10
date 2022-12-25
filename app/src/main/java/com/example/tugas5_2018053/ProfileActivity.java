package com.example.tugas5_2018053;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.tugas5_2018053.databinding.ActivityProfileBinding;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setup view binding
        binding =
                ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Action Bar
        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_alarm) {
                    Intent a = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(a);
                } else if (id == R.id.nav_restapi) {
                    Intent a = new Intent(ProfileActivity.this,
                            ApiActivity.class);
                    startActivity(a);
                }
                else if (id == R.id.nav_display){
                    Intent a = new Intent(ProfileActivity.this,
                            MainActivity2.class);
                    startActivity(a);
                }
                else if (id == R.id.nav_profile){
                    Intent a = new Intent(ProfileActivity.this,
                            MainActivity1.class);
                    startActivity(a);
                }
                else if (id == R.id.nav_logout){
                    Intent a = new Intent(ProfileActivity.this,
                            LoginActivity.class);
                    startActivity(a);
                }
                return true;
            }
        });
        preferences = getSharedPreferences("AndroidHiveLogin", 0);
        editor = preferences.edit();
        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
