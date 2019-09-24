package com.example.submission;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.submission.fragment.FavFragment;
import com.example.submission.fragment.MovieFragment;
import com.example.submission.fragment.SettingsFragment;
import com.example.submission.fragment.ShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.notification_settings, false);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean dailySwitchPref = sharedPref.getBoolean(SettingActivity.DAILY_KEY_SWITCH,false);
        Boolean releaseSwithcPref = sharedPref.getBoolean(SettingActivity.RELEASE_KEY_SWITHC, false);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_fav:
                        fragment = new FavFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName()).commit();
                        return true;
                    case R.id.navigation_movie:
                        fragment = new MovieFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName()).commit();
                        return true;
                    case R.id.navigation_tv:
                        fragment = new ShowFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName()).commit();
                        return true;

                }
                return false;
            }
        });

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.navigation_movie);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        else if (item.getItemId() == R.id.action_notification){
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }
        recreate();
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean dailySwitchPref = sharedPref.getBoolean(SettingActivity.DAILY_KEY_SWITCH,false);
        Boolean releaseSwithcPref = sharedPref.getBoolean(SettingActivity.RELEASE_KEY_SWITHC, false);

        if(dailySwitchPref){
            FirebaseMessaging.getInstance().subscribeToTopic("news");
        }
        else{
            FirebaseMessaging.getInstance().unsubscribeFromTopic("news");
        }
        if(releaseSwithcPref){
            FirebaseMessaging.getInstance().subscribeToTopic("release");
        }
        else{
            FirebaseMessaging.getInstance().unsubscribeFromTopic("release");
        }

    }
}
