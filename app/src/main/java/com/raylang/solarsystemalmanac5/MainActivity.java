package com.raylang.solarsystemalmanac5;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements BodyListFragment.OnBodySelectedListener,
        BodyImageFragment.OnBodyImageCallback,
        BodyWebFragment.OnWebFragmentListener {

    private static final String ACTIVITY_TAG = "MainActivity";

    private FragmentManager fragMgr;
    BodyWebFragment webFragment;


    public static ArrayList<Body> solarSystem;
    public static Body selectedBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(ACTIVITY_TAG, "top of onCreate");
        setContentView(R.layout.activity_main_dynamic);
        Log.w(ACTIVITY_TAG, "set content for dynamic layout");

        solarSystem = readSolarSystemFromCSVfile();
        Log.w(ACTIVITY_TAG, "read solar system data from file");
        fragMgr = getFragmentManager();
        Log.w(ACTIVITY_TAG, "got fragment manager");

        webFragment = new BodyWebFragment();

        if (fragMgr.findFragmentById(R.id.image_placeholder) == null) {
            FragmentTransaction ft = fragMgr.beginTransaction();
            Fragment f = new BodyImageFragment();
            ft.add(R.id.image_placeholder, f);
            ft.commit();
        }
        Log.w(ACTIVITY_TAG, "created image fragment");

        if (fragMgr.findFragmentById(R.id.list_placeholder) == null) {
            FragmentTransaction ft = fragMgr.beginTransaction();
            Fragment f = new BodyListFragment();
            ft.add(R.id.list_placeholder, f);
            ft.commit();
        }
        Log.w(ACTIVITY_TAG, "end of onCreate");
    }

    private ArrayList<Body> readSolarSystemFromCSVfile() {
        ArrayList<Body> bodies = new ArrayList<>();

        InputStream inStrm = getResources().openRawResource(R.raw.solar_system);
        BufferedReader bReader = new BufferedReader(
                new InputStreamReader(inStrm, Charset.defaultCharset()));
        try {
            bReader.readLine();
            String line;
            while ((line = bReader.readLine()) != null) {
                Body b = new Body(line);
                bodies.add(b);
            }
            bReader.close();
        } catch (IOException e) {
            Log.wtf(ACTIVITY_TAG, "could not find internal resource. pretty bad.");
            e.printStackTrace();
        }

        return bodies;
    }

    @Override
    public void onBodySelected(Body b) {
        selectedBody = b; // keep a handle to the selected body
        // setup image fragment to show the body's jpeg
        String[] tokens = b.getImageName().split("[\\\\.]"); // e.g. "Images\Mercury.jpg"
        String idStr = tokens[1].toLowerCase();
        int imageID = getResources().getIdentifier(idStr, "drawable", getPackageName());
        ImageView iv = findViewById(R.id.ivBodyImageView);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setImageResource(imageID);
    }

    @Override
    public void onBodyImageClick() {
        String toastStr = String.format(Locale.US,
                "top of onBodyImageClick, body is %s",
                selectedBody.getName());
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();

        FragmentTransaction ft = fragMgr.beginTransaction();
        ft.replace(R.id.image_placeholder,webFragment);
        ft.commit();

        WebView webView = findViewById(R.id.body_web_view);
        if (webView == null) {
            Toast.makeText(this, "null webView", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedBody != null) {
            webView.loadUrl(selectedBody.getURI());
            toastStr = String.format(Locale.US, "loaded \"%s\"",
                    selectedBody,selectedBody.getURI());
        } else {
            webView.loadUrl("http://en.wikipedia.org/wiki/Mercury_(planet)");
            toastStr = "loaded page about Mercury";
        }
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onWebFragmentInteraction(Uri uri) {

    }
}
