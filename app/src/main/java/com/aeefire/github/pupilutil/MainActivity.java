package com.aeefire.github.pupilutil;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    public static final boolean KEEP_SCREEN_AWAKE = true;
    private Drawable currentlyShownDrawable;

    private Drawable calibImage;
    private Drawable stopImage;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(fabClickListener);

        initImages();

        setScreenAwake(KEEP_SCREEN_AWAKE);
    }

    private void initImages() {
        calibImage = ContextCompat.getDrawable(this, R.drawable.pupil_calibration_marker_calib);
        stopImage = ContextCompat.getDrawable(this, R.drawable.pupil_calibration_marker_stop);
    }

    private View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Drawable imageToShow = getNextImageDrawable();
            if (imageToShow != null) {
                ((MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment)).setDisplayedDrawable(imageToShow);
                currentlyShownDrawable = imageToShow;

                fab.setImageDrawable(getNextImageDrawable());
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * @return the image to be shown on click on the FAB
     */
    public Drawable getNextImageDrawable() {
        if (currentlyShownDrawable != calibImage) {
            return calibImage;
        }
        return stopImage;
    }

    public void setScreenAwake(boolean screenAwake) {
        if (screenAwake) getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        else getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
