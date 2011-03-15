package com.mapthegraph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class NewUser extends Activity {
	public Context context = getApplicationContext();
	
	private OnClickListener createUserListener = new OnClickListener() {
		public void onClick(View v) {
		    // Check Internet Connectivity
			if (isOnline()){
							
			// Data validation
			
			// Send data to server
						
			// Show Waiting Alert
				Context context = getApplicationContext();
				String msg = "Creating New User ...";
				int duration = Toast.LENGTH_LONG;
				Toast toast = Toast.makeText(context, msg, duration);
				toast.show();
				
			// Receive data from server
			
			// Show Success or Failure Alert
				toast.cancel();
				//...
				SharedPreferences mySharedPreferences = getSharedPreferences("mapTheGraph", Activity.MODE_PRIVATE); 
	        	SharedPreferences.Editor editor = mySharedPreferences.edit();
	        	editor.putBoolean("registered", true);
	        	// Add username pass
	        	editor.commit();
			
			// Switch to next screen
				Intent home = new Intent(NewUser.this, FBConnect.class);
				startActivity(home); /* close existing activity before starting this */
			}
			else {
				// Show Alert
				AlertDialog.Builder ad = new AlertDialog.Builder(context);
				ad.setMessage("A working internet connection is required. Please check your connectivity and try again later.");
				ad.setNegativeButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface d, int arg1){
						
					}
				});
			}
		    }
	};
	
	public boolean isOnline() {
		 ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		 return cm.getActiveNetworkInfo().isConnectedOrConnecting();
		}

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
	}
}
