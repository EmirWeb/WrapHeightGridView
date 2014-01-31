package com.WrapHeightGridView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Emir Hasanbegovic on 2014-01-31.
 */
@RunWith(RobolectricTestRunner.class)
public class WrapHeightGridViewAdapterGetViewTests {

    class RowAdapter extends BaseAdapter {


        private final int mCount;

        public RowAdapter(final int count) {
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
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                final Context context = Robolectric.getShadowApplication().getApplicationContext();
                view = new TextView(context);
            }

            view.setTag(i);

            return view;
        }
    }

    public int getNumberOfVisibleViews(final ViewGroup viewGroup) {
        if (viewGroup == null) {
            return 0;
        }

        int visibleCount = 0;
        final int childCount = viewGroup.getChildCount();
        for (int index = 0; index < childCount; index++) {
            final View view = viewGroup.getChildAt(index);
            if (view.getVisibility() == View.VISIBLE) {
                visibleCount++;
            }
        }

        return visibleCount;

    }

    final ViewGroup mViewGroup = new ViewGroup(Robolectric.getShadowApplication().getApplicationContext()) {

        @Override
        protected void onLayout(boolean b, int i, int i2, int i3, int i4) {

        }

    };

    @Test
    public void getViewWithOneItem() {
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new RowAdapter(1);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        wrapHeightGridViewAdapter.setColumns(1);
        final View view = wrapHeightGridViewAdapter.getView(0, null, mViewGroup);
        assertThat(view).isInstanceOf(LinearLayout.class);
        final LinearLayout linearLayout = (LinearLayout) view;

        final int childCount = linearLayout.getChildCount();
        assertThat(childCount).isEqualTo(1);

        final View child = linearLayout.getChildAt(0);
        final int position = (Integer) child.getTag();
        assertThat(position).isEqualTo(0);
    }

    @Test
    public void getViewWithOneFullRow() {
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new RowAdapter(2);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        wrapHeightGridViewAdapter.setColumns(2);
        final View view = wrapHeightGridViewAdapter.getView(0, null, mViewGroup);
        assertThat(view).isInstanceOf(LinearLayout.class);
        final LinearLayout linearLayout = (LinearLayout) view;

        final int childCount = linearLayout.getChildCount();
        assertThat(childCount).isEqualTo(2);
    }

    @Test
    public void getViewWithRemainderRow() {
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new RowAdapter(3);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        wrapHeightGridViewAdapter.setColumns(2);
        final View row0 = wrapHeightGridViewAdapter.getView(0, null, mViewGroup);
        final View row1 = wrapHeightGridViewAdapter.getView(1, null, mViewGroup);

        assertThat(row0).isInstanceOf(LinearLayout.class);
        assertThat(row1).isInstanceOf(LinearLayout.class);

        final LinearLayout linearLayout0 = (LinearLayout) row0;
        final LinearLayout linearLayout1 = (LinearLayout) row1;

        final int childCount0 = linearLayout0.getChildCount();
        assertThat(childCount0).isEqualTo(2);

        final int childCount1 = linearLayout1.getChildCount();
        assertThat(childCount1).isEqualTo(1);
    }

    @Test
    public void getViewWithRecycleRow() {
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new RowAdapter(3);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        wrapHeightGridViewAdapter.setColumns(2);
        final View row0 = wrapHeightGridViewAdapter.getView(0, null, mViewGroup);
        final View row1 = wrapHeightGridViewAdapter.getView(1, row0, mViewGroup);

        assertThat(row1).isInstanceOf(LinearLayout.class);

        final LinearLayout linearLayout = (LinearLayout) row1;


        final int visibleChildCount = getNumberOfVisibleViews(linearLayout);
        assertThat(visibleChildCount).isEqualTo(1);

    }
}
