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
				if (passEdit.getText().toString().length() < Utils.PASS_MIN_LENGTH)
				{
					Utils.simpleAlert(context, "Password should contain atleast " + Utils.PASS_MIN_LENGTH + " characters");
					
				}
				else {
			
				// Show Waiting Alert  
				SharedPreferences.Editor editor = Utils.getEditor(NewUser.this);
	        	editor.putBoolean("registered", true);
	        	editor.putString("user", userEdit.getText().toString());
	        	editor.putString("pass", Utils.encrypt(passEdit.getText().toString()));
	        	editor.commit();
	        	
	    		Utils.toaster(context, "Signing Up", Toast.LENGTH_SHORT);
				Boolean result = Utils.postUserCreate();
					        	
	        	if(!result) {
					Utils.simpleAlert(context, "Sorry, the user already exists, please try a different name or sign in instead.");
					//Code for restarting activity
					Intent intent = getIntent();
					finish();
					startActivity(intent);
				}
			// Switch to next screen
				Intent home = new Intent(NewUser.this, FBConnect.class);
				finish();
				startActivity(home); /* close existing activity before starting this */
				}
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