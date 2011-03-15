package com.mapthegraph;

import com.facebook.android.Facebook;

public class FacebookSingleton {
	private static final FacebookSingleton INSTANCE = new FacebookSingleton();
	private static final Facebook facebook = new Facebook("207311602615822");
	private FacebookSingleton() {
		
	}
	
	public FacebookSingleton getInstance() {
		return INSTANCE;
	}

	public static Facebook getFacebook() {
		return facebook;
	}
}
