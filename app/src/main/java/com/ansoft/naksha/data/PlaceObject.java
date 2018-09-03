package com.ansoft.naksha.data;

public class PlaceObject{

	
	int id;
	int star;
	float distance;
	String address;
	int plctype = 0;
	
	String type = "", email = "", name = "", website = "", phone = "",
			adstreet = "", adcity = "", adpc = "", adunit = "",
			addrhousenumber = "", housename = "", openinghours = "";
	
	

	public int getPlctype() {
		return plctype;
	}

	public void setPlctype(int plctype) {
		this.plctype = plctype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PlaceObject() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdstreet() {
		return adstreet;
	}

	public void setAdstreet(String adstreet) {
		this.adstreet = adstreet;
	}

	public String getAdcity() {
		return adcity;
	}

	public void setAdcity(String adcity) {
		this.adcity = adcity;
	}

	public String getAdpc() {
		return adpc;
	}

	public void setAdpc(String adpc) {
		this.adpc = adpc;
	}

	public String getAdunit() {
		return adunit;
	}

	public void setAdunit(String adunit) {
		this.adunit = adunit;
	}

	public String getAddrhousenumber() {
		return addrhousenumber;
	}

	public void setAddrhousenumber(String addrhousenumber) {
		this.addrhousenumber = addrhousenumber;
	}

	public String getHousename() {
		return housename;
	}

	public void setHousename(String housename) {
		this.housename = housename;
	}

	public String getOpeninghours() {
		return openinghours;
	}

	public void setOpeninghours(String openinghours) {
		this.openinghours = openinghours;
	}

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

	double lat, lon;

}
