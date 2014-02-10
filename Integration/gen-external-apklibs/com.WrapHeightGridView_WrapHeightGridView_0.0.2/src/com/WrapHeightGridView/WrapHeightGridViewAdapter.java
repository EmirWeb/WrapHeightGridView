package com.WrapHeightGridView;

import android.content.Context;
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
    private TypesRecycler mTypesRecycler;
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

        final Context context = viewGroup.getContext();
        final LinearLayout linearLayout = getLinearLayout(view, context);
        final ViewHolder viewHolder = (ViewHolder) linearLayout.getTag();

        final int count = mAdapter.getCount();
        final int childCount = linearLayout.getChildCount();
        final int convertedPosition = position * mColumns;

        // Recycle Views
        for (int index = 0; index < mColumns; index++) {
            final int viewPosition = convertedPosition + index;
            final int type = mAdapter.getItemViewType(viewPosition);

            if (index < childCount) {

                final View child = linearLayout.getChildAt(index);

                if (viewPosition < count) {
                    final int visibility = child.getVisibility();
                    final boolean isVisible = visibility == View.VISIBLE;

                    if (!isVisible) {
                        child.setVisibility(View.VISIBLE);
                    }

                    final int oldType = viewHolder.getType(index);
                    final boolean isSameType = oldType == type;

                    if (isSameType) {
                        final View convertedView = mAdapter.getView(viewPosition, child, viewGroup);
                        if (convertedView != view) {
                            linearLayout.removeViewAt(index);
                            linearLayout.addView(convertedView, index);
                        }
                    } else {
                        if (mTypesRecycler == null) {
                            final int typeCount = getViewTypeCount();
                            mTypesRecycler = new TypesRecycler(typeCount);
                        }

                        final View recycledView = mTypesRecycler.getView(type);
                        mTypesRecycler.recycle(child, oldType); 
                        linearLayout.removeViewAt(index);

                        final View convertedView = mAdapter.getView(viewPosition, recycledView, viewGroup);
                        linearLayout.addView(convertedView, index);
                    }

                } else {
                    child.setVisibility(View.GONE);
                }
            } else if (viewPosition < count) {
                final View convertedView = mAdapter.getView(viewPosition, null, viewGroup);
                convertedView.setVisibility(View.VISIBLE);
                linearLayout.addView(convertedView, index);
            }
        }

        return linearLayout;
    }

    private LinearLayout getLinearLayout(final View view, final Context context) {
        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            final LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.list_item_row, null);
            final ViewHolder viewHolder = new ViewHolder(mColumns);
            linearLayout.setTag(viewHolder);
            return linearLayout;
        }

        return (LinearLayout) view;
    }

    private static class ViewHolder {
        public static final int EMPTY = -1;
        private int[] mViewTypes;

        public ViewHolder(final int columns) {
            mViewTypes = new int[columns];

            for (int index = 0; index < mViewTypes.length; index++) {
                mViewTypes[index] = EMPTY;
            }
        }

        public int getType(final int index) {
            return mViewTypes[index];
        }
    }
}
