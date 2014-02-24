package com.WrapHeightGridView.Integration;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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
        sut = Robolectric.buildActivity(WrapHeightGridViewActivity.class).create().start().resume().visible().get();
    }

    @Test
    public void height_of_first_row_is_equal_to_highest_item() {
        final WrapHeightGridView wrapHeightGridView = (WrapHeightGridView) sut.findViewById(R.id.wrap_height_grid_view);
        final View view = wrapHeightGridView.getChildAt(0);
        assertThat(view).isNotNull();

        final int height = view.getHeight();
        assertThat(height).isEqualTo(300);
    }

    private boolean mButtonClicked = false;


    /**
     * 02-20 09:34:57.651 D/WrapHeightGridView dispatchTouchEvent(18290): result: true event: MotionEvent { action=ACTION_DOWN, id[0]=0, x[0]=560.3208, y[0]=71.565765, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=8163182, downTime=8163182, deviceId=8, source=0x1002 }
     * 02-20 09:34:57.661 D/WrapHeightGridView onInterceptTouchEvent(18290): result: false event: MotionEvent { action=ACTION_UP, id[0]=0, x[0]=560.3208, y[0]=71.565765, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=8163215, downTime=8163182, deviceId=8, source=0x1002 }
     * 02-20 09:34:57.661 D/TouchLinearLayout onInterceptTouchEvent(18290): result: false event: MotionEvent { action=ACTION_UP, id[0]=0, x[0]=60.3208, y[0]=71.565765, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=8163215, downTime=8163182, deviceId=8, source=0x1002 }
     * 02-20 09:34:57.671 D/TouchButton onTouchEvent(18290): result: true event: MotionEvent { action=ACTION_UP, id[0]=0, x[0]=60.3208, y[0]=71.565765, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=8163215, downTime=8163182, deviceId=8, source=0x1002 }
     * 02-20 09:34:57.671 D/TouchLinearLayout dispatchTouchEvent(18290): result: true event: MotionEvent { action=ACTION_UP, id[0]=0, x[0]=60.3208, y[0]=71.565765, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=8163215, downTime=8163182, deviceId=8, source=0x1002 }
     * 02-20 09:34:57.671 D/WrapHeightGridView dispatchTouchEvent(18290): result: true event: MotionEvent { action=ACTION_UP, id[0]=0, x[0]=560.3208, y[0]=71.565765, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=8163215, downTime=8163182, deviceId=8, source=0x1002 }
     */

    @Test
    public void testTouchingTheFirstItem() {
        final WrapHeightGridView wrapHeightGridView = (WrapHeightGridView) sut.findViewById(R.id.wrap_height_grid_view);
        final View view = wrapHeightGridView.getChildAt(0);
        final LinearLayout linearLayout = (LinearLayout) view;
        final ViewGroup childViewGroup = (ViewGroup) linearLayout.getChildAt(2);
        final View child = childViewGroup.getChildAt(0);
        final Button button = (Button) child;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                assertThat(view).isSameAs(button);
                mButtonClicked = true;
            }
        });

        final long downtime = 1000;

        {
            // 02-20 09:34:57.631 D/WrapHeightGridView onInterceptTouchEvent(18290): result: false event: MotionEvent { action=ACTION_DOWN, id[0]=0, x[0]=560.3208, y[0]=71.565765, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=8163182, downTime=8163182, deviceId=8, source=0x1002 }
            final MotionEvent motionEvent = MotionEvent.obtain(downtime, downtime, MotionEvent.ACTION_DOWN, 560.3208f, 71.565765f, 0);
            final boolean result1 = wrapHeightGridView.onInterceptTouchEvent(motionEvent);
            assertThat(result1).isFalse();

            //02-20 09:34:57.651 D/TouchLinearLayout onInterceptTouchEvent(18290): result: false event: MotionEvent { action=ACTION_DOWN, id[0]=0, x[0]=60.3208, y[0]=71.565765, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=8163182, downTime=8163182, deviceId=8, source=0x1002 }
            motionEvent.setLocation(60.3208f, 71.565765f);
            final boolean result2 = linearLayout.onInterceptTouchEvent(motionEvent);
            assertThat(result2).isFalse();

            //02-20 09:34:57.651 D/TouchButton onTouchEvent(18290): result: true event: MotionEvent { action=ACTION_DOWN, id[0]=0, x[0]=60.3208, y[0]=71.565765, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=8163182, downTime=8163182, deviceId=8, source=0x1002 }
            final boolean result3 = button.onTouchEvent(motionEvent);
            assertThat(result3).isTrue();

            //02-20 09:34:57.651 D/TouchLinearLayout dispatchTouchEvent(18290): result: true event: MotionEvent { action=ACTION_DOWN, id[0]=0, x[0]=60.3208, y[0]=71.565765, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=8163182, downTime=8163182, deviceId=8, source=0x1002 }
            final boolean result4 = linearLayout.dispatchTouchEvent(motionEvent);
            System.out.print(motionEvent.toString());
            assertThat(result4).isTrue();

            //02-20 09:34:57.651 D/WrapHeightGridView dispatchTouchEvent(18290): result: true event: MotionEvent { action=ACTION_DOWN, id[0]=0, x[0]=560.3208, y[0]=71.565765, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=8163182, downTime=8163182, deviceId=8, source=0x1002 }
            motionEvent.setLocation(560.3208f, 71.565765f);

            final boolean result5 = wrapHeightGridView.dispatchTouchEvent(motionEvent);
            assertThat(result5).isTrue();
        }

        {
            final MotionEvent motionEvent = MotionEvent.obtain(downtime, downtime + 3, MotionEvent.ACTION_UP, 560.3208f, 71.565765f, 0);
            final boolean result1 = wrapHeightGridView.onInterceptTouchEvent(motionEvent);
            assertThat(result1).isFalse();

            motionEvent.setLocation(60.3208f, 71.565765f);
            final boolean result2 = linearLayout.onInterceptTouchEvent(motionEvent);
            assertThat(result2).isFalse();

            final boolean result3 = button.onTouchEvent(motionEvent);
            assertThat(result3).isTrue();

            final boolean result4 = linearLayout.dispatchTouchEvent(motionEvent);
            assertThat(result4).isTrue();

            motionEvent.setLocation(560.3208f, 71.565765f);
            final boolean result5 = wrapHeightGridView.dispatchTouchEvent(motionEvent);
            assertThat(result5).isTrue();
        }

        assertThat(mButtonClicked).isTrue();
    }

    public static void singleClick(Instrumentation instrumentation, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();

        MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, x, y, 0);
        instrumentation.sendPointerSync(event);
        instrumentation.waitForIdleSync();

        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, x, y, 0);
        instrumentation.sendPointerSync(event);
        instrumentation.waitForIdleSync();
    }

}
