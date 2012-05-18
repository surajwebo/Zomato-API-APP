package com.zomato.ZomatoApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.zomato.helpers.AppStatus;
import com.zomato.helpers.Constants;

import com.zomato.restservices.RestClient;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class LocationActivity extends Activity {
	AppStatus appStatus;
	ProgressDialog progress;
	Handler handler;
	String cityName,cityId;
	double lat=18.52043, lon=73.856744;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Location Manager to fetch Current Location of Device
		LocationManager locateManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener locateListnr = new mylocationlistener();
		locateManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locateListnr);

		appStatus = AppStatus.getInstance(this);
		handler = new Handler();
		progress = new ProgressDialog(this);
		startAppLocationActvty();
	}

	private void startAppLocationActvty() 
	{
		Thread t = new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				showprogress(true, "Detecting Location", "In Process please wait...");
				if (appStatus.isOnline(LocationActivity.this)) 
				{
					Log.v("SPLASH_SCREEN", "App is online");
					if (appStatus.isRegistered()) 
					{
						Log.v("SPLASH_SCREEN", "Online !!!!");
						getCityInfo(); // Method to do web call and fetch City Id and City Name from Zomato API
						Intent intent = new Intent(LocationActivity.this,CuisinesActivity.class); // Create Intent object
						intent.putExtra("cityId",cityId); // Pass cityid to next Activity - CuisinesActivity
						
						startActivity(intent); 
						finish();
					} 					
				} 
				else 
				{
					Log.v("SPLASH_SCREEN", "Offline !!!!");
					//showprogress(false, "", "");
					//message("Please check you internet connection!!");
					Intent intent = new Intent(LocationActivity.this,CuisinesActivity.class); // Create Intent object
					startActivity(intent); 
					finish();
					
				}
				showprogress(false, "", "");
			}
		});
		t.start();
	}
	private void getCityInfo() 
	{	
		try 
		{			
			appStatus = AppStatus.getInstance(this);
			List<NameValuePair> params = new ArrayList<NameValuePair>(3);			
			params.add(new BasicNameValuePair("lat","18.52043"));
			params.add(new BasicNameValuePair("lon","73.856744"));
			params.add(new BasicNameValuePair("apikey", Constants.AUTH_KEY));

			String strJsonReponse = RestClient.getInstance(this).doApiCall(Constants.strLocation, "GET", params);
			
			Log.d("Data Response...",String.valueOf(strJsonReponse));

				JSONObject responseObj=new JSONObject(strJsonReponse);
				JSONObject localityObj=responseObj.getJSONObject("locality");
				cityName = localityObj.getString("city_name");
				cityId = localityObj.getString("city_id");
				Log.d("city Id", cityId);
				Log.d("city Name", cityName);
				
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
	void showprogress(final boolean show, final String title, final String msg) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (show) {
					if (progress != null) {
						progress.setTitle(title);
						progress.setMessage(msg);
						progress.show();
					}
				} else {
					progress.cancel();
					progress.dismiss();
				}
			}
		});
	}

	void message(String msg) {
		final String mesage = msg;
		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(LocationActivity.this, mesage, 8000);
				toast.show();
			}
		});
	}

public class mylocationlistener implements LocationListener{

	@Override
	public void onLocationChanged(Location location) {
		if (location != null) {
			Log.d("LOCATION CHANGED", location.getLatitude() + "");
			Log.d("LOCATION CHANGED", location.getLongitude() + "");
			//   Toast.makeText(SplashActivity.this,String.format("%f,%f",location.getLatitude(),location.getLongitude()),Toast.LENGTH_SHORT).show();
			Geocoder geocoder = new Geocoder(LocationActivity.this, Locale.getDefault());
			List<Address> addresses;
			try
			{
				addresses= geocoder.getFromLocation(18.52043,73.856744, 1);
				Log.d("Address: ", addresses.get(0).getLocality());
			}
			catch (Exception e) {
				// TODO: handle exception
			}


		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}

}

