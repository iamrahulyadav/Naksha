package com.ansoft.naksha;

import java.util.ArrayList;

import org.osmdroid.bonuspack.overlays.Marker;

import com.ansoft.naksha.data.PlaceObject;

import android.app.Application;

public class NakshaApplication extends Application {

	static ArrayList<PlaceObject> mapdata;
	static String SETTING_UNIT_KILOMETERS="Kilometers";
	static String SETTING_UNIT_METERS="Meters";
	static String SETTING_PRIORITY_RATING="Rating";
	static String SETTING_PRIORITY_DISTANCE="Distance";
	static String SETTING_PRIORITY_ALPHABET="Alphabet";
	static double lat;
	static double lon;
	static int radius;
	static String SETTING_UNIT;
	static String SETTING_PRIORITY;
	public static int getRadius() {
		return radius;
	}

	public static void setRadius(int radius) {
		NakshaApplication.radius = radius;
	}

	public static String getSETTING_UNIT() {
		return SETTING_UNIT;
	}

	public static void setSETTING_UNIT(String sETTING_UNIT) {
		SETTING_UNIT = sETTING_UNIT;
	}

	public static String getSETTING_PRIORITY() {
		return SETTING_PRIORITY;
	}

	public static void setSETTING_PRIORITY(String sETTING_PRIORITY) {
		SETTING_PRIORITY = sETTING_PRIORITY;
	}

	static Marker currentMarker;
	static Marker previousMarker;
	
	public static ArrayList<PlaceObject> getMapdata() {
		return mapdata;
	}

	public static void setMapdata(ArrayList<PlaceObject> nmapdata) {
		mapdata = nmapdata;
	}
	public NakshaApplication() {
		super();
	}
	
	
	public static Marker getPreviousMarker() {
		return previousMarker;
	}

	public static void setPreviousMarker(Marker previousMarker) {
		NakshaApplication.previousMarker = previousMarker;
	}

	public static Marker getCurrentMarker() {
		return currentMarker;
	}

	public static void setCurrentMarker(Marker mcurrentMarker) {
		currentMarker = mcurrentMarker;
	}

	static PlaceObject placeInUse;
	public static PlaceObject getPlaceInUse() {
		return placeInUse;
	}

	public static void setPlaceInUse(PlaceObject placeInUse) {
		NakshaApplication.placeInUse = placeInUse;
	}

	public static double getLat() {
		return lat;
	}

	public static void setLat(double lati) {
		lat = lati;
	}

	public static double getLon() {
		return lon;
	}

	public static void setLon(double loni) {
		lon = loni;
	}

	

}
