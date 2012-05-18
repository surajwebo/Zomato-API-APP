package com.zomato.adapters;

import java.util.ArrayList;

import com.zomato.ZomatoApplication.R;
import com.zomato.models.CuisinesModel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CuisinesAdapter extends ArrayAdapter {
	Activity context;
	ArrayList<CuisinesModel> cuisineArray;
	
	public CuisinesAdapter(Activity context,ArrayList<CuisinesModel> cuisineArray) 
	{
		super(context,R.layout.list_item,R.id.text,cuisineArray);
		this.context = context;
		this.cuisineArray = cuisineArray;	
	}
	
	@Override
	public Object getItem(int position) 
	{
		return cuisineArray.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		CuisinesModel c=cuisineArray.get(position);
		if(convertView==null){
			LayoutInflater inflater=context.getLayoutInflater();
			convertView=inflater.inflate(R.layout.customcuisines,null);
			
			holder = new ViewHolder();			
			holder.name = (TextView) convertView.findViewById(R.id.textName);
			//holder.image = (ImageView) convertView.findViewById(R.id.imgIcon);
	
			convertView.setTag(holder);
		}
		else 
		{
			holder=(ViewHolder)convertView.getTag();
		}
	
		holder.name.setText(c.getName());
		return convertView;
	}
	
	static class ViewHolder {
		TextView name;		
	}
	


}
