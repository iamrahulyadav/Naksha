package com.ansoft.naksha.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlaceListData {

String name;
String address;
float distance;
PlaceObject place;
public PlaceObject getPlace() {
	return place;
}
public void setPlace(PlaceObject place) {
	this.place = place;
}
int type;
double lat, lon;
public double getLat() {
	return lat;
}
public void setLat(double lat) {
	this.lat = lat;
}
public double getLon() {
	return lon;
}
public void setLon(double lon) {
	this.lon = lon;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public float getDistance() {
	return distance;
}
public void setDistance(float f) {
	
	
	BigDecimal bd = new BigDecimal(f);
	BigDecimal res = bd.setScale(2, RoundingMode.HALF_UP);
	f = res.floatValue();
	this.distance = f;
}
int star;
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
public int getStar() {
	return star;
}
public void setStar(int star) {
	this.star = star;
}

}
