package com.mapthegraph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
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
			if (passEdit.getText().toString().length() < Utils.PASS_MIN_LENGTH)
			{
				Utils.simpleAlert(context, "Password should contain atleast " + Utils.PASS_MIN_LENGTH + " characters");
				
			}
			else {
			// Store Data in shared prefs, Check if user valid
				Editor edit = Utils.getEditor(SigninScreen.this);
				edit.putString("user", userEdit.getText().toString());
				edit.putString("pass", Utils.encrypt(passEdit.getText().toString()));
				edit.commit();
			// Show Waiting Alert
				Utils.toaster(context, "Signing In", Toast.LENGTH_SHORT);
				Boolean result = Utils.postUserCheck();
				
			// If invalid, show alert, refresh screen
				if(!result) {
					Utils.simpleAlert(context, "Sorry, username or password invalid. Create a new account or try again.");
					//Code for restarting activity
					Intent intent = getIntent();
					finish();
					startActivity(intent);
				}
			// Switch to next screen
				Intent home = new Intent(SigninScreen.this, Tabs.class);
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
		//Load layout
		setContentView(R.layout.signin);
		userEdit = (EditText) findViewById(R.id.si_user);
		passEdit = (EditText) findViewById(R.id.si_pass);
		loginButton = (Button) findViewById(R.id.si_btn);
		loginButton.setOnClickListener(loginUserListener);
	}
}
