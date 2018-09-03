package com.ansoft.naksha.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ansoft.naksha.HomeActivity;
import com.ansoft.naksha.NakshaApplication;
import com.ansoft.naksha.DB.*;
import com.ansoft.naksha.data.PlaceObject;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class ParseJsonUtils {

	Activity activity;
	String jsonTxt = "";
	ArrayList<PlaceObject> placeObjects;
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	public void parse(TextView txt) {
		placeObjects=new ArrayList<PlaceObject>();
		jsonTxt = readFromFile();
		
		
		DBTask db=new DBTask(new IDBTaskListener() {
			
			@Override
			public void onPreExcute() {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void onPostExcute() {
				
				NakshaApplication.setMapdata(placeObjects);
				Intent intent=new Intent(activity, HomeActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				activity.startActivity(intent);
				activity.finish();
			}
			
			@Override
			public void onDoInBackground() {
				// TODO Auto-generated method stub
				
				int total = 0;
				JSONObject obj;
				try {
					obj = new JSONObject(jsonTxt);
					JSONArray features = obj.getJSONArray("features");
					for (int i = 0; i < features.length(); i++) {
						JSONObject oobj = features.getJSONObject(i);
						
						boolean DataIsValid=true;
						double lat = 0 , lon = 0;
						if (oobj.has("geometry")) {
							JSONObject geometry = oobj.getJSONObject("geometry");
							for (int l = 0; l < geometry.length(); l++) {
								
								JSONArray cN = geometry.getJSONArray("coordinates");
								
								String latitude = cN.getString(0);
								String longitude = cN.getString(1);
								
								try {
									lat = Double.parseDouble(latitude);
									lon = Double.parseDouble(longitude);
								} catch (NumberFormatException e) {
									DataIsValid=false;
									
								} 
								
								

							}
						}
						
						
						JSONObject objProperties = oobj.getJSONObject("properties");
						String type = "", email = "", name = "", website = "", phone = "", adstreet = "", adcity = "", adpc = "", adunit = "", addrhousenumber = "", housename = "", openinghours = "";
						boolean ishotel = false;
						boolean isshop = false;
						boolean isother = false;
						
						
						if (objProperties.has("name")) {
							if (DataIsValid){
								PlaceObject po=new PlaceObject();
								
								if (objProperties.has("shop")) {
									
									type = "shop";
									
								} else if (objProperties.has("tourism")) {
									
									type = objProperties.getString("tourism");
									
								}else if (objProperties.has("highway")) {
									
									if (objProperties.getString("highway").equalsIgnoreCase("bus_stop")){
										type="bus_station";
									}
									
								}  
								
								else {
									
									if (objProperties.has("amenity")) {
										type = objProperties.getString("amenity");
										
									}
								}
								if (type!=null) {
								po.setType(type);
								po.setLon(lat);
								po.setLat(lon);
								po.setId(i+1);
								po.setStar(randInt(0, 5));
							name = objProperties.getString("name");
							po.setName(name);
						
						if (objProperties.has("contact:email")) {
							email = objProperties.getString("contact:email");
							po.setEmail(email);
							
						}
						if (objProperties.has("email")) {
							email = objProperties.getString("email");
							po.setEmail(email);
						}
						if (objProperties.has("phone")) {
							phone = objProperties.getString("phone");
							po.setPhone(phone);
						}
						if (objProperties.has("contact:phone")) {
							phone = objProperties.getString("contact:phone");
							po.setPhone(phone);

						}
						
						if (objProperties.has("website")) {
							website = objProperties.getString("website");
							po.setWebsite(website);
						}
						if (objProperties.has("contact:website")) {
							website = objProperties.getString("contact:website");
							po.setWebsite(website);
						}
						if (objProperties.has("addr:street")) {
							adstreet = objProperties.getString("addr:street");
							po.setAdstreet(adstreet);
						}
						if (objProperties.has("addr:city")) {
							adcity = objProperties.getString("addr:city");
							po.setAdcity(adcity);
						}
						if (objProperties.has("addr:postcode")) {
							adpc = objProperties.getString("addr:postcode");
							po.setAdpc(adpc);
						}
						if (objProperties.has("addr:unit")) {
							adunit = objProperties.getString("addr:unit");
							po.setAdunit(adunit);
						}
						if (objProperties.has("addr:housenumber")) {
							addrhousenumber = objProperties
									.getString("addr:housenumber");
							po.setAddrhousenumber(addrhousenumber);
						}
						if (objProperties.has("addr:housename")) {
							housename = objProperties.getString("addr:housename");
							po.setHousename(housename);
						}
						if (objProperties.has("opening_hours")) {
							openinghours = objProperties.getString("opening_hours");
							po.setOpeninghours(openinghours);
						}
						po.setAddress(housename+" "+addrhousenumber+" "+adstreet+" "+adunit+", "+adcity);
						placeObjects.add(po);
						total++;
						
						}}}
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		db.execute(new Void[0]);
		
		
		
		
		
		
		
	}

	private String readFromFile() {

		String ret = "";

		try {

			FileInputStream inputStream = new FileInputStream(new File(
					"/sdcard/osmdroid/map.geojson"));

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append(receiveString);
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}

		return ret;
	}

	public ParseJsonUtils(Activity contexti) {
		super();
		this.activity = contexti;
	}

}
