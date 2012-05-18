package com.zomato.database;


import java.util.ArrayList;

import com.zomato.helpers.Constants;
import com.zomato.models.CuisinesModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class CuisineDbAdapter extends DbAdapter
{
	public String strTableName = Constants.strCuisinesTableName;
	
	public CuisineDbAdapter(Context context, String strTableName) 
	{
		super(context, strTableName);
		Log.i("Cuisine dBAdapter.......", "!!!!!!");
		this.strTableName=strTableName;
		setDbName();
		setDbColumns();
	}
	public static final String ROWID = "_id";
	public static final String CuisineId = "cuisinesid";
	public static final String CuisineName = "cuisinesname";
	
	@Override
	protected void setDbName() 
	{
		this.dbName = strTableName;
		Log.i("DB Name Set", dbName);
	}
	@Override
	protected void setDbColumns() 
	{
		this.dbColumns = new String[] { "_id", CuisineId, CuisineName };
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
	
	public ArrayList<CuisinesModel>getCuisinesModel() 
	{
		Cursor victoriesC = this.fetchAll(null, null);
		
		ArrayList<CuisinesModel> List = new ArrayList<CuisinesModel>();

		while (victoriesC.moveToNext()) {	
			 String CuisieneNAME = victoriesC.getString(victoriesC.getColumnIndex("cuisinesname"));
			 CuisinesModel c=new CuisinesModel();
			 c.setName(CuisieneNAME);
			 Log.i("Cursor values ########", String.valueOf(CuisieneNAME));
			List.add(c);
		}
		victoriesC.close();		
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
