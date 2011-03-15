package com.mapthegraph;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class FirstTime extends Activity {
	
	private OnClickListener signinListener = new OnClickListener() {
		public void onClick(View v) {
		      // do something when the button is clicked
			Intent signinIntent = new Intent(FirstTime.this, SigninScreen.class);
			startActivity(signinIntent);
		    }
	};
	
	private OnClickListener newUserListener = new OnClickListener() {
		public void onClick(View v) {
		      // do something when the button is clicked
			Intent newUserIntent = new Intent(FirstTime.this, NewUser.class);
			startActivity(newUserIntent);
		    }
	};
	
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
	}
}