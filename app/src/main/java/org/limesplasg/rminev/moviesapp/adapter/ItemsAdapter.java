package org.limesplasg.rminev.moviesapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.limesplasg.rminev.moviesapp.R;
import org.limesplasg.rminev.moviesapp.model.SimpleItem;
import org.limesplasg.rminev.moviesapp.repository.image.ImageLoadr;

import java.util.List;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

public class ItemsAdapter extends ArrayAdapter<SimpleItem> {

        private ImageLoadr mImageLoader;

        public ItemsAdapter(Context context, List<SimpleItem> items,ImageLoadr imageLoadr) {
            super(context, R.layout.category_view,R.id.category_view_label, items);
            mImageLoader = imageLoadr;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            SimpleItem item = getItem(position);
            View view = super.getView(position, convertView, parent);
            ((TextView)view.findViewById(R.id.category_view_label)).setText(item.getName());

            ImageView imageView = (ImageView)view.findViewById(R.id.category_view_image);

            if(item.getImageUri() !=null) {

                imageView.setVisibility(View.VISIBLE);
                mImageLoader.loadImage(imageView,item.getImageUri());

            }else {
                imageView.setVisibility(View.GONE);
            }
            return view;
        }

}
