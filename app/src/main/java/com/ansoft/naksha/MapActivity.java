package com.ansoft.naksha;

import java.util.ArrayList;

import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.InfoWindow;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.MarkerInfoWindow;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.bonuspack.routing.MapQuestRoadManager;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.bonuspack.routing.RoadNode;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import com.ansoft.naksha.DB.*;
import com.ansoft.naksha.data.PlaceObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class MapActivity extends Activity {

	public static final GeoPoint MyLocation = new GeoPoint(
			NakshaApplication.getLat(), NakshaApplication.getLon());
	ArrayList<PlaceObject> mapdata = null;
	ArrayList<OverlayItem> overlaylistitem;
	ArrayList<OverlayItem> anotherOverlayItemArray;
	int choice = 0;
	double lat, lon;
	private DBTask mDBTask;
	private Handler mHandler = new Handler();
	PlaceObject placeuse;
	String actionBarTitle;
	boolean isOneWindowAvailable = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		placeuse = NakshaApplication.getPlaceInUse();
		choice = getIntent().getIntExtra("choice", 0);
		mapdata = NakshaApplication.getMapdata();
		Log.e("Lat", "" + mapdata.get(100).getLat());
		Log.e("Lon", "" + mapdata.get(100).getLon());
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.refreshDrawableState();
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);
		mapView.setMultiTouchControls(true);
		mapView.setUseDataConnection(true);
		mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);
		mapView.setMaxZoomLevel(18);
		mapView.setMinZoomLevel(13);
		mapView.setScrollableAreaLimit(new BoundingBoxE6(28.309217, 84.119911,
				28.155557, 83.877525));
		IMapController mapViewController = mapView.getController();
		mapViewController.setZoom(15);

		if (choice != 9) {
			mapViewController.setCenter(MyLocation);

		}
		switch (choice) {
		case 9:
			mapViewController.setCenter(new GeoPoint(placeuse.getLat(),
					placeuse.getLon()));
			AddMarker(mapView, placeuse);

			startRouting(mapView);

			break;
		case 1:
			for (PlaceObject place : mapdata) {

				if (place.getType().equalsIgnoreCase("hotel")
						|| place.getType().equalsIgnoreCase("guest_house")) {
					AddMarker(mapView, place);
				}
			}
			actionBarTitle = "Hotel";
			break;
		case 2:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("restaurant")
						|| place.getType().equalsIgnoreCase("cafe")) {

					AddMarker(mapView, place);
				}
			}
			actionBarTitle = "Food & Drink";
			break;
		case 3:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("shop")) {

					AddMarker(mapView, place);
				}
			}
			actionBarTitle = "Shopping";
			break;
		case 4:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("school")
						|| place.getType().equalsIgnoreCase("college")) {

					AddMarker(mapView, place);
				}
			}
			actionBarTitle = "Attraction";
			break;
		case 5:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("bar")) {

					AddMarker(mapView, place);
				}
			}
			actionBarTitle = "Entertainment";
			break;
		case 6:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("atm")) {

					AddMarker(mapView, place);
				}
			}
			actionBarTitle = "ATM";
			break;
		case 7:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("bank")) {

					AddMarker(mapView, place);
				}
			}
			actionBarTitle = "Bank";
			break;
		case 8:
			for (PlaceObject place : mapdata) {
				if (place.getType().equalsIgnoreCase("bus_station")) {

					AddMarker(mapView, place);
				}
			}
			actionBarTitle = "Bus Station";
			break;

		}
		getActionBar().setTitle(actionBarTitle);

	}

	private void startRouting(final MapView mView) {
		this.mDBTask=new DBTask(new IDBTaskListener() {
			RoadManager roadManager;
			Road road;
			Polyline roadOverlay;
			MapView mapView=mView;
			ProgressDialog pd;
			@Override
			public void onPreExcute() {
				// TODO Auto-generated method stub
				pd=ProgressDialog.show(MapActivity.this, "Routing", "Please wait...");
			}
			
			@Override
			public void onPostExcute() {
				// TODO Auto-generated method stub
				Drawable nodeIcon = getResources().getDrawable(R.drawable.map_marker_restaurant);
				for (int i=0; i<road.mNodes.size(); i++){
				    RoadNode node = road.mNodes.get(i);
				    Marker nodeMarker = new Marker(mapView);
				    nodeMarker.setPosition(node.mLocation);
				    nodeMarker.setIcon(nodeIcon);
				    nodeMarker.setSnippet(node.mInstructions);
				    nodeMarker.setSubDescription(Road.getLengthDurationText(node.mLength, node.mDuration));
				    nodeMarker.setTitle("Step "+i);
				    Drawable icon = getResources().getDrawable(R.drawable.ic_continue);
				    nodeMarker.setImage(icon);
				    mapView.getOverlays().add(nodeMarker);
				}
				mapView.getOverlays().add(roadOverlay);
				
				mapView.invalidate();
				pd.dismiss();
			}
			
			@Override
			public void onDoInBackground() {
				// TODO Auto-generated method stub
				roadManager = new MapQuestRoadManager(
						"GTXmfs4xJYBAY0QZUx8728hG80cF0Vqp");
				roadManager.addRequestOption("routeType=fastest");
				ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
				waypoints.add(MyLocation);
				GeoPoint endPoint = new GeoPoint(placeuse.getLat(),
						placeuse.getLon());
				waypoints.add(endPoint);
				road= roadManager.getRoad(waypoints);

				roadOverlay = RoadManager.buildRoadOverlay(road,
						MapActivity.this);
				
			}

		});
		this.mDBTask.execute(new Void[0]);
		
		
	}

	@SuppressWarnings("deprecation")
	private void AddMarker(MapView mapView, PlaceObject place) {
		final Marker marker = new Marker(mapView);
		int alternate = 0;
		int original = 0;
		marker.setPosition(new GeoPoint(place.getLat(), place.getLon()));
		marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
		if (place.getType().equalsIgnoreCase("hotel")
				|| place.getType().equalsIgnoreCase("guest_house")) {
			if (place.getStar() > 3) {
				original = R.drawable.map_marker_hotel;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_hotel));
			} else {
				original = R.drawable.map_marker_hotel_smalldot;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_hotel_smalldot));
			}
			alternate = R.drawable.map_marker_hotel_selected;
		} else if (place.getType().equalsIgnoreCase("restaurant")
				|| place.getType().equalsIgnoreCase("cafe")) {
			if (place.getStar() > 3) {
				original = R.drawable.map_marker_restaurant;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_restaurant));
			} else {
				original = R.drawable.map_marker_restaurant_smalldot;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_restaurant_smalldot));
			}
			alternate = R.drawable.map_marker_restaurant_selected;
		} else if (place.getType().equalsIgnoreCase("shop")) {
			if (place.getStar() > 3) {
				original = R.drawable.map_marker_shopping;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_shopping));

			} else {
				original = R.drawable.map_marker_shopping_smalldot;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_shopping_smalldot));
			}
			alternate = R.drawable.map_marker_shopping_selected;
		} else if (place.getType().equalsIgnoreCase("atm")) {
			if (place.getStar() > 3) {
				original = R.drawable.map_marker_atm;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_atm));
			} else {
				original = R.drawable.map_marker_atm_smalldot;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_atm_smalldot));
			}
			alternate = R.drawable.map_marker_atm_selected;
		} else if (place.getType().equalsIgnoreCase("school")
				|| place.getType().equalsIgnoreCase("college")) {
			if (place.getStar() > 3) {
				original = R.drawable.map_marker_attraction;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_attraction));
			} else {
				original = R.drawable.map_marker_attraction_smalldot;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_attraction_smalldot));
			}
			alternate = R.drawable.map_marker_attraction_selected;
		} else if (place.getType().equalsIgnoreCase("bank")) {
			if (place.getStar() > 3) {
				original = R.drawable.map_marker_bank;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_bank));
			} else {
				original = R.drawable.map_marker_bank_smalldot;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_bank_smalldot));
			}
			alternate = R.drawable.map_marker_bank_selected;
		} else if (place.getType().equalsIgnoreCase("bus_station")) {
			if (place.getStar() > 3) {
				original = R.drawable.map_marker_bus_station;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_bus_station));
			} else {
				original = R.drawable.map_marker_bus_station_smalldot;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_bus_station_smalldot));
			}
			alternate = R.drawable.map_marker_bus_station_selected;
		} else if (place.getType().equalsIgnoreCase("bar")) {
			if (place.getStar() > 3) {
				original = R.drawable.map_marker_nightlife;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_nightlife));
			} else {
				original = R.drawable.map_marker_nightlife_smalldot;
				marker.setIcon(getResources().getDrawable(
						R.drawable.map_marker_nightlife_smalldot));
			}
			alternate = R.drawable.map_marker_nightlife_selected;
		} else {
			marker.setIcon(getResources().getDrawable(
					R.drawable.ic_marker_default));
		}
		InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble,
				mapView, MapActivity.this, place.getName(), place.getAddress(),
				marker, alternate, original);

		marker.setInfoWindow(infoWindow);
		marker.setTitle(place.getName());
		mapView.getOverlays().add(marker);
	}

}
