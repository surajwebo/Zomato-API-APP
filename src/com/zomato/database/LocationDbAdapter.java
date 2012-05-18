package com.zomato.database;


import com.zomato.helpers.Constants;
import com.zomato.models.LocationModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class LocationDbAdapter extends DbAdapter
{
	public String strTableName = Constants.strCuisinesTableName;
	
	public LocationDbAdapter(Context context, String strTableName) 
	{
		super(context, strTableName);
		Log.i("City dBAdapter.......", "!!!!!!");
		this.strTableName = strTableName;
		setDbName();
		setDbColumns();
	}
	public static final String ROWID = "_id";
	public static final String CityId = "cityid";
	public static final String CityName = "cityname";
	
	@Override
	protected void setDbName() 
	{
		this.dbName = strTableName;
		Log.i("DB Name Set", dbName);
	}
	@Override
	protected void setDbColumns() 
	{
		this.dbColumns = new String[] { "_id", CityId, CityName };
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

	public LocationModel getLocationModel(String CityId, Context context) //String CityName,
	{
		Cursor cursor = this.fetchAll("id=" + CityId, null );
		LocationModel locationModel = new LocationModel();
		return locationModel;		
	}
	
	public void deleteAll()
	{
		try {
			db.beginTransaction();
			this.delete();
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
}
