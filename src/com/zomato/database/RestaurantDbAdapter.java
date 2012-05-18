package com.zomato.database;

import java.util.ArrayList;

import com.zomato.helpers.Constants;
import com.zomato.models.CuisinesModel;
import com.zomato.models.RestaurantsModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;



public class RestaurantDbAdapter extends DbAdapter
{
	public String strTableName = Constants.strRestaurantTableName;
	
	public RestaurantDbAdapter(Context context, String strTableName) 
	{
		super(context, strTableName);
		Log.i("Restaurant dBAdapter.......", "!!!!!!");
		this.strTableName=strTableName;
		setDbName();
		setDbColumns();
	}
	public static final String ROWID = "_id";
	public static final String RestaurantName = "restaurantname";
	public static final String RestaurantAddr = "restaurantaddr";
	public static final String RestaurantCuisine = "restaurantcuisine";
	public static final String RestaurantRating = "restaurantrating";
	
	@Override
	protected void setDbName() 
	{
		this.dbName = strTableName;
		Log.i("DB Name Set", dbName);
	}
	@Override
	protected void setDbColumns() 
	{
		this.dbColumns = new String[] { "_id", RestaurantName,RestaurantAddr,RestaurantCuisine,RestaurantRating };
		Log.i("Db Comolmn Set", dbColumns.toString());
	}
	
	ContentValues createContentValues() 
	{
		ContentValues Values = new ContentValues();
		return Values;
	}

	public long create(ContentValues Values) {

		return super.create(Values);
	}

	public boolean update(long rowId, ContentValues Values) {

		return super.update(rowId, Values);
	}
	
	public ArrayList<RestaurantsModel>getRestaurantsModels() 
	{
		Cursor cursor = this.fetchAll(null, null);
		
		ArrayList<RestaurantsModel>List = new ArrayList<RestaurantsModel>();

		while (cursor.moveToNext()) {	
			 String RestaurantName = cursor.getString(cursor.getColumnIndex("restaurantname"));
			 String RestaurantAddr = cursor.getString(cursor.getColumnIndex("restaurantaddr"));
			 String RestaurantCuisines = cursor.getString(cursor.getColumnIndex("restaurantcuisine"));
			 String RestaurantRating = cursor.getString(cursor.getColumnIndex("restaurantrating"));
			 
			 
			 RestaurantsModel model = new RestaurantsModel();
			 model.setName(RestaurantName);
			 model.setAddress(RestaurantAddr);
			 model.setCuisines(RestaurantCuisines);
			 model.setRating(RestaurantRating);
			 
			 Log.i("Cursor values", String.valueOf(RestaurantName));
			 List.add(model);
		}
		cursor.close();		
		return List;
	}

	public void deleteAll() {
		try {
			db.beginTransaction();
			this.delete();
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

	}

}
