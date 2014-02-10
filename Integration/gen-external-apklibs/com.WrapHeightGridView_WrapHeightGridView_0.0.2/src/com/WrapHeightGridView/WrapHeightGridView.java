package com.WrapHeightGridView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Emir Hasanbegovic on 2014-01-31.
 */
public class WrapHeightGridView extends ListView {
    private final WrapHeightGridViewAdapter mWrapHeightGridViewAdapter = new WrapHeightGridViewAdapter();

    public WrapHeightGridView(Context context) {
        super(context);
    }

    public WrapHeightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapHeightGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(final ListAdapter adapter) {
        mWrapHeightGridViewAdapter.setAdapter(adapter);
        super.setAdapter(mWrapHeightGridViewAdapter);
    }

    public void setColumns(final int columns){
        mWrapHeightGridViewAdapter.setColumns(columns);
    }
}
