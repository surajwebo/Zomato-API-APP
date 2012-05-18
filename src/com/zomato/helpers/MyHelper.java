package com.zomato.helpers;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "ZomatoDatabase";
	private static final int DATABASE_VERSION = 1;
	
	public MyHelper(Activity context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		
		//	db.execSQL("CREATE TABLE cuisines (cuisines TEXT);");
			
			String createSql = "CREATE TABLE cuisines (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "cuisinesid TEXT	 , "
					+ "cuisinesname TEXT );";
			Log.v("Zomato", "Creating Table cuisines : " + createSql);
			db.execSQL(createSql);
			
			createSql = null;
			
		    //db.execSQL("CREATE TABLE restaurants (restaurants TEXT);");
			
			createSql = "CREATE TABLE restaurants (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "restaurantname TEXT	 , "
				+ "restaurantaddr TEXT , "
				+ "restaurantcuisine TEXT, "
				+ "restaurantrating TEXT );";
			Log.v("Zomato", "Creating Table restaurants : " + createSql);
		db.execSQL(createSql);
								
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
