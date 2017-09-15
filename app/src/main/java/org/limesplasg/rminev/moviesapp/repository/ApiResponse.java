package org.limesplasg.rminev.moviesapp.repository;

import org.limesplasg.rminev.moviesapp.model.Category;
import org.limesplasg.rminev.moviesapp.model.SubCategory;
import org.limesplasg.rminev.moviesapp.model.SubSubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class ApiResponse {

    public static void mapSubCategories(ApiResponse response) {
        if (response == null || response.subcategories == null)
            return;

        //TODO optimize ...
        for (SubCategory subCat : response.subcategories) {
            int categoryId = subCat.getCategoryId();
            for (Category cat : response.categories) {
                if (cat.getId() == categoryId) {
                    if (cat.getSubCategories() == null)
                        cat.setmSubCategories(new ArrayList<SubCategory>());
                    cat.getSubCategories().add(subCat);
                    subCat.setCategory(cat);
                }
            }

            for(SubSubCategory subSubCat:subCat.getSubCategories()){
                subSubCat.setSubcategory(subCat);
            }
        }
    }


    public List<Category> categories;
    public List<SubCategory> subcategories;
}
