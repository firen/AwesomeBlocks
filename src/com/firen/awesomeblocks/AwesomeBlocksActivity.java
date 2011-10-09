package com.firen.awesomeblocks;

import android.app.Activity;
import android.os.Bundle;

public class AwesomeBlocksActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameplaySurface gameplaySurface = new GameplaySurface(this);
        setContentView(gameplaySurface);
    }
}