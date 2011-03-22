package com.mapthegraph;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mapthegraph.models.Update;
import com.mapthegraph.views.UpdateView;

//TODO: Populate feed, picture loading from facebook

public class UpdatesFeed extends Activity{
	
	
	
	
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		TextView title = new TextView(this);
		title.setText("Updates from Friends");
		title.setTextSize(40);
			
		int lHeight = LinearLayout.LayoutParams.FILL_PARENT;
		int lWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
		ll.setLayoutParams(new LayoutParams(lWidth, lHeight));
		
		Utils.toaster(getApplicationContext(), "Loading Updates ...", Toast.LENGTH_LONG);
		
		Update[] feed = Utils.getFeed();
		
		for (Update update : feed ) {
			UpdateView x = new UpdateView(this);
			x.nameField.setText(update.user.name);
			x.statusField.setText(update.message);
			
			Utils.loadPicture(x.profilePicture, update.user.getImageUrl());
			ll.addView(x, new LayoutParams(lHeight, lWidth));
			
		}
		
		setContentView(ll);
		
	}
}
