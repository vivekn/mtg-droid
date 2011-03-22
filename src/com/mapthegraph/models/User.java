package com.mapthegraph.models;

public class User {
	public String name;
	public String fb_uid;	
	
	public String getImageUrl() {
		return "http://graph.facebook.com/" + fb_uid + "/picture";
	}
}
