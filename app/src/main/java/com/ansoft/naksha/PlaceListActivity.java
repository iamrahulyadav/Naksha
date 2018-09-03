package com.ansoft.naksha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.ansoft.naksha.adapter.PlaceListAdapter;
import com.ansoft.naksha.data.PlaceListData;
import com.ansoft.naksha.data.PlaceObject;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class PlaceListActivity extends Activity {

	private ArrayList<PlaceObject> mapdata;
	private ArrayList<PlaceObject> thislist;
	private ArrayList<PlaceListData> listitem;
	private PlaceListAdapter listAdapter;
	ListView list;
	ImageView showOnMap;
	int type=0;
	String actionBarTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_list);
		list=(ListView)findViewById(R.id.listViewPlace);
		showOnMap=(ImageView)findViewById(R.id.showOnMap);
		
		mapdata=NakshaApplication.getMapdata();
		thislist=new ArrayList<PlaceObject>();
		type=getIntent().getIntExtra("type", 0);
		getActualList();
		CalculateDistance(thislist);
		sortingBy(thislist);
		listitem=new ArrayList<PlaceListData>();
		for (PlaceObject place:thislist) {
			PlaceListData data=new PlaceListData();
			data.setAddress(place.getAddress());
			
			data.setDistance(place.getDistance()/1000);
			data.setLat(place.getLat());
			data.setLon(place.getLon());
			data.setName(place.getName());
			data.setStar(place.getStar());
			data.setType(place.getPlctype());
			data.setPlace(place);
			listitem.add(data);
		}
		listAdapter=new PlaceListAdapter(listitem, PlaceListActivity.this);
		list.setAdapter(listAdapter);
		getActionBar().setTitle(actionBarTitle);
		showOnMap.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(PlaceListActivity.this, MapActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("choice",type);
				startActivity(intent);
			}
		});
		
	}

	private void CalculateDistance(ArrayList<PlaceObject> pla) {
		for (PlaceObject place:pla){
			
		double lat = place.getLat(), lon = place.getLon();
		Location loc1 = new Location("");
		loc1.setLatitude(NakshaApplication.getLat());
		loc1.setLongitude(NakshaApplication.getLon());

		Location loc2 = new Location("");
		loc2.setLatitude(lat);
		loc2.setLongitude(lon);

		float distanceInMeters = loc1.distanceTo(loc2);
		place.setDistance(distanceInMeters);
		}
	}
	
	  @SuppressWarnings("unchecked")
	private void sortingBy(ArrayList<PlaceObject> paramArrayList)
	  {
	    if ((paramArrayList != null) && (paramArrayList.size() > 0)) {
	      Collections.sort(paramArrayList, new Comparator<PlaceObject>()
	      {
	    	  
			@Override
			public int compare(PlaceObject ob1, PlaceObject ob2) {
				try {
					float f1 = ob1.getDistance();
		              float f2 = ob2.getDistance();
		              if (f1 < f2) {
		                return -1;
		              }
		              return 1;
				} catch (Exception e) {
					// TODO: handle exception
				}
				return 0;
			}


			

	      });
	    }
	    
	  }
	
	//store right data to thislist
	private void getActualList() {
		switch (type) {
		case 0:

			break;
		case 1:
			for (PlaceObject place : mapdata) {

				if (place.getType().equalsIgnoreCase("hotel")
						|| place.getType().equalsIgnoreCase("guest_house")) {
					thislist.add(place);
					place.setPlctype(1);
					actionBarTitle="Hotel";
				}
			}
			break;
		case 2:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("restaurant")
						|| place.getType().equalsIgnoreCase("cafe")) {

					thislist.add(place);
					place.setPlctype(2);
					actionBarTitle="Food & Drink";
				}
			}

			break;
		case 3:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("shop")) {

					thislist.add(place);
					place.setPlctype(3);
					actionBarTitle="Shopping";
				}
			}
			break;
		case 4:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("school")
						|| place.getType().equalsIgnoreCase("college")) {

					thislist.add(place);
					place.setPlctype(4);
					actionBarTitle="School";
				}
			}
			break;
		case 5:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("bar")) {

					thislist.add(place);
					place.setPlctype(5);
					actionBarTitle="Entertainment";
				}
			}
			break;
		case 6:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("atm")) {

					thislist.add(place);
					place.setPlctype(6);
					actionBarTitle="ATM";
				}
			}
			break;
		case 7:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("bank")) {

					thislist.add(place);
					place.setPlctype(7);
					actionBarTitle="Bank";
				}
			}
			break;
		case 8:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("bus_station")) {

					thislist.add(place);
					place.setPlctype(8);
					actionBarTitle="Bus Station";
				}
			}
			break;

		}
	}

	
}
