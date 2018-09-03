package com.ansoft.naksha.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Unzip {
	private String _zipFile; 
	  private String _location; 
	 
	  public Unzip(String zipFile, String location) { 
	    _zipFile = zipFile; 
	    _location = location; 
	  } 
	 
	  public void unzip() { 
		    try {
		         ZipFile zipFile = new ZipFile(_zipFile);
		         zipFile.extractAll(_location);
		        
		    } catch (ZipException e) {
		        e.printStackTrace();
		    } 
		    }
}
