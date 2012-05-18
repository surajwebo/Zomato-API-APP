package com.zomato.models;

public class RestaurantsModel {
	String name;
	String address;
	String cuisines;
	String rating;
	public RestaurantsModel() {
		super();
	}
	public RestaurantsModel(String name, String address, String cuisines,String rating) 
	{
		super();
		this.name = name;
		this.address = address;
		this.cuisines = cuisines;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCuisines() {
		return cuisines;
	}
	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "RestaurantModel [name=" + name + ", address=" + address + ", cuisines=" + cuisines + ", rating=" + rating + "]";
	}
	
	
	
	

}
