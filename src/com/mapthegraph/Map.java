package com.mapthegraph;
import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class Map extends MapActivity {
	
	public MapView mapView;
	public MapController mapController;
	public Button updateButton;
	public LocationManager locationManager;
	String provider, address = "";
	
	private class GeocodeTask extends AsyncTask<Location, Integer, String> {

		@Override
		protected String doInBackground(Location... params) {
			// TODO Auto-generated method stub
			double latitude = params[0].getLatitude();
			double longitude = params[0].getLongitude();
			String temp = "";
			Geocoder gc = new Geocoder(Map.this);
			StringBuilder sb = new StringBuilder();

			try {
				List<Address> l = gc.getFromLocation(latitude, longitude, 1);
				Address addr = l.get(0);
				for (int i = 0; i < addr.getMaxAddressLineIndex(); i++) 
					  sb.append(addr.getAddressLine(i)).append("\n");
				sb.append(addr.getLocality()).append("\n");
				sb.append(addr.getPostalCode()).append("\n");
				sb.append(addr.getCountryName());
					
				temp = sb.toString();

				
			}
			catch (IOException e){
				
			}
			//return reverse geocoded address
			return temp;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			address = result;
		}

		
	}
	private static GeocodeTask geo;

	private OnClickListener updateClick = new OnClickListener() {
		
		public void onClick(View v) {
			
			// Open New Update Activity
			Intent share = new Intent(Map.this, ShareLocation.class);
			share.putExtra("Address", address);
			startActivity(share);
		}
	};
	
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.map_layout);
		mapView = (MapView) findViewById(R.id.map_view);
		updateButton = (Button) findViewById(R.id.update_button);
		updateButton.setOnClickListener(updateClick);
		map_initialize();
	}

	private void map_initialize() {
		mapController = mapView.getController();
		mapController.setZoom(17);
		
		// Get Current Location
		
		String context = Context.LOCATION_SERVICE;
		
		locationManager = (LocationManager)getSystemService(context);
		Criteria criteria = new Criteria();
		
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		
		provider = locationManager.getBestProvider(criteria, true);
		
		Location location = locationManager.getLastKnownLocation(provider);
		LocationListener locationListener = new LocationListener() {
			
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			public void onLocationChanged(Location location) {
				mapController.setCenter(new GeoPoint((int)(location.getLatitude()*1E6), (int) (location.getLongitude()*1E6)));
				geo.cancel(true);
				geo.execute();
						
			
			}
		};
		locationManager.requestLocationUpdates(provider, 2000, 30, locationListener);

		mapController.setCenter(new GeoPoint((int)(location.getLatitude()*1E6), (int) (location.getLongitude()*1E6)));
	

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
