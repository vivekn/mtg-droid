package com.mapthegraph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewUser extends Activity {
	public Context context = getApplicationContext();
	EditText userEdit, passEdit;
	Button registerButton;
	
	
	private OnClickListener createUserListener = new OnClickListener() {
		public void onClick(View v) {
		    // Check Internet Connectivity
			if (Utils.isOnline(NewUser.this)){
							
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
				SharedPreferences.Editor editor = Utils.getEditor(NewUser.this);
	        	editor.putBoolean("registered", true);
	        	editor.putString("user", userEdit.getText().toString());
	        	editor.putString("pass", Utils.encrypt(passEdit.getText().toString()));
	        	editor.commit();
			
			// Switch to next screen
				Intent home = new Intent(NewUser.this, FBConnect.class);
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
		setContentView(R.layout.signup);
		userEdit = (EditText) findViewById(R.id.su_user);
		passEdit = (EditText) findViewById(R.id.su_pass);
		registerButton = (Button) findViewById(R.id.su_btn);
		
		registerButton.setOnClickListener(createUserListener);
	}
	
}