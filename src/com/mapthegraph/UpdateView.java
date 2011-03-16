package com.mapthegraph;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UpdateView extends LinearLayout {
	
	TextView nameField, statusField;
	ImageView profilePicture;
	
	public OnClickListener showUpdateOnMap = new OnClickListener() {
		public void onClick(View v) {
			// Add code to display on a map
		};
	};
		
	

	public UpdateView(Context context) {
		super(context);
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.updateview, this, true);
		
		nameField = (TextView) findViewById(R.id.namefield);
		statusField = (TextView) findViewById(R.id.statusfield);
		profilePicture = (ImageView) findViewById(R.id.profilepic);
		
		setOnClickListener(showUpdateOnMap);
	}

}
