package com.aeefire.github.pupilutil;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ImageView ivDisplay;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ivDisplay = (ImageView) v.findViewById(R.id.iv_display);
        return v;
    }

    public void setDisplayedDrawable(int resId) {
        setDisplayedDrawable(ContextCompat.getDrawable(getContext(), resId));
    }

    public void setDisplayedDrawable(Drawable img) {
        if (ivDisplay == null) {
            throw new IllegalStateException("We must have the \"display\" imageview set to display a drawable! Currently, it's null.");
        }
        ivDisplay.setImageDrawable(img);
    }
}
