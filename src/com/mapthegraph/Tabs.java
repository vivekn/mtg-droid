package com.mapthegraph;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class Tabs extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 	super.onCreate(savedInstanceState);
    	    setContentView(R.layout.main);

    	    Resources res = getResources();
    	    TabHost tabHost = getTabHost();  // The activity TabHost
    	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
    	    Intent intent;  // Reusable Intent for each tab

    	    // Create an Intent to launch an Activity for the tab (to be reused)
    	    intent = new Intent().setClass(this, Map.class);

    	    // Initialize a TabSpec for each tab and add it to the TabHost
    	    spec = tabHost.newTabSpec("map").setIndicator("Map", res.getDrawable(R.drawable.ic_tab_map))
    	                  .setContent(intent);
    	    tabHost.addTab(spec);

    	    // Do the same for the other tabs
    	    intent = new Intent().setClass(this, UpdatesFeed.class);
    	    spec = tabHost.newTabSpec("upd").setIndicator("Updates", res.getDrawable(R.drawable.ic_tab_feed))
    	                  .setContent(intent);
    	    tabHost.addTab(spec);

    	    intent = new Intent().setClass(this, FriendLocator.class);
    	    spec = tabHost.newTabSpec("friends").setIndicator("Friends", res.getDrawable(R.drawable.ic_tab_update))
    	                  .setContent(intent);
    	    tabHost.addTab(spec);

    	    tabHost.setCurrentTab(2);

    }
}