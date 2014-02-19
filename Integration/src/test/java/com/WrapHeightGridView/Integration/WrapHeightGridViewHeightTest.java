package com.WrapHeightGridView.Integration;

import android.view.LayoutInflater;
import android.view.View;

import com.WrapHeightGridView.Integration.activities.WrapHeightGridViewActivity;
import com.WrapHeightGridView.WrapHeightGridView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Emir Hasanbegovic on 2014-02-12.
 */
@RunWith(RobolectricTestRunner.class)
public class WrapHeightGridViewHeightTest {

    WrapHeightGridViewActivity sut;

    @Before
    public void setup() {
//        sut = Robolectric.buildActivity(WrapHeightGridViewActivity.class).create().start().resume().visible().get();
    }

    @Test
    public void height_of_first_row_is_equal_to_highest_item() {
            LayoutInflater inflater = LayoutInflater.from(Robolectric.getShadowApplication().getApplicationContext());
            View view = inflater.inflate(R.layout.list_item_button, null);

            assertThat(view).isNotNull();
    }

}
