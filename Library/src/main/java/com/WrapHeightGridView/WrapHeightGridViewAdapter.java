package com.WrapHeightGridView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;

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
        mColumns = columns;
    }

    @Override
    public int getCount() {
        if (mAdapter == null) {
            return 0;
        }

        final int count = mAdapter.getCount();
        final int rows = count / mColumns;
        final boolean hasExtraRow = count % mColumns != 0;

        if (hasExtraRow){
            return rows + 1;
        }

        return rows;
    }

    @Override
    public Object getItem(final int i) {
        return null;
    }

    @Override
    public long getItemId(final int i) {
        return 0;
    }

    @Override
    public View getView(final int i, final View view, final ViewGroup viewGroup) {
        return null;
    }
}
