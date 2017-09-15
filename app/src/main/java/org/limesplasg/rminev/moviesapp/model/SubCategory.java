package org.limesplasg.rminev.moviesapp.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

@DatabaseTable
public class SubCategory implements SimpleItem {

    @DatabaseField(id = true)
    @SerializedName("subcategory_id")
    private int mId;

    @DatabaseField
    @SerializedName("subcategory_name")
    private String mName;

    @SerializedName("category_id")
    private int mCategoryId;

    @DatabaseField
    @SerializedName("cover")
    private String mCoverUri;

    @ForeignCollectionField(eager = true)
    @SerializedName("subcategories")
    private Collection<SubSubCategory> mSubCategories;

    @DatabaseField(foreign = true)
    private Category mCategory;

    public boolean hasSubCategories() {
        return mSubCategories != null && mSubCategories.size() > 0;
    }

    public Collection<SubSubCategory> getSubCategories() {
        return mSubCategories;
    }

    public int getCategoryId(){
        return mCategoryId;
    }

    public String getImageUri() {
        return mCoverUri;
    }

    public String getName() {
        return mName;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }

}
