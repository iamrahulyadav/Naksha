package com.ansoft.naksha.adapter;

import java.util.List;
import java.util.zip.Inflater;

import com.ansoft.naksha.PlaceListActivity;
import com.ansoft.naksha.data.HomeListdata;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeListAdapter extends BaseAdapter{
	private Activity activity;
	private LayoutInflater inflater;
	private List<HomeListdata> listitem;
	public HomeListAdapter(Activity activity, List<HomeListdata> mlistdata) {
		super();
		this.activity = activity;
		this.listitem = mlistdata;
	}

	@Override
	public int getCount() {
		if (listitem!=null) {
			return listitem.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listitem.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint({ "InflateParams", "ViewHolder" }) @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (inflater==null)
			inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final int pos=position;
		convertView=inflater.inflate(R.layout.list_item_info, null);
		final HomeListdata item=listitem.get(position);
		RelativeLayout bg=(RelativeLayout)convertView.findViewById(R.id.list_tem_bg);
		ImageView cat_img=(ImageView)convertView.findViewById(R.id.img_icon);
		TextView cat_title=(TextView)convertView.findViewById(R.id.list_item_txt);
		cat_img.setImageResource(item.getList_icon_drawable_name());
		cat_title.setText(item.getList_item_name());
		bg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(activity, PlaceListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("type", pos+1);
				activity.startActivity(intent);
			}
		});
		return convertView;
	}

	

}
