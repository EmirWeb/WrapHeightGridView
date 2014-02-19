package com.WrapHeightGridView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
public class WrapHeightGridViewAdapterColumnTest {

    class RowAdapter extends BaseAdapter {


        private static final int TYPE_COUNT = 3;
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
        public int getItemViewType(int position) {
            return position % TYPE_COUNT;
        }

        @Override
        public int getViewTypeCount() {
            return TYPE_COUNT;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final int type = getItemViewType(i);
            if (view == null) {
                final Context context = Robolectric.getShadowApplication().getApplicationContext();
                switch (type) {
                    case 0:
                        view = new TextView(context);
                        break;
                    case 1:
                        view = new ImageView(context);
                        break;
                    case 2:
                    default:
                        view = new Button(context);
                }
            }
            view.setTag(type);

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
    public void getViewWith5ColumnsItem() {
        final WrapHeightGridViewAdapter wrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();
        final Adapter adapter = new RowAdapter(100);
        wrapHeightGridViewAdapter.setAdapter(adapter);
        wrapHeightGridViewAdapter.setColumns(5);
        final View view = wrapHeightGridViewAdapter.getView(0, null, mViewGroup);
        assertThat(view).isInstanceOf(LinearLayout.class);
        final LinearLayout linearLayout = (LinearLayout) view;
        final int columnCount = linearLayout.getChildCount();
        assertThat(columnCount).isEqualTo(5);
        int visibleItemCount = 0;
        for (int index = 0; index < columnCount; index++){
            final View child = linearLayout.getChildAt(index);
            if (child.getVisibility() == View.VISIBLE)
                visibleItemCount++;
        }
        assertThat(visibleItemCount).isEqualTo(5);
    }
}
