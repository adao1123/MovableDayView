package com.example.movabledayview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by adao1 on 7/27/2016.
 */
public class DayFragment extends android.support.v4.app.Fragment implements View.OnTouchListener{

    private static final String TAG = "DAY FRAGMENT";
    private float dx;
    private int width;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_day_view,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: in Fragment ");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                dx = view.getX() - motionEvent.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                view.animate().x(motionEvent.getRawX()+dx).setDuration(0).start();
                break;
            default:
                return false;
        }
        return true;
    }
}
