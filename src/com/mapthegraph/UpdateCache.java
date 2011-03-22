package com.mapthegraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.mapthegraph.models.Update;

public class UpdateCache {
	public static int LIMIT  = 100;
	public static Gson g = new Gson();
	public static Activity activity = new Activity();
	
	
	public static Update[] getFriendsFeed() {
		try {
			FileInputStream inputStream = activity.openFileInput("updates.json");
			String streamToString = Utils.inputStreamToString(inputStream);
			return g.fromJson(streamToString, Update[].class);
		} catch (FileNotFoundException e) {
			
		}
		return null;	
	}
	
	public static void storeUpdates(Update[] updates) {
		try {
			FileOutputStream output = activity.openFileOutput("updates.json", Context.MODE_PRIVATE);
			OutputStreamWriter writer = new OutputStreamWriter(output);
			writer.write(g.toJson(updates, Update[].class));
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		
	}
	
	public static void storeSelfUpdate(Update update) {
		try {
			FileOutputStream output = activity.openFileOutput("selfupdate.json", Context.MODE_PRIVATE);
			OutputStreamWriter writer = new OutputStreamWriter(output);
			writer.write(g.toJson(update, Update.class));
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		
	}
	
	
	public static Update getLastUpdate() {
		try {
			FileInputStream inputStream = activity.openFileInput("selfupdate.json");
			String streamToString = Utils.inputStreamToString(inputStream);
			return g.fromJson(streamToString, Update.class);
		} catch (FileNotFoundException e) {
			
		}
		return null;
		
	}
	
	public static void purgeCache() {
		for(String s:activity.fileList()) 
			activity.deleteFile(s);
	}
}
