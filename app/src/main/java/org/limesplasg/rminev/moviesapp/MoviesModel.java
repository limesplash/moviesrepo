package org.limesplasg.rminev.moviesapp;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import org.limesplasg.rminev.moviesapp.model.Category;
import org.limesplasg.rminev.moviesapp.model.SubCategory;
import org.limesplasg.rminev.moviesapp.repository.MoviesRepository;

import java.util.List;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class MoviesModel {

    public final ObservableArrayList<Category> categories = new ObservableArrayList<Category>();
    public ObservableField<Category> selectedCategpry;
    public ObservableField<SubCategory> selectedSubCategpry;


    private MoviesRepository mRepo;

    public MoviesModel(MoviesRepository repository) {
        mRepo = repository;

        selectedCategpry = new ObservableField<Category>();
        selectedSubCategpry = new ObservableField<SubCategory>();
    }

    public void loadData() {
        mRepo.getCategories(new MoviesRepository.CategoriesLoadedCallback() {
            @Override
            public void onCategoriesLoaded(List<Category> categories) {
                setCategories(categories);
            }
        });
    }

    private void setCategories(List<Category> categories) {

        this.categories.clear();

        if(categories != null)
            this.categories.addAll(categories);

    }

}