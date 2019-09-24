package com.example.submission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.submission.fragment.SettingsFragment;

public class SettingActivity extends AppCompatActivity {

    public static  final String DAILY_KEY_SWITCH = "switch_daily";
    public static  final String RELEASE_KEY_SWITHC = "switch_release";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }
}
