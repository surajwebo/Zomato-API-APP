package com.zomato.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zomato.models.CuisinesModel;
import com.zomato.models.RestaurantsModel;


public class RestaurantParser {
	ArrayList<RestaurantsModel> restaurantsArray;

	public ArrayList<RestaurantsModel> parseRestaurants(String strJsonReponse)
	{

		restaurantsArray=new ArrayList<RestaurantsModel>();
		try
		{
			JSONObject responseRestaurant = new JSONObject(strJsonReponse);
			JSONArray restaurantObj = responseRestaurant.getJSONArray("results");

			for(int i=0;i < restaurantObj.length();i++)
			{
				RestaurantsModel restaurantsModel =new RestaurantsModel();
				JSONObject jObj=restaurantObj.getJSONObject(i);
				JSONObject restaurant=jObj.getJSONObject("result");
				restaurantsModel.setName(restaurant.getString("name").toString());
				restaurantsModel.setAddress(restaurant.getString("address").toString());
				restaurantsModel.setCuisines(restaurant.getString("cuisines").toString());
				restaurantsModel.setRating(restaurant.getString("rating_editor_overall").toString());

				restaurantsArray.add(restaurantsModel);

			}

		}
		catch (Exception e) {
			// TODO: handle exception
		}

		return restaurantsArray;
	}

}
