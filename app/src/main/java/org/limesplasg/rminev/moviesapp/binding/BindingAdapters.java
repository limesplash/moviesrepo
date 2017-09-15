package org.limesplasg.rminev.moviesapp.binding;

import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.v4.view.ViewPager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.limesplasg.rminev.moviesapp.adapter.PagerAdapter;

/**
 * Created by Radoslav Minev on 15.9.2017 г..
 */

@InverseBindingMethods({
        @InverseBindingMethod(type = ViewPager.class,
                attribute = "current_page",
                method = "getCurrentItem",
                event="OnPageChange")
})
public class BindingAdapters {

    @android.databinding.BindingAdapter("current_page")
    public static final void setCurrentPage(ViewPager pager, int page){
        pager.setCurrentItem(page);
    }


    @android.databinding.BindingAdapter(requireAll = false, value = {"current_pageAttrChanged","OnPageChange"})
    public static void setListeners(ViewPager pager, final ViewPager.OnPageChangeListener listener,
                                    final InverseBindingListener inverseBindingListener) {
        ViewPager.OnPageChangeListener newListener;

        if (inverseBindingListener == null) {
            newListener = listener;
        } else {
            newListener = new ViewPager.OnPageChangeListener() {


                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

                @Override
                public void onPageSelected(int position) {
                    if (listener != null) {
                        listener.onPageSelected(position);
                    }
                    inverseBindingListener.onChange();
                }

                @Override
                public void onPageScrollStateChanged(int state) {}
            };
        }
        //TODO remove old listeners if any
        if (newListener != null) {
            pager.addOnPageChangeListener(newListener);
        }
    }

    @android.databinding.BindingAdapter("adapter")
    public static final void setAdapter(ViewPager pager, PagerAdapter adapter){
        if(pager.getAdapter()!= null && pager.getAdapter().equals(adapter))
            return;
        pager.setAdapter(adapter);
    }

    @android.databinding.BindingAdapter("listAdapter")
    public static final void setListAdapter(ListView list, ListAdapter adapter){
        list.setAdapter(adapter);
    }

    @android.databinding.BindingAdapter("onItemClick")
    public static final void setListItemClick(ListView list, AdapterView.OnItemClickListener listener){
        list.setOnItemClickListener(listener);
    }

}
