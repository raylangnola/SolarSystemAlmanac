package com.raylang.solarsystemalmanac5;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by raylang on 3/26/18.
 */

public class BodyListFragment extends ListFragment {

    private static final String ACTIVITY_TAG = "MainActivity";
    OnBodySelectedListener mListener;

    public BodyListFragment() {
    } // empty constructor req'd

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.w(ACTIVITY_TAG,"top of BodyListFragment onActivityCreated");
        ArrayList<Body> solarSystem = MainActivity.solarSystem;
        ArrayAdapter<Body> adapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_list_item_1, solarSystem);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Body b = MainActivity.solarSystem.get(position);
        mListener.onBodySelected(b);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnBodySelectedListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(
                    context.toString() + " must implement OnBodySelectedListener");
        }
    }

    // container activity must implement this interface
    public interface OnBodySelectedListener {
        void onBodySelected(Body b);
    }
}
