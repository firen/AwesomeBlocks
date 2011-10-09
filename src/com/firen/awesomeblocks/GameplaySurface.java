package com.firen.awesomeblocks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

public class GameplaySurface extends SurfaceView {

	public GameplaySurface(Context context) {
		super(context);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawText("IAMAWESOME", 30, 30, new Paint());
		invalidate();
	}

}
