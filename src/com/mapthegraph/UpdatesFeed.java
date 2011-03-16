package com.mapthegraph;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

class Update {
	public String name, picture, update;
	public Update(String name, String picture, String update) {
		this.name = name;
		this.picture = picture;
		this.update = update;
	}
}

public class UpdatesFeed extends Activity{
	
	public Update[] getFeed() {
		Update array[] = null; 
		
		// get feed from server
		
		return array;
	}
	
	public void loadPicture(ImageView imView, String urlString) {
		try {
			URL url = new URL(urlString);
			Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
			imView.setImageBitmap(bitmap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		

	}
	
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		TextView title = new TextView(this);
		title.setText("Updates from Friends");
		title.setTextSize(40);
		
		Update feed[] = getFeed();
		
		int lHeight = LinearLayout.LayoutParams.FILL_PARENT;
		int lWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
		ll.setLayoutParams(new LayoutParams(lWidth, lHeight));
		
		for (Update update : feed ) {
			UpdateView x = new UpdateView(this);
			x.nameField.setText(update.name);
			x.statusField.setText(update.update);
			loadPicture(x.profilePicture, update.picture);
			ll.addView(x, new LayoutParams(lHeight, lWidth));
			
		}
		
		setContentView(ll);
		
	}
}
