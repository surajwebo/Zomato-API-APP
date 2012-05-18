package com.zomato.ZomatoApplication;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.zomato.adapters.CuisinesAdapter;
import com.zomato.database.CuisineDbAdapter;
import com.zomato.database.DbFunctions;
import com.zomato.helpers.AppStatus;
import com.zomato.helpers.Constants;
import com.zomato.helpers.MyHelper;
import com.zomato.models.CuisinesModel;
import com.zomato.parsers.CuisinesParser;
import com.zomato.restservices.RestClient;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CuisinesActivity extends Activity 
{
	DbFunctions dbFunctions = new DbFunctions(CuisinesActivity.this); // DbFunctions
	String cityId;
	ListView list;
	ArrayList<CuisinesModel> cuisineArray;
	AppStatus appStatus;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cuisines);
		CuisineDbAdapter dbAdapter = new CuisineDbAdapter(this, Constants.strCuisinesTableName);   // CuisineDbAdapter Database Adapter 
		appStatus = AppStatus.getInstance(this);
		Intent intent=getIntent();
		if (appStatus.isOnline(CuisinesActivity.this)) 
		{
			//deleteAll();
			Log.v("Cuisines_SCREEN", "App is online");
			cityId = (String)intent.getSerializableExtra("cityId");
			Log.d("City id: cuisines", cityId);
			getCuisinesInfo();
		}
		else
		{
			Log.v("Cuisines_SCREEN", "App is Offline");
			CuisineDbAdapter mdbAdapter = new CuisineDbAdapter(this, Constants.strCuisinesTableName); 
			cuisineArray  = mdbAdapter.getCuisinesModel();
			dispalyList();
		}
	}


	public void dispalyList()
	{
		list=(ListView)findViewById(R.id.list);

		CuisinesAdapter adapter = new CuisinesAdapter(CuisinesActivity.this, cuisineArray);
		dbFunctions.storeDataInDB(cuisineArray);  // Store cuisine array in Database table

		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3) 
			{
				CuisinesModel cuisine=cuisineArray.get(position);
				Intent intent=new Intent(CuisinesActivity.this,RestaurantActivity.class);
				intent.putExtra("cuisineId", cuisine.getId());
				intent.putExtra("cityId", cityId);
				startActivity(intent);					
			}
		});		
	}
	private void getCuisinesInfo() 
	{		
		try 
		{	
			/*Toast toast=Toast.makeText(this, "Processing please wait...", 2000);
	     	toast.setGravity(Gravity.TOP, 50, 50);
	     	toast.show();	*/
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);			
			params.add(new BasicNameValuePair("city_id",cityId));
			params.add(new BasicNameValuePair("apikey", Constants.AUTH_KEY));

			String strJsonReponse = RestClient.getInstance(this).doApiCall(Constants.strCuisines, "GET", params);

			Log.d("Cuisines Data Response",String.valueOf(strJsonReponse));
			CuisinesParser parser=new CuisinesParser();
			cuisineArray=parser.parseCuisines(strJsonReponse);

			dispalyList();
			// 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
