package com.example.submission.fragment;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.submission.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    public SettingsFragment(){

    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.notification_settings, rootKey);
    }

}
