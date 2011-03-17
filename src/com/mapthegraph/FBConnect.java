package com.mapthegraph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.facebook.android.*;
import com.facebook.android.Facebook.*;

import android.os.Bundle;

public class FBConnect extends Activity {
	
	public Context context = getApplicationContext();
	
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		
		
		FacebookSingleton.getFacebook().authorize(this, new String[] { "user_location", "publish_stream", "friends_location" },

			      new DialogListener() {
			           public void onComplete(Bundle values) {
			        	   // Launch Main Screen
						   SharedPreferences.Editor editor = Utils.getEditor(FBConnect.this);
			        	   editor.putBoolean("logged_in", true);
			        	   editor.commit();
			        	   
			        	   Intent mainScreen = new Intent(FBConnect.this, Tabs.class);
			        	   startActivity(mainScreen);
			           }
			           
			           public void onFacebookError(FacebookError error) {
			        	   //Display Alert, restart screen
			        	   AlertDialog.Builder ad = new AlertDialog.Builder(context);
			        	   ad.setTitle(error.getErrorType());
			        	   ad.setMessage(error.getMessage());
							ad.setNegativeButton("OK", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface d, int arg1){
									
								}
							});
			        	   ad.show();
			        	   
			           }
			           
			           public void onError(DialogError e) {
			        	   //Display Alert, restart screen
			        	   AlertDialog.Builder ad = new AlertDialog.Builder(context);
			        	   ad.setMessage(e.getMessage());
						   ad.setNegativeButton("OK", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface d, int arg1){
									
								}
							});
			        	   ad.show();
			           }
			           
			           public void onCancel() {}
			      }
			);

	}
}
