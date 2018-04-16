package com.raylang.solarsystemalmanac5;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BodyImageFragment extends Fragment
        implements View.OnClickListener {

    private OnBodyImageCallback mListener;

    public BodyImageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_body_image, container, false);

        // attach a click handler to the image view (won't work if set in layout file, unsure why)
        ImageView iv = view.findViewById(R.id.ivBodyImageView);
        iv.setOnClickListener(this); // the click calls the container's callback method

        return view;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onBodyImageClick();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBodyImageCallback) {
            mListener = (OnBodyImageCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBodyImageCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnBodyImageCallback {
        void onBodyImageClick();
    }
}
