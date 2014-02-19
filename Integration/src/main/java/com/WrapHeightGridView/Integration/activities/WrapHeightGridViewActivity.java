package com.WrapHeightGridView.Integration.activities;

import android.app.Activity;
import android.os.Bundle;

import com.WrapHeightGridView.Integration.R;
import com.WrapHeightGridView.WrapHeightGridView;
import com.WrapHeightGridView.Integration.adapters.IntegrationAdapter;

/**
 * Created by Emir Hasanbegovic on 2014-02-02.
 */
public class WrapHeightGridViewActivity extends Activity {

    private static final int COLUMNS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_wrap_height_grid_view);
        super.onCreate(savedInstanceState);

        final IntegrationAdapter integrationAdapter = new IntegrationAdapter();

        final WrapHeightGridView wrapHeightGridView = (WrapHeightGridView) findViewById(R.id.wrap_height_grid_view);
        wrapHeightGridView.setColumns(COLUMNS);
        wrapHeightGridView.setAdapter(integrationAdapter);

    }


}
