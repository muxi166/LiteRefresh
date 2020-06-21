package com.androidpi.literefresh.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewConfigurationCompat;

import com.androidpi.literefresh.dependency.DependencyManager;

public class IndicatorBehavior<V extends View>
        extends AnimationOffsetBehavior<V> {

    private final String TAG = "IndicatorBehavior";
    private static final int INVALID_POINTER = -1;

    private boolean isBeingDragged = false;
    private int touchSlop = -1;
    private int activePointerId = INVALID_POINTER;
    private VelocityTracker velocityTracker;

    private boolean isDraggable = true;

    public IndicatorBehavior(Context context) {
        super(context);
    }

    public IndicatorBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout parent, @NonNull V child, @NonNull MotionEvent ev) {
        if (!isDraggable)
            return super.onInterceptTouchEvent(parent, child, ev);
        if (touchSlop < 0) {
            touchSlop = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
        }
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_MOVE && isBeingDragged) {
            return true;
        }

        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                isBeingDragged = false;
                break;

        }
        return false;
    }

    @Override
    public boolean onTouchEvent(@NonNull CoordinatorLayout parent, @NonNull V child, @NonNull MotionEvent ev) {
        if (!isDraggable)
            return super.onTouchEvent(parent, child, ev);
        int action = ev.getAction();
        int actionIndex = ev.getActionIndex();
        int pointerId = ev.getPointerId(actionIndex);

        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                logPointer("down", actionIndex, pointerId);
                DependencyManager.INSTANCE.interact(child, this);
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                logPointer("down_pointer", actionIndex, pointerId);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                logPointer("pointer_up", actionIndex, pointerId);
                break;
                case MotionEvent.ACTION_MOVE:

                    break;
            case MotionEvent.ACTION_CANCEL:
                logPointer("cancel", actionIndex, pointerId);
                DependencyManager.INSTANCE.interact(child, this);
                break;
            case MotionEvent.ACTION_UP:
                logPointer("up", actionIndex, pointerId);
                DependencyManager.INSTANCE.interact(child, this);
                break;
        }
        return false;
    }

    private void ensureVelocityTracker() {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
    }

    private void logPointer(String action, int actionIndex, int pointerId) {
        Log.d(TAG, String.format(action + ": %d, %d", actionIndex, pointerId));
    }

    @Override
    public IndicatorConfiguration getConfiguration() {
        return (IndicatorConfiguration) super.getConfiguration();
    }

    @Override
    IndicatorConfiguration.Builder newConfigBuilder() {
        return new IndicatorConfiguration.Builder(getConfiguration());
    }

    @Override
    public IndicatorConfiguration.Builder with(@NonNull Context context) {
        return new IndicatorConfiguration.Builder(context, this, getConfiguration());
    }
}
