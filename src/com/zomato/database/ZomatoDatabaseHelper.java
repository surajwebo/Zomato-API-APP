package com.zomato.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ZomatoDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "dbZomato";
	private static final int DATABASE_VERSION = 1;

	public ZomatoDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		try 
		{
			String createSql = "CREATE TABLE Cuisines (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "cuisinesid Text	 , "
					+ "cuisinesname TEXT );";
			Log.v("Zomato", "Creating Cuisine table: " + createSql);
			db.execSQL(createSql);
			
			createSql = null;
			createSql = "CREATE TABLE Restaurants (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "restaurantname TEXT , "
					+ "restaurantaddr TEXT , "
					+ "restaurantcuisine TEXT, "
					+ "restaurantrating TEXT );";
			Log.v("Zomato", "Creating Restaurant Table: " + createSql);
			db.execSQL(createSql);
	
		} catch (SQLException e) 
		{
			e.printStackTrace();
			Log.e("Zomato", "Database creation failed: " + e.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w("Zomato", "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS victories");
		onCreate(db);
	}
}
