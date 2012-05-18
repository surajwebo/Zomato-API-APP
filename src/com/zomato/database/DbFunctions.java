package com.zomato.database;

import java.util.ArrayList;
import com.zomato.helpers.Constants;
import com.zomato.models.CuisinesModel;
import com.zomato.models.RestaurantsModel;
import android.content.ContentValues;
import android.content.Context;

public class DbFunctions 
{
	private Context context;

	public DbFunctions(Context context) 
	{
		super();
	}

	/*public void storeDataInDB(String strCuisineId, String strCuisineName) 
	{
		CuisineDbAdapter dbAdapter = new CuisineDbAdapter(context, Constants.strCuisinesTableName);
		ContentValues Values = new ContentValues();
		Values.put(CuisineDbAdapter.CuisineId, strCuisineId);
		Values.put(CuisineDbAdapter.CuisineName, strCuisineName);
		dbAdapter.create(Values);
	}
	*/
	public void storeDataInDB(ArrayList<CuisinesModel>cuisineArrayList) 
	{
		CuisineDbAdapter dbAdapter = new CuisineDbAdapter(context, Constants.strCuisinesTableName);
		for(CuisinesModel cuisinesModel: cuisineArrayList)
		{
			ContentValues Values = new ContentValues();
			Values.put(CuisineDbAdapter.CuisineId, cuisinesModel.getId());
			Values.put(CuisineDbAdapter.CuisineName, cuisinesModel.getName());
			dbAdapter.create(Values);
		}
		
	}
	
	public void storeRestaurantDataInDB (ArrayList<RestaurantsModel>restaurantsArray)
	{
		RestaurantDbAdapter restaurantDbAdapter = new RestaurantDbAdapter(context,Constants.strRestaurantTableName);
		for (RestaurantsModel restaurantsModel: restaurantsArray)
		{
			ContentValues Values = new ContentValues();
			Values.put(RestaurantDbAdapter.RestaurantName, restaurantsModel.getName());
			Values.put(RestaurantDbAdapter.RestaurantAddr, restaurantsModel.getAddress());
			Values.put(RestaurantDbAdapter.RestaurantCuisine, restaurantsModel.getCuisines());
			Values.put(RestaurantDbAdapter.RestaurantRating, restaurantsModel.getRating());
			restaurantDbAdapter.create(Values);
		}
	}
	
}