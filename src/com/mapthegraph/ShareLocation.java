package com.mapthegraph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ShareLocation extends Activity {
	
	private OnClickListener share = new OnClickListener() {
		
		public void onClick(View v) {
			// Send Data to Facebook
			// Show Alert
			AlertDialog.Builder ad = new AlertDialog.Builder(getApplicationContext());
			ad.setMessage("Updating your location");
			ad.setNegativeButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface d, int arg1){
					
				}
			});
			ad.show();
			// Success / Failure
			// Return to previous screen
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

}
