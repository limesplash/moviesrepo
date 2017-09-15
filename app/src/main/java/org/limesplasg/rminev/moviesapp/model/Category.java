package org.limesplasg.rminev.moviesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.List;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

@DatabaseTable
public class Category implements SimpleItem {

    @DatabaseField(id = true)
    @SerializedName("category_id")
    private int mId;

    @DatabaseField
    @SerializedName("category_name")
    private String mName;

    @ForeignCollectionField(eager = true)
    @Expose(serialize = false)
    private Collection<SubCategory> mSubCategories;

    public boolean hasSubCategories() {
        return mSubCategories != null && mSubCategories.size() > 0;
    }

    public Collection<SubCategory> getSubCategories() {
        return mSubCategories;
    }

    public void setmSubCategories( List<SubCategory> subCategories) {
        mSubCategories = subCategories;
    }

    public int getId(){
        return mId;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getImageUri() {
        return null;
    }


}
