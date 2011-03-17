package com.mapthegraph;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class StartController extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		SharedPreferences mySharedPreferences = getSharedPreferences("mapTheGraph", Activity.MODE_PRIVATE);
		Boolean logged_in = mySharedPreferences.getBoolean("logged_in", false);
		Intent intent;
		
		if (logged_in){
			intent = new Intent(StartController.this, Tabs.class);
		}
		
		else {
			intent = new Intent(StartController.this, FirstTime.class);
		}

		startActivity(intent);
	}
}
