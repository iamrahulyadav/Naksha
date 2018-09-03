package com.ansoft.naksha.adapter;

import java.util.ArrayList;

import com.ansoft.naksha.MapActivity;
import com.ansoft.naksha.NakshaApplication;
import com.ansoft.naksha.data.PlaceListData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlaceListAdapter extends BaseAdapter{

	ArrayList<PlaceListData> items;
	LayoutInflater inflater;
	Activity activity;
	public PlaceListAdapter(ArrayList<PlaceListData> items, Activity activity) {
		super();
		this.items = items;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (items!=null) {
			return items.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (inflater==null)
			inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final int pos=position;
		convertView=inflater.inflate(R.layout.item_place, null);
		final PlaceListData item=items.get(position);
		RelativeLayout bg=(RelativeLayout)convertView.findViewById(R.id.RLlayoutListItem);
		ImageView icon=(ImageView)convertView.findViewById(R.id.img_icon_place);
		ImageView ratingBar=(ImageView)convertView.findViewById(R.id.ratingBar);
		TextView name=(TextView)convertView.findViewById(R.id.tv_name);
		TextView address=(TextView)convertView.findViewById(R.id.tv_address);
		TextView distance=(TextView)convertView.findViewById(R.id.tv_distance);
		switch(item.getType()){
		case 1:
			icon.setImageResource(R.drawable.icon_type_ahead_hotel);
			break;
		
		case 2:
			icon.setImageResource(R.drawable.icon_type_ahead_restaurant);
			break;
		
		case 3:
			icon.setImageResource(R.drawable.icon_type_ahead_shopping);
			break;
		
		case 4:
			icon.setImageResource(R.drawable.icon_type_ahead_attraction);
			break;
		
		case 5:
			icon.setImageResource(R.drawable.icon_type_ahead_nightlife);
			break;
		
		case 6:
			icon.setImageResource(R.drawable.icon_type_ahead_atm);
			break;
		
		case 7:
			icon.setImageResource(R.drawable.icon_type_ahead_bank);
			break;
		
		case 8:
			icon.setImageResource(R.drawable.icon_type_ahead_bus_station);
			break;
		}
		switch(item.getStar()) {
		case 0:
			ratingBar.setImageResource(R.drawable.ta_rating_0);
			break;
			
		case 1:
			ratingBar.setImageResource(R.drawable.ta_rating_1);
			break;
		
		case 2:
			ratingBar.setImageResource(R.drawable.ta_rating_2);
			break;
		
		case 3:
			ratingBar.setImageResource(R.drawable.ta_rating_3);
			break;
		
		case 4:
			ratingBar.setImageResource(R.drawable.ta_rating_4);
			break;
		
		case 5:
			ratingBar.setImageResource(R.drawable.ta_rating_5);
			break;
		
		}
		name.setText(item.getName());
		address.setText(item.getAddress());
		 String mytext=Float.toString(item.getDistance());
		distance.setText(mytext+" km");
		bg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
				LayoutInflater inflater = activity.getLayoutInflater();
				View dialogView = inflater.inflate(R.layout.activity_place_detail, null);
				dialogBuilder.setView(dialogView);
				ImageView placeIcon=(ImageView)dialogView.findViewById(R.id.im)
				AlertDialog alertDialog = dialogBuilder.create();
				alertDialog.show();
			}
		});
		return convertView;
	}



}
