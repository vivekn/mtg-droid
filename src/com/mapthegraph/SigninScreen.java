package com.mapthegraph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigninScreen extends Activity {
	
	public Context context = getApplicationContext();
	EditText userEdit, passEdit;
	Button loginButton;
	
	private OnClickListener loginUserListener = new OnClickListener() {
		public void onClick(View v) {
		    // Check Internet Connectivity
			if (Utils.isOnline(SigninScreen.this)){
							
			// Data validation
			
			// Send data to server
						
			// Show Waiting Alert
				Context context = getApplicationContext();
				String msg = "Signing In ...";
				int duration = Toast.LENGTH_LONG;
				Toast toast = Toast.makeText(context, msg, duration);
				toast.show();
				
			// Receive data from server
			
			// Show Success or Failure Alert
				toast.cancel();
				//...
			
			// Switch to next screen
				Intent home = new Intent(SigninScreen.this, Tabs.class);
				startActivity(home); /* close existing activity before starting this */
			}
			else {
				// Show Alert
				AlertDialog.Builder ad = Utils.simpleAlert(context, "A working internet connection is required. Please check your connectivity and try again later.");
				ad.show();
			}
		    }
	};


	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		//Load layout
		setContentView(R.layout.signin);
		userEdit = (EditText) findViewById(R.id.si_user);
		passEdit = (EditText) findViewById(R.id.si_pass);
		loginButton = (Button) findViewById(R.id.si_btn);
		loginButton.setOnClickListener(loginUserListener);
	}
}
