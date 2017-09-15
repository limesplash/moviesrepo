package org.limesplasg.rminev.moviesapp.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.limesplasg.rminev.moviesapp.MoviesViewModel;
import org.limesplasg.rminev.moviesapp.R;
import org.limesplasg.rminev.moviesapp.databinding.CategoriesViewBinding;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class CategoriesFragment extends Fragment {

    private MoviesViewModel mViewModel;

    private int mPageIndex;

    public void setViewModel(MoviesViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setPageIndex(int index) {
        mPageIndex = index;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CategoriesViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.categories_view, container,false);

        binding.setViewModel(mViewModel);
        binding.setPageIndex(mPageIndex);

        return binding.getRoot();
    }
}
