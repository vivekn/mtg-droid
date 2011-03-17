package com.mapthegraph;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.widget.ImageView;

public class Utils {
	public static String encrypt(String pass) {
		return pass;
	}
	
	public static boolean isOnline(Activity act) {
		 ConnectivityManager cm = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
		 return cm.getActiveNetworkInfo().isConnectedOrConnecting();
		}
	
	public static void loadPicture(ImageView imView, String urlString) {
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
	
	public static SharedPreferences.Editor getEditor(Activity a) {
		SharedPreferences mySharedPreferences = a.getSharedPreferences("mapTheGraph", Activity.MODE_PRIVATE); 
    	return mySharedPreferences.edit();
	}
	
	public static AlertDialog.Builder simpleAlert(Context c, String message) {
		AlertDialog.Builder ad = new AlertDialog.Builder(c);
		ad.setMessage(message);
	    ad.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int arg1){
				
			}
		});
	 return ad;   
	}
}
