package com.mapthegraph;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ImageCache {
	public static Activity act = new Activity();
	public static Bitmap getBitmapFromId(String id) {
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(act.openFileInput(id));
			return bitmap;
		} catch (FileNotFoundException e) {
			
		}
		return null;
	}
	
	public static void storeBitmap(String url, String id){
		try {
			URL uUrl = new URL(url);
			Bitmap bitmap = BitmapFactory.decodeStream(uUrl.openStream());
			storeBitmap(bitmap, id);
		} catch (MalformedURLException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	public static void storeBitmap(Bitmap b, String id) {
		OutputStream stream = null;
		try {
			stream = act.openFileOutput(id, Context.MODE_PRIVATE);
			b.compress(Bitmap.CompressFormat.PNG, 100, stream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void reloadImage(String id) {
		
	}
	
	public static void clearImage(String id) {
		act.deleteFile(id);
	}
	
	public static void clearCache() {
		String list[] = act.fileList();
		for (String item: list) 
			clearImage(item);
	}

}
