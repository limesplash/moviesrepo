package org.limesplasg.rminev.moviesapp.repository.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class RemoteImageLoadr implements ImageLoadr {

    private static final String BASE_URL = "https://tvc.mobiletv.bg/";

    private Retrofit mRetrofit;

    private ImageApiInterface mImageApiInterface;

    private Retrofit getRetrofit(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    private ImageApiInterface getImageApiInterface() {
        if(mImageApiInterface == null)
            mImageApiInterface = getRetrofit().create(ImageApiInterface.class);
        return mImageApiInterface;
    }

    public void loadImage(final ImageView imageView, String imageUri) {

        Call<ResponseBody> call = getImageApiInterface().getImage(imageUri);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // display the image data in a ImageView or save it
                        Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                        imageView.setImageBitmap(bm);
                    } else {
                        // TODO
                    }
                } else {
                    // TODO
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private interface ImageApiInterface{

        @GET("sxm/images/subcategory/{imageUri}")
        Call<ResponseBody> getImage(@Path("imageUri") String imageUri);
    }
}

