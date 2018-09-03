package com.ansoft.naksha;

import org.osmdroid.bonuspack.overlays.InfoWindow;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

 class MyInfoWindow extends InfoWindow {
	
	
	Activity activity;
	TextView titleTxt;
	TextView desTxt;
	String title, des;
	Marker mark;
	int res, org;
	LinearLayout layout;
	boolean layoutOpen;
	public MyInfoWindow(int layoutResId, MapView mapView, Activity ac,
			String titlet, String dest, Marker marker, int alternate, int original) {
		super(layoutResId, mapView);
		
		
		activity = ac;
		title = titlet;
		org=original;
		des = dest;
		mark = marker;
		res = alternate;
		
	}

	@Override
	public void onClose() {
	}
	
	@Override
	public void onOpen(Object arg0) {
		layout = (LinearLayout) mView.findViewById(R.id.bubble);
		
		titleTxt = (TextView) mView.findViewById(R.id.bubble_title);
		desTxt = (TextView) mView.findViewById(R.id.bubble_description);
		titleTxt.setText(title);
		desTxt.setText(des);
		mark.setIcon(activity.getResources().getDrawable(res));
		
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(activity, "Bubble clicked	", Toast.LENGTH_SHORT)
						.show();
			}
		});
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	  protected View mView;
      protected boolean mIsVisible;
      protected MapView mMapView;
      
      
      public MyInfoWindow(int layoutResId, MapView mapView) {
              mMapView = mapView;
              mIsVisible = false;
              ViewGroup parent=(ViewGroup)mapView.getParent();
              Context context = mapView.getContext();
              LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              mView = inflater.inflate(layoutResId, parent, false);
      }

      
      public View getView() {
              return(mView);
      }

      
      public void open(Object object, GeoPoint position, int offsetX, int offsetY) {
              onOpen(object);
              MapView.LayoutParams lp = new MapView.LayoutParams(
                              MapView.LayoutParams.WRAP_CONTENT,
                              MapView.LayoutParams.WRAP_CONTENT,
                              position, MapView.LayoutParams.BOTTOM_CENTER, 
                              offsetX, offsetY);
              close(); //if it was already opened
              mMapView.addView(mView, lp);
              mIsVisible = true;
      }
  
      public void close() {
              if (mIsVisible) {
                      mIsVisible = false;
                      ((ViewGroup)mView.getParent()).removeView(mView);
                      onClose();
              }
      }
      
      public boolean isOpen(){
              return mIsVisible;
      }
      
      //Abstract methods to implement in sub-classes:
      public abstract void onOpen(Object item);
      public abstract void onClose();
	
	
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}