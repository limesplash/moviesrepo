package org.limesplasg.rminev.moviesapp.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.limesplasg.rminev.moviesapp.model.Category;
import org.limesplasg.rminev.moviesapp.model.SubCategory;
import org.limesplasg.rminev.moviesapp.model.SubSubCategory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class LocalMoviesRepo extends OrmLiteSqliteOpenHelper implements MoviesRepository {

    private static final String DB_NAME = "categories.db";
    private static final int DB_VERSION = 1;

    private CategoriesLoadedCallback mCallback;

    public LocalMoviesRepo(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mCallback = mCallback;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, SubCategory.class);
            TableUtils.createTable(connectionSource, SubSubCategory.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) { }

    @Override
    public void getCategories(CategoriesLoadedCallback callback) {
        mCallback = callback;
        AsyncTask<Void,Void,List<Category>> readAsync = new AsyncTask<Void, Void, List<Category>>() {
            @Override
            protected List<Category> doInBackground(Void... params) {
                return readDb();
            }

            @Override
            protected void onPostExecute(List<Category> categories) {
                super.onPostExecute(categories);
                if(mCallback != null)
                    mCallback.onCategoriesLoaded(categories);
                mCallback = null;
            }
        };

        readAsync.execute();
    }

    private List<Category> readDb(){
        List<Category> categories = null;
        try {
            Dao<Category, Integer> categoriesDao = getDao(Category.class);
            categories = categoriesDao.queryForAll();

        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            //close();
        }

        return categories;
    }

    public void save(List<Category> categories){
        if(categories == null)
            return;

        try {
            Dao<Category, Integer> categoriesDao = getDao(Category.class);
            Dao<SubCategory,Integer> subCatDao =  getDao(SubCategory.class);
            Dao<SubSubCategory,Integer> subSubCatDao =  getDao(SubSubCategory.class);

            TableUtils.clearTable(getConnectionSource(),SubSubCategory.class);
            TableUtils.clearTable(getConnectionSource(),SubCategory.class);
            TableUtils.clearTable(getConnectionSource(),Category.class);

            for(Category c:categories){
                categoriesDao.create(c);

                if(c.hasSubCategories()){

                    for(SubCategory subCat:c.getSubCategories()) {
                        subCatDao.create(subCat);

                        if(subCat.hasSubCategories()){
                            for(SubSubCategory subSubCat:subCat.getSubCategories())
                                subSubCatDao.create(subSubCat);
                        }
                    }

                }
            }

        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            //close();
        }
    }
}
