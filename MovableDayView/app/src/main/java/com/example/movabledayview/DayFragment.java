package com.example.movabledayview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by adao1 on 7/27/2016.
 */
public class DayFragment extends android.support.v4.app.Fragment implements View.OnTouchListener{

    private static final String TAG = "DAY FRAGMENT";
    private static final int viewSize = 60;
    private static final int boundary = 35;

    private View view;
    private CardView movableView;
    private float dx;
    private int width;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_day_view,container,false);
        movableView = (CardView)view.findViewById(R.id.movable_id);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: in Fragment");
        movableView.setOnTouchListener(this);
        width = getScreenWidth();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                dx = view.getX() - motionEvent.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (motionEvent.getRawX()+dx >= width-convertDpToPx(boundary+viewSize)) return false;
                if (motionEvent.getRawX()+dx <= convertDpToPx(boundary)) return false;
                view.animate().x(motionEvent.getRawX()+dx).setDuration(0).start();
                break;
            default:
                return false;
        }
        return true;
    }

    private int getScreenWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private float convertDpToPx(int dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }
}
