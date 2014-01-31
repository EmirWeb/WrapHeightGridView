package com.WrapHeightGridView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Emir Hasanbegovic on 2014-01-31.
 */
@RunWith(RobolectricTestRunner.class)
public class WrapHeightGridViewAdapterGetCountTests {


    class CountBaseAdapter extends BaseAdapter{

        private final int mCount;

        public CountBaseAdapter(final int count){
            mCount = count;
        }

        @Override
        public int getCount() {
            return mCount;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }

    @Test
    public void getCountWithNoAdapter(){
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final int count = wrapHeightGridViewAdapter.getCount();
        assertThat(count).isEqualTo(0);
    }

    @Test
    public void getCountWithEmptyAdapter(){
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new CountBaseAdapter(0);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        final int count = wrapHeightGridViewAdapter.getCount();
        assertThat(count).isEqualTo(0);
    }

    @Test
    public void getCountWithFullRows(){
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new CountBaseAdapter(5);
        wrapHeightGridViewAdapter.setColumns(5);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        final int count = wrapHeightGridViewAdapter.getCount();
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void getCountWithRemainderRows(){
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new CountBaseAdapter(6);
        wrapHeightGridViewAdapter.setColumns(5);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        final int count = wrapHeightGridViewAdapter.getCount();
        assertThat(count).isEqualTo(2);
    }

    @Test
      public void getCountWithRemainderRow(){
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new CountBaseAdapter(4);
        wrapHeightGridViewAdapter.setColumns(5);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        final int count = wrapHeightGridViewAdapter.getCount();
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void getCountWithNegativeColumnCount(){
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new CountBaseAdapter(4);
        wrapHeightGridViewAdapter.setColumns(-1);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        final int count = wrapHeightGridViewAdapter.getCount();
        assertThat(count).isEqualTo(4);
    }

    @Test
    public void getCountWithNegativeAdapterCount(){
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new CountBaseAdapter(-1);
        wrapHeightGridViewAdapter.setColumns(1);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        final int count = wrapHeightGridViewAdapter.getCount();
        assertThat(count).isEqualTo(0);
    }

    @Test
    public void getCountWithNegativeZeroColumn(){
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new CountBaseAdapter(5);
        wrapHeightGridViewAdapter.setColumns(0);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        final int count = wrapHeightGridViewAdapter.getCount();
        assertThat(count).isEqualTo(5);
    }

}
