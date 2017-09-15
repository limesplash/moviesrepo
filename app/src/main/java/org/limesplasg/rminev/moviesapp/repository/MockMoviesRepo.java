package org.limesplasg.rminev.moviesapp.repository;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class MockMoviesRepo implements MoviesRepository {

    private static final String SAMPLE = "{\"categories\": [{\"category_id\": "+
            "\"33\", \"category_name\": \"Анимация\"}],"+
            "\"subcategories\": [{\"subcategory_id\": \"63\", \"category_id\": \"33\",\"subcategory_name\": \"The Simpsons\"," +
            "\"cover\": \"f7e3175db14a9301d91b709ab9c6ba92d43262fe.png\",\"subcategories\": [{ \"sub_subcategory_id\": \"27\","+
            "\"subcategory_id\": \"63\",\"sub_subcategory_name\": \"Сезон 27\"}]}]}";

    @Override
    public void getCategories(CategoriesLoadedCallback callback) {

        GsonBuilder builder = new GsonBuilder();
        ApiResponse response = null;

        try {
            JsonReader rd = new JsonReader(new StringReader(SAMPLE));
            rd.setLenient(true);
            response = builder.create().fromJson(rd, ApiResponse.class);
        } catch(Exception e) {
            e.printStackTrace();
        };

        ApiResponse.mapSubCategories(response);
        callback.onCategoriesLoaded(response.categories);
    }
}
