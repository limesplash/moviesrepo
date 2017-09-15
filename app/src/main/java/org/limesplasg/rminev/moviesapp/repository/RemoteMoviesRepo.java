package org.limesplasg.rminev.moviesapp.repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class RemoteMoviesRepo implements MoviesRepository {

    private static final String BASE_URL = "http://dev.mobiletv.bg/";
    private static final String USER = "veroun1@gmail.com";
    private static final String PASS = "test1";
    private static final String MODE = "categories";
    private static final String AUTH = "Basic dGVzdDE6dGVzdDE=";//base 64 encoded test1:test1

    private Retrofit mRetrofit;
    private ApiInterface mApiInterface;

    private Retrofit getRetrofit(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    private ApiInterface getApiInterface(){
        if(mApiInterface == null)
            mApiInterface = getRetrofit().create(ApiInterface.class);
        return mApiInterface;
    }

    @Override
    public void getCategories(final CategoriesLoadedCallback callback) {


        Call<ApiResponse> call = getApiInterface().getCategories(USER, PASS, MODE,AUTH);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse.mapSubCategories(response.body());
                callback.onCategoriesLoaded(response.body().categories);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                callback.onCategoriesLoaded(null);
                //TODO handle error
            }
        });
    }

    private interface ApiInterface{


        @GET("4P1/kidsvod/json.php")
        Call<ApiResponse> getCategories(@Query("user") String user, @Query("pass") String password, @Query("mode") String mode, @Header("Authorization") String auth);


    }
}
