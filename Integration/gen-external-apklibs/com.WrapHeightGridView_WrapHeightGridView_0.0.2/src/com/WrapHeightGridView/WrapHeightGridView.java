package com.WrapHeightGridView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Emir Hasanbegovic on 2014-01-31.
 */
public class WrapHeightGridView extends ListView implements WrapHeightGridViewOnClickListener {
    private final WrapHeightGridViewAdapter mWrapHeightGridViewAdapter = new WrapHeightGridViewAdapter(this);
    private OnItemClickListener mOnItemClickListener;

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

    public void setAdapter(final int columns, final ListAdapter adapter) {
        mWrapHeightGridViewAdapter.setColumns(columns);
        setAdapter(adapter);
    }


    @Override
    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;

    }

    @Override
    public void onItemClick(final View view, final int position, final long id) {
        if (mOnItemClickListener == null) {
            return;
        }

        mOnItemClickListener.onItemClick(this, view, position, id);
    }

}
