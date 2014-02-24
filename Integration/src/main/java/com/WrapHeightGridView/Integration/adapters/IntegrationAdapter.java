package com.WrapHeightGridView.Integration.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.WrapHeightGridView.Integration.R;

/**
 * Created by Emir Hasanbegovic on 2014-02-02.
 */
public class IntegrationAdapter extends BaseAdapter {

    private static final int COUNT = 2000;
    private static final int TYPE_COUNT = 3;

    private enum Types {
        textView,
        imageView,
        button
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return position % TYPE_COUNT;
    }

    @Override
    public Object getItem(final int position) {
        return position;
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View view, final ViewGroup viewGroup) {
        final int type = getItemViewType(position);
        final Context context = viewGroup.getContext();


        final Types types = Types.values()[type];
        switch (types) {
            case button: {
                final LinearLayout linearLayout = (LinearLayout) getView(view, context, R.layout.list_item_button);
                final Button button = (Button) linearLayout.findViewById(R.id.button);
                button.setText(Integer.toString(position));
                return linearLayout;
            }
            case imageView: {
                final LinearLayout linearLayout = (LinearLayout) getView(view, context, R.layout.list_item_image_view);
                return linearLayout;
            }
            case textView:
            default: {
                final LinearLayout linearLayout = (LinearLayout) getView(view, context, R.layout.list_item_text_view);
                final TextView textView = (TextView) linearLayout.findViewById(R.id.text_view);
                textView.setText(Integer.toString(position));
                return linearLayout;
            }
        }
    }

    private View getView(final View view, final Context context, final int resourceId) {
        if (view != null) {
            return view;
        }

        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View newView = layoutInflater.inflate(resourceId, null);
        return newView;
    }
}
