package com.example.submission.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.submission.R;
import com.example.submission.fragment.FavoriteMovieFragment;
import com.example.submission.fragment.FavoriteTVFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FavoriteMovieFragment();
        } else {
            return new FavoriteTVFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.page1);
            case 1:
                return context.getString(R.string.page2);
            default:
                return null;
        }
    }
}
