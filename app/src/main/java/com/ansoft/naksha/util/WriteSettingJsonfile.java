package com.ansoft.naksha.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;

public class WriteSettingJsonfile {
Activity activity;
final static String SETTINGS_DATA_FILE="/sdcard/osmdroid/SettingsData/settings.json";
public WriteSettingJsonfile(Activity activity) {
	super();
	this.activity = activity;
}
public void Write(int radius, String unit, String priority){
	String string1="{\n\"settings\": [\n{\n\"name\": \"radius\",\n\"value\":";
	String string2="\n},\n{\n\"name\": \"unit\",\n\"value\": \"";
	String string3="\"\n},\n{\n\"name\": \"priority\",\n\"value\": \"";
	String string4="\"\n}\n]\n \n}";
	String dataString=string1+radius+string2+unit+string3+priority+string4;
	File dir=new File("/sdcard/osmdroid/SettingsData/");
	if (!dir.isDirectory()){
		dir.mkdir();
	}
	File myFile = new File(SETTINGS_DATA_FILE);
	if (!myFile.exists())
	{
	    try {
			myFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} else if (myFile.exists()){
		
		myFile.delete();
		try {
			myFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	FileOutputStream fos;
	byte[] data = dataString.getBytes();
	try {
	    fos = new FileOutputStream(myFile);
	    try {
			fos.write(data);
			fos.flush();
	        fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	
}}
