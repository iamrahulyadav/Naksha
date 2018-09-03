package com.ansoft.naksha;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ansoft.naksha.DB.*;
import com.ansoft.naksha.util.GpsTracer;
import com.ansoft.naksha.util.ParseJsonUtils;
import com.ansoft.naksha.util.Unzip;
import com.ansoft.naksha.util.WriteSettingJsonfile;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

	TextView firstStartTxt;
	final static String TARGET_OSMDROID_PATH = "/sdcard/osmdroid/";
	final static String TARGET_MAPNIK_TILE_FILE = "/sdcard/osmdroid/tiles.zip";
	final static String TARGET_MAPNIK_TILE_FOLDER = "/sdcard/osmdroid/tiles/MapquestOSM/13/";
	final static String ARRAYLIST_FILE = "/sdcard/MyCustomObject/test";
	final static String SETTINGS_DATA_FILE="/sdcard/osmdroid/SettingsData/settings.json";
	double lat=0.0;
	double lon=0.0;
	String jsonTxt = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstStartTxt=(TextView)findViewById(R.id.txtstatus);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
        	  startup();
          }
        }, 300);
        
        
    }





	private void startup(){
		
		GpsTracer gps=new GpsTracer(MainActivity.this);
		lat=gps.getLatitude();
		lon=gps.getLongitude();
		if (lat!=0.0 && lon!=0.0){
			Log.e("lat", ""+lat);
			Log.e("lon", ""+lon);
			NakshaApplication.setLat(lat);
			NakshaApplication.setLon(lon);
			
			
			
		File f=new File(TARGET_MAPNIK_TILE_FILE);
		File f2=new File(SETTINGS_DATA_FILE);
        if (f.exists()&&f2.exists()) {
        	Log.d("Msg", "Parse starting");
        	DBTask db=new DBTask(new IDBTaskListener() {
				
				@Override
				public void onPreExcute() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onPostExcute() {
					// TODO Auto-generated method stub
					ParseJsonUtils pj=new ParseJsonUtils(MainActivity.this);
		        	pj.parse(firstStartTxt);
				}
				
				@Override
				public void onDoInBackground() {
					// TODO Auto-generated method stub
					jsonTxt = readFromFile();
					JSONObject obj;
					try {
						obj = new JSONObject(jsonTxt);
						JSONArray features = obj.getJSONArray("settings");
						
						for (int i = 0; i < features.length(); i++) {
							JSONObject oobj=features.getJSONObject(i);
							if (oobj.getString("name").equals("radius")){
								NakshaApplication.setRadius(oobj.getInt("value"));
							} else if (oobj.getString("name").equals("unit")){
								NakshaApplication.setSETTING_UNIT(oobj.getString("value"));
							}else if (oobj.getString("name").equals("priority")){
								NakshaApplication.setSETTING_PRIORITY(oobj.getString("value"));
							}
						}
					}catch (JSONException e) {
						Log.e("Setting", e.getMessage());
					}
					
				}
			});
        	db.execute(new Void[0]);
        	
        }else {
        	
        	File osmdroid =new File(TARGET_OSMDROID_PATH);
        	if(!osmdroid.isDirectory()) {
        		osmdroid.mkdir();
        	}
        	DBTask dbb=new DBTask(new IDBTaskListener() {
				
				@Override
				public void onPreExcute() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onPostExcute() {
					// TODO Auto-generated method stub
					DBTask dbt=new DBTask(new IDBTaskListener() {
						
						@Override
						public void onPreExcute() {
							// TODO Auto-generated method stub
							firstStartTxt.setVisibility(View.VISIBLE);
							firstStartTxt.setText("Copying files to sdcard...");
						}
						
						@Override
						public void onPostExcute() {
							// TODO Auto-generated method stub
							
				        		DBTask dbtask=new DBTask(new IDBTaskListener() {
									
									@Override
									public void onPreExcute() {
										// TODO Auto-generated method stub
										firstStartTxt.setText("Extracting Map Tiles...");
									}
									
									@Override
									public void onPostExcute() {
										// TODO Auto-generated method stub
										
										File fe=new File("/sdcard/osmdroid/tiles/MapQuest");
						            	if (fe.isDirectory()) {
						            		
						            		DBTask db=new DBTask(new IDBTaskListener() {
						        				
						        				@Override
						        				public void onPreExcute() {
						        					// TODO Auto-generated method stub
						        					firstStartTxt.setVisibility(View.VISIBLE);
						        					firstStartTxt.setText("Starting parsing data...");
						        				}
						        				
						        				@Override
						        				public void onPostExcute() {
						        					// TODO Auto-generated method stub
						        					
						        				}
						        				
						        				@Override
						        				public void onDoInBackground() {
						        					// TODO Auto-generated method stub
						        					
						        					ParseJsonUtils pj=new ParseJsonUtils(MainActivity.this);
						        		        	pj.parse(firstStartTxt);
						        				}
						        			});
						                	db.execute(new Void[0]);
						            	}
									}
									
									@Override
									public void onDoInBackground() {
										Unzip uz=new Unzip(TARGET_MAPNIK_TILE_FILE, TARGET_OSMDROID_PATH);
										uz.unzip();
									}
								});
				        		dbtask.execute(new Void[0]);
				            	
				        	}
						
						
						@Override
						public void onDoInBackground() {
							// TODO Auto-generated method stub
							
							copyFileOrDir("");
							
						}
					});
		        	dbt.execute(new Void[0]);
				}
				
				@Override
				public void onDoInBackground() {
					// TODO Auto-generated method stub
					WriteSettingJsonfile wr=new WriteSettingJsonfile(MainActivity.this);
					wr.Write(3, NakshaApplication.SETTING_UNIT_KILOMETERS, NakshaApplication.SETTING_PRIORITY_RATING);
				}
			});
        	dbb.execute(new Void[0]);
        	
        	
        }
				
		}
		
	}

    private void copyFileOrDir(final String path) {
        
        
				// TODO Auto-generated method stub
				AssetManager assetManager = MainActivity.this.getAssets();
		        String assets[] = null;
				try {
		            Log.i("tag", "copyFileOrDir() "+path);
		            assets = assetManager.list(path);
		            if (assets.length == 0) {
		                copyFile(path);
		            } else {
		                String fullPath =  TARGET_OSMDROID_PATH + path;
		                Log.i("tag", "path="+fullPath);
		                File dir = new File(fullPath);
		                if (!dir.exists() && !path.startsWith("images") && !path.startsWith("sounds") && !path.startsWith("webkit"))
		                    if (!dir.mkdirs())
		                        Log.i("tag", "could not create dir "+fullPath);
		                for (int i = 0; i < assets.length; ++i) {
		                    String p;
		                    if (path.equals(""))
		                        p = "";
		                    else 
		                        p = path + "/";

		                    if (!path.startsWith("images") && !path.startsWith("sounds") && !path.startsWith("webkit"))
		                        copyFileOrDir( p + assets[i]);
		                }
		            }
		        } catch (IOException ex) {
		            Log.e("tag", "I/O Exception", ex);
		        }
			
		
        
        
    }

    private void copyFile(String filename)  {
        AssetManager assetManager = this.getAssets();

        InputStream in = null;
        OutputStream out = null;
        String newFileName = null;
        try {
            Log.i("tag", "copyFile() "+filename);
            in = assetManager.open(filename);
            if (filename.endsWith(".jpg")) // extension was added to avoid compression on APK file
                newFileName = TARGET_OSMDROID_PATH + filename.substring(0, filename.length()-4);
            else
                newFileName = TARGET_OSMDROID_PATH + filename;
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", "Exception in copyFile() of "+newFileName);
            Log.e("tag", "Exception in copyFile() "+e.toString());
        }

    }
    private String readFromFile() {

		String ret = "";

		try {

			FileInputStream inputStream = new FileInputStream(new File(
					SETTINGS_DATA_FILE));

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append(receiveString);
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}

		return ret;
	}
    
}

