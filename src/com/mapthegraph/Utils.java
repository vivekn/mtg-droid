package com.mapthegraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.mapthegraph.models.Update;
import com.mapthegraph.models.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.widget.ImageView;
import android.widget.Toast;

//TODO: All the post methods

public class Utils {
	protected static final int PASS_MIN_LENGTH = 6;
	public static Activity activity = new Activity(); 
	
	public static String encrypt(String pass) {
		return pass;
	}
	
	public static boolean isOnline(Activity act) {
		 ConnectivityManager cm = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
		 return cm.getActiveNetworkInfo().isConnectedOrConnecting();
		}
	
	public static void loadPicture(ImageView imView, String user) {
		try {
			URL url = new URL(user);
			Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
			imView.setImageBitmap(bitmap);
		} catch (MalformedURLException e) {
			
		} catch (IOException e) {
			
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
	
	public static String inputStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();
	    
	    // Wrap a BufferedReader around the InputStream
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));

	    // Read response until the end
	    try {
			while ((line = rd.readLine()) != null) { 
			    total.append(line); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    // Return full string
	    return total.toString();
	}

	public static void updateBaseUrl() {
		// TODO
	}
	
	public static String postData(String url, List<NameValuePair> l) {
		 // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    SharedPreferences s = activity.getPreferences(Activity.MODE_PRIVATE);
	    String baseUrl = s.getString("base_url", "TODO:INSERT DEFAULT URL HERE");
	    HttpPost httppost = new HttpPost(baseUrl + url);
	    String contents = null;
	    try {
	        // Add your data
	        httppost.setEntity(new UrlEncodedFormEntity(l));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        contents = inputStreamToString(response.getEntity().getContent());
	    } catch (ClientProtocolException e) {
	        
	    } catch (IOException e) {
	    }

		return contents;
	}
	
	public static Update[] updatesFromString(String dump) {
		Gson gson = new Gson();
		return gson.fromJson(dump, Update[].class);
	}
	
	public static void toaster(Context a, String message, int duration) {
	        Toast.makeText(a, message, duration)
	             .show();
	    }

	public static Boolean postUserCheck() {
		List<NameValuePair> l = new ArrayList<NameValuePair>(2);
    	SharedPreferences s = activity.getPreferences(Activity.MODE_PRIVATE);
	    String user = s.getString("user", "");
	    String pass = s.getString("pass", "");

		l.add(new BasicNameValuePair("userid", user));
		l.add(new BasicNameValuePair("pass_hash", pass));
		String response = postData("check", l);
		return response=="Success"?true:false;
	}

	public static Boolean postUserCreate() {
		List<NameValuePair> l = new ArrayList<NameValuePair>(2);
    	SharedPreferences s = activity.getPreferences(Activity.MODE_PRIVATE);
	    String user = s.getString("user", "");
	    String pass = s.getString("pass", "");

		l.add(new BasicNameValuePair("userid", user));
		l.add(new BasicNameValuePair("pass_hash", pass));
		String response = postData("create", l);
		return response=="Success"?true:false;
	}

	public static void postUpdateFB_friends() {
		// TODO Auto-generated method stub
		
	}

	public static void postUpdateFB_id() {
		List<NameValuePair> l = new ArrayList<NameValuePair>(2);
    	SharedPreferences s = activity.getPreferences(Activity.MODE_PRIVATE);
	    String user = s.getString("user", "");
	    String pass = s.getString("pass", "");
	    String uid = s.getString("fb_uid", ""); //TODO Get this from FB Singleton

		l.add(new BasicNameValuePair("userid", user));
		l.add(new BasicNameValuePair("pass_hash", pass));
		l.add(new BasicNameValuePair("fb_uid", uid));
		postData("fb_save_uid", l);
		
	}

	public static Update[] getFeed() {
		List<NameValuePair> l = new ArrayList<NameValuePair>(2);
    	SharedPreferences s = activity.getPreferences(Activity.MODE_PRIVATE);
	    String user = s.getString("user", "");
	    String pass = s.getString("pass", "");

		l.add(new BasicNameValuePair("userid", user));
		l.add(new BasicNameValuePair("pass_hash", pass));
		return updatesFromString(postData("feed/", l));
	}

	
}
