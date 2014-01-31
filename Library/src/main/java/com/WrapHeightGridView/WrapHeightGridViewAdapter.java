package com.WrapHeightGridView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * Created by Emir Hasanbegovic on 2014-01-31.
 */
public class WrapHeightGridViewAdapter extends BaseAdapter {

    private static final int DEFAULT_COLUMN_COUNT = 1;

    private Adapter mAdapter;
    private int mColumns = DEFAULT_COLUMN_COUNT;


    public void setAdapter(final Adapter adapter) {
        mAdapter = adapter;
    }

    public void setColumns(final int columns) {
        mColumns = Math.max(DEFAULT_COLUMN_COUNT, columns);
    }

    @Override
    public int getCount() {
        if (mAdapter == null) {
            return 0;
        }

        final int count = Math.max(0, mAdapter.getCount());
        final int rows = count / mColumns;
        final boolean hasExtraRow = count % mColumns != 0;

        if (hasExtraRow) {
            return rows + 1;
        }

        return rows;
    }

    @Override
    public Object getItem(final int i) {
        return null;
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View view, final ViewGroup viewGroup) {
        final boolean isValidRecycle = view == null || view instanceof LinearLayout;

        if (!isValidRecycle || mAdapter == null) {
            return null;
        }

        LinearLayout linearLayout = (LinearLayout) view;

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.list_item_row, null);
        }

        final int count = mAdapter.getCount();
        final int childCount = linearLayout.getChildCount();
        final int convertedPosition = position * mColumns;

        // Recycle Views
        for (int index = 0; index < mColumns; index++) {
            final int viewPosition = convertedPosition + index;

            if (index < childCount) {

                final View child = linearLayout.getChildAt(index);

                if (viewPosition < count) {
                    final int visibility = child.getVisibility();
                    final boolean isVisible = visibility == View.VISIBLE;

                    if (!isVisible) {
                        child.setVisibility(View.VISIBLE);
                    }

                    final View convertedView = mAdapter.getView(viewPosition, child, viewGroup);
                    if (convertedView != view) {
                        linearLayout.removeViewAt(index);
                        linearLayout.addView(convertedView, index);
                    }
                } else {
                    child.setVisibility(View.GONE);
                }
            } else if (viewPosition < count) {
                final View convertedView = mAdapter.getView(position, null, viewGroup);
                convertedView.setVisibility(View.VISIBLE);
                linearLayout.addView(convertedView, index);
            }
        }

        return linearLayout;
    }
}
