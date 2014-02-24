package com.WrapHeightGridView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
    private final WrapHeightGridViewOnClickListener mWrapHeightGridViewOnClickListener;

    public WrapHeightGridViewAdapter() {
        super();
        mWrapHeightGridViewOnClickListener = null;
    }

    public WrapHeightGridViewAdapter(final WrapHeightGridViewOnClickListener wrapHeightGridViewOnClickListener) {
        super();
        mWrapHeightGridViewOnClickListener = wrapHeightGridViewOnClickListener;
    }

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
    public int getItemViewType(int position) {
        if (mAdapter == null) {
            return 0;
        }

        final int startPosition = position * mColumns;
        int type = 0;

        for (int index = startPosition; index < startPosition + mColumns; index++) {
            final int columnIndex = index - startPosition;
            final int typeCount = getAdapterViewTypeCount();
            final int typeAtIndex = getTypeAtIndex(index);
            type += typeAtIndex * Math.pow(typeCount, columnIndex);
        }

        return type;
    }

    private int getAdapterViewTypeCount() {
        if (mAdapter == null) {
            return 1;
        }

        return mAdapter.getViewTypeCount() + 1;
    }

    private int getTypeAtIndex(final int index) {
        final int typeCount = getAdapterViewTypeCount();
        final int count = mAdapter.getCount();

        if (index < count) {
            return mAdapter.getItemViewType(index);
        }

        return typeCount - 1;
    }

    @Override
    public int getViewTypeCount() {
        if (mAdapter == null) {
            return 1;
        }

        final int viewTypeCount = getAdapterViewTypeCount();
        final double typeCount = Math.pow(viewTypeCount, mColumns);
        return (int) typeCount;
    }

    @Override
    public View getView(final int position, final View view, final ViewGroup viewGroup) {
        final boolean isValidRecycle = view == null || view instanceof LinearLayout;

        if (!isValidRecycle || mAdapter == null) {
            return null;
        }

        final boolean isRecycled = view != null;
        final Context context = viewGroup.getContext();
        final LinearLayout linearLayout = getLinearLayout(view, context);

        final int count = mAdapter.getCount();
        final int convertedPosition = position * mColumns;

        for (int index = 0; index + convertedPosition < count && index < mColumns; index++) {
            final int viewPosition = convertedPosition + index;
            final long viewId = mAdapter.getItemId(viewPosition);

            if (isRecycled) {
                final View child = linearLayout.getChildAt(index);
                final View convertedView = mAdapter.getView(viewPosition, child, viewGroup);
                setChildOnClickListener(convertedView, viewPosition, viewId);
                if (convertedView != view) {
                    view.setOnClickListener(null);
                    linearLayout.removeViewAt(index);
                    linearLayout.addView(convertedView, index);
                }
            } else {
                final View convertedView = mAdapter.getView(viewPosition, null, viewGroup);
                setChildOnClickListener(convertedView, viewPosition, viewId);
                linearLayout.addView(convertedView, index);
            }
        }


        return linearLayout;
    }

    private void setChildOnClickListener(final View view, final int position, final long id) {
        final WrapHeightGridViewOnClickListener wrapHeightGridViewOnClickListener = mWrapHeightGridViewOnClickListener;
        if (wrapHeightGridViewOnClickListener == null){
            return;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                wrapHeightGridViewOnClickListener.onItemClick(view, position, id);
            }
        });
    }

    private LinearLayout getLinearLayout(final View view, final Context context) {
        if (view == null) {
            final LinearLayout linearLayout = new LinearLayout(context);
            final AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            return linearLayout;
        }

        return (LinearLayout) view;
    }

}
