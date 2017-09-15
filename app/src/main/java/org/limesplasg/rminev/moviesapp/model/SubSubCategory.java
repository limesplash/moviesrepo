package org.limesplasg.rminev.moviesapp.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

@DatabaseTable
public class SubSubCategory implements SimpleItem {

    @DatabaseField(id = true)
    @SerializedName("sub_subcategory_id")
    private int mId;

    @DatabaseField
    @SerializedName("sub_subcategory_name")
    private String mName;

    @SerializedName("subcategory_id")
    private int mSubSubCategoryId;

    @DatabaseField(foreign = true)
    private SubCategory mSubcategory;

    public String getName() { return mName; }

    @Override
    public String getImageUri() {
        return null;
    }

    public SubCategory getSubcategory() {
        return mSubcategory;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.mSubcategory = subcategory;
    }
}
