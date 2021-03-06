package com.example.musicin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.musicin.data.Musician;
import com.example.musicin.fragments.HomeFragment;
import com.example.musicin.fragments.SearchEventsFragment;
import com.example.musicin.fragments.SearchMembersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MusicianHubActivity extends AppCompatActivity {

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_hub);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String email = getIntent().getStringExtra("email");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Bundle data = new Bundle();
        data.putString("email", email);
        Fragment homeFragment = new HomeFragment();
        homeFragment.setArguments(data);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, homeFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_search_members:
                        fragment = new SearchMembersFragment();
                        break;
                    case R.id.nav_search_events:
                        fragment = new SearchEventsFragment();
                        break;
                }
                Bundle data = new Bundle();
                data.putString("email", email);
                fragment.setArguments(data);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
                return true;
            }
        });

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.logout_item:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();
                        intent = new Intent(MusicianHubActivity.this, MainActivity.class);
                        intent.putExtra("email", email);
                        finishAffinity();
                        startActivity(intent);
                        break;
                    case R.id.profile_settings_item:
                        intent = new Intent(MusicianHubActivity.this, ProfileSettingsActivity.class);
                        startActivity(intent);
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}