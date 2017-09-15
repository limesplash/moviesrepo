package org.limesplasg.rminev.moviesapp;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.widget.ListAdapter;

import org.limesplasg.rminev.moviesapp.adapter.ItemsAdapter;
import org.limesplasg.rminev.moviesapp.adapter.PagerAdapter;
import org.limesplasg.rminev.moviesapp.model.Category;
import org.limesplasg.rminev.moviesapp.model.SimpleItem;
import org.limesplasg.rminev.moviesapp.model.SubCategory;
import org.limesplasg.rminev.moviesapp.repository.CombinedMoviesRepo;
import org.limesplasg.rminev.moviesapp.repository.LocalMoviesRepo;
import org.limesplasg.rminev.moviesapp.repository.MockMoviesRepo;
import org.limesplasg.rminev.moviesapp.repository.MoviesRepository;
import org.limesplasg.rminev.moviesapp.repository.RemoteMoviesRepo;
import org.limesplasg.rminev.moviesapp.repository.image.ImageLoadr;
import org.limesplasg.rminev.moviesapp.repository.image.RemoteImageLoadr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class MoviesViewModel extends BaseObservable {

    public ObservableInt selectedPage;

    private Activity mActivity;

    private MoviesModel mModel;

    private PagerAdapter mPagerAdapter;

    private ObservableList.OnListChangedCallback<ObservableList> mCategoriesChangeListener;

    private ImageLoadr mImageLoader;


    public MoviesViewModel(FragmentActivity activity) {
        mActivity = activity;
        mModel = new MoviesModel(getCorrectRepo());

        mPagerAdapter = new PagerAdapter(activity.getSupportFragmentManager(),this,1);

        selectedPage = new ObservableInt(0);

        mImageLoader = new RemoteImageLoadr();

        bindModel();
        loadData();
    }
    private MoviesRepository getCorrectRepo(){
        return isNetworkAvailable() ? new CombinedMoviesRepo(mActivity) : new LocalMoviesRepo(mActivity);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void bindModel() {
        if(mCategoriesChangeListener == null)
            mCategoriesChangeListener = new ObservableList.OnListChangedCallback<ObservableList>() {
                @Override
                public void onChanged(ObservableList sender) {
                    notifyChange();
                }

                @Override
                public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
                    notifyChange();
                }

                @Override
                public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                    notifyChange();
                }

                @Override
                public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
                    notifyChange();
                }

                @Override
                public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                    notifyChange();
                }
            };

        mModel.categories.addOnListChangedCallback(mCategoriesChangeListener);
    }

    public ListAdapter getAdapter(int pageIndex){

        List<SimpleItem> items = null;

        switch(pageIndex) {
            case 0:

                SimpleItem s = null;
                items = new ArrayList<SimpleItem>(Collections.nCopies(mModel.categories.size(),s));

                Collections.fill(items,null);
                Collections.copy(items,mModel.categories);

                break;
            case 1:
                if(mModel.selectedCategpry.get() !=null && mModel.selectedCategpry.get().hasSubCategories()) {
                    items = new ArrayList<SimpleItem>(mModel.selectedCategpry.get().getSubCategories());
                }
                break;
            case 2:
                if(mModel.selectedSubCategpry.get() !=null && mModel.selectedSubCategpry.get().hasSubCategories()) {
                    items = new ArrayList<SimpleItem>(mModel.selectedSubCategpry.get().getSubCategories());
                }
                break;
        }

        return items != null ? new ItemsAdapter(mActivity, items,mImageLoader) : null;
    }

    public PagerAdapter getPagerAdapter(){
        return mPagerAdapter;
    }

    public void itemClick(Object item){

        if(item instanceof Category) {
            mModel.selectedCategpry.set((Category) item);
            if( mModel.selectedCategpry.get().hasSubCategories() ) {
                mPagerAdapter.setCount(2);
                selectedPage.set(1);
            }
        }else if(item instanceof SubCategory){
            mModel.selectedSubCategpry.set((SubCategory) item);
            if(mModel.selectedSubCategpry.get().hasSubCategories()) {
                mPagerAdapter.setCount(3);
                selectedPage.set(2);
            }
        }else {
            return;
        }
        notifyChange();
    }


    private void loadData() {
        mModel.loadData();
    }

    public boolean onBackPressed() {
        if(selectedPage.get() > 0) {
            selectedPage.set(selectedPage.get() - 1);
            return true;
        }
        return false;
    }
}
