package com.firen.awesomeblocks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class GameplayActivity extends Activity {
	private Board board;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.board = new Board();
		setContentView(new GameplaySurface(this, this.board));
	}

//	@Override
//	public void onBackPressed() {
//		super.onBackPressed();
//		AlertDialog alert = new AlertDialog.Builder(this).create();
//		alert.setTitle("SURE?");
//		alert.show();
//		alert.setButton("YES", new OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//	}
	
}
