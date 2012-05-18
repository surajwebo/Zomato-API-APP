package com.zomato.models;

import android.database.Cursor;
import com.zomato.database.CuisineDbAdapter;

public class CuisinesModel 
{
	String id;
	String name;
	public CuisinesModel() 
	{
		super();
	}
	
	public CuisinesModel(Cursor cursor)
	{
		super();
		this.id =cursor.getString(cursor.getColumnIndex(CuisineDbAdapter.CuisineId));
		this.name =cursor.getString(cursor.getColumnIndex(CuisineDbAdapter.CuisineName));
	}
	
	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	@Override
	public String toString() 
	{
		return "CuisinesClass [id=" + id + ", name=" + name + "]";
	}
	

}
