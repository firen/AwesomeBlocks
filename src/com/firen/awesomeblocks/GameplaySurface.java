package com.firen.awesomeblocks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class GameplaySurface extends View {

	public GameplaySurface(Context context) {
		super(context);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint p = new Paint();
		p.setColor(Color.WHITE);
		canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), p);
		p.setColor(Color.BLACK);
		canvas.drawText("IAMAWESOME", 30, 30, p);
	}

}
