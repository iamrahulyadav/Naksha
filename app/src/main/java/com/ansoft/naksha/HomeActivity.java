package com.ansoft.naksha;

import java.util.ArrayList;

import com.ansoft.naksha.adapter.HomeListAdapter;
import com.ansoft.naksha.data.HomeListdata;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */

	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Log.e("lat", ""+NakshaApplication.getLat());
		Log.e("lon", ""+NakshaApplication.getLon());
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		
		switch (position) {
		case 0:
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							PlaceholderFragment.newInstance(position + 1)).commit();
			break;
		case 1:
			Intent in=new Intent(HomeActivity.this, SettingActivity.class);
			startActivity(in);
			break;
		case 2:
			
			break;
		}
		
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
		Drawable myBg= getResources().getDrawable( R.drawable.bg_gradient_green );
		actionBar.setBackgroundDrawable(myBg);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.home, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		private String[] list_item_name = { "Hotel", "Food and drink",
				"Shopping", "Attraction", "Entertainment", "ATM", "Bank",
				"Bus Stations" };
		private int[] list_item_icon = { R.drawable.ic_hotel,
				R.drawable.ic_food, R.drawable.ic_shopping,
				R.drawable.ic_attraction, R.drawable.ic_entertainment,
				R.drawable.ic_atm, R.drawable.ic_bank,
				R.drawable.ic_bus_station };
		private ArrayList<HomeListdata> listitem;
		private HomeListAdapter listAdapter;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_home, container,
					false);

			ImageView pkrImg = (ImageView) rootView
					.findViewById(R.id.img_icon_place);
			ListView list = (ListView) rootView.findViewById(R.id.list);
			listitem = new ArrayList<HomeListdata>();
			for (int i = 0; i < list_item_name.length; i++) {
				HomeListdata data = new HomeListdata();
				data.setList_item_name(list_item_name[i]);
				data.setList_icon_drawable_name(list_item_icon[i]);
				listitem.add(data);
			}
			listAdapter = new HomeListAdapter(getActivity(), listitem);
			ImageView imageHeaderView = new ImageView(getActivity());
			imageHeaderView.setImageBitmap(BitmapFactory.decodeResource(
					getResources(), R.drawable.pokhara_photo));

			list.addHeaderView(imageHeaderView);
			list.setAdapter(listAdapter);

			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((HomeActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

}
