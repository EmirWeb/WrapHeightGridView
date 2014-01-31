package com.WrapHeightGridView;

import android.view.View;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Emir Hasanbegovic on 2014-01-31.
 */
public class TypesRecycler {

    private final Queue<View>[] mRecycler;

    public TypesRecycler(final int typeCount) {
        mRecycler = new Queue[typeCount];

        for (int index = 0; index < typeCount; index++) {
            final Queue<View> queue = new LinkedList<View>();
            mRecycler[index] = queue;
        }

    }

    public void recycle(final View view, final int type) {
        if (type >= mRecycler.length || type < 0) {
            return;
        }

        mRecycler[type].add(view);
    }

    public View getView(final int type) {
        if (type >= mRecycler.length || type < 0) {
            return null;
        }

        final Queue<View> queue = (Queue<View>) mRecycler[type];
        if (queue.isEmpty()) {
            return null;
        }

        return queue.remove();
    }

}
