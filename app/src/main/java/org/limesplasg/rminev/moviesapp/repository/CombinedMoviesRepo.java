package org.limesplasg.rminev.moviesapp.repository;

import android.content.Context;

import org.limesplasg.rminev.moviesapp.model.Category;

import java.util.List;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class CombinedMoviesRepo implements MoviesRepository {

    private RemoteMoviesRepo mRemoteRepo;

    private LocalMoviesRepo mLocalRepo;

    public CombinedMoviesRepo(Context context) {
        mLocalRepo = new LocalMoviesRepo(context);
        mRemoteRepo = new RemoteMoviesRepo();
    }

    @Override
    public void getCategories(final CategoriesLoadedCallback callback) {
        mRemoteRepo.getCategories(new CategoriesLoadedCallback() {
            @Override
            public void onCategoriesLoaded(List<Category> categories) {
                callback.onCategoriesLoaded(categories);
                mLocalRepo.save(categories);
            }
        });
    }
}
