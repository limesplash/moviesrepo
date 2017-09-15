package org.limesplasg.rminev.moviesapp;

import android.databinding.ObservableArrayList;

import org.limesplasg.rminev.moviesapp.model.Category;
import org.limesplasg.rminev.moviesapp.repository.MoviesRepository;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class MoviewModel {

    public final ObservableArrayList<Category> categories = new ObservableArrayList<Category>();

    private MoviesRepository mRepo;

    public MoviewModel(MoviesRepository repository) {
        mRepo = repository;
    }

}
