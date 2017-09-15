package org.limesplasg.rminev.moviesapp.repository;

import org.limesplasg.rminev.moviesapp.model.Category;

import java.util.List;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public interface MoviesRepository {

    public interface CategoriesLoadedCallback {
        public void onCategoriesLoaded(List<Category> categories);
    }

    public void getCategories(CategoriesLoadedCallback callback);

}
