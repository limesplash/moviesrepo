package org.limesplasg.rminev.moviesapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.limesplasg.rminev.moviesapp.MoviesViewModel;
import org.limesplasg.rminev.moviesapp.fragment.CategoriesFragment;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class PagerAdapter extends FragmentStatePagerAdapter {


    private MoviesViewModel mViewModel;
    private int mCount;

    public PagerAdapter(FragmentManager fm, MoviesViewModel viewModel, int pageCount) {
        super(fm);
        mViewModel = viewModel;
        mCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setPageIndex(position);
        fragment.setViewModel(mViewModel);
        return fragment;
    }

    @Override
    public int getCount() {
        return mCount;
    }


    public void setCount(int count) {
        if(mCount != count) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}
