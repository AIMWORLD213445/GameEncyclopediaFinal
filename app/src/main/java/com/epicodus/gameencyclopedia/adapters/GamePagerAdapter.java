package com.epicodus.gameencyclopedia.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.gameencyclopedia.models.Game;
import com.epicodus.gameencyclopedia.ui.GameDetailFragment;

import java.util.ArrayList;

public class GamePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Game> mGames;

    public GamePagerAdapter(FragmentManager fmanage, ArrayList<Game> games) {
        super(fmanage);
        mGames = games;
    }
    @Override
    public Fragment getItem(int position) {
        return GameDetailFragment.newInstance(mGames.get(position));
    }

    @Override
    public int getCount() {
        return mGames.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mGames.get(position).getName();
    }


}
