package org.limesplasg.rminev.moviesapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.limesplasg.rminev.moviesapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MoviesViewModel mViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new MoviesViewModel(this);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mBinding.setViewModel(mViewModel);
        mBinding.executePendingBindings();
    }

    @Override
    public void onBackPressed() {
        if(mViewModel != null && mViewModel.onBackPressed())
            return;
        super.onBackPressed();
    }
}
