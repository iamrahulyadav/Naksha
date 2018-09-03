package com.ansoft.naksha;

import com.ansoft.naksha.DB.DBTask;
import com.ansoft.naksha.DB.IDBTaskListener;
import com.ansoft.naksha.util.WriteSettingJsonfile;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SettingActivity extends ActionBarActivity {

	SeekBar seekbar;
	TextView minLimit, maxLimit;
	TextView unitMetric, unitPriority;
	RelativeLayout metric, priority, account;
	public static String METRIC1 = "Kilometer";
	public static String METRIC2 = "Meter";
	public static String PRIORITY1 = "Rating";
	public static String PRIORITY2 = "Distance";
	public static String PRIORITY3 = "Alphabet";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		connectView();
		minLimit.setText(""+NakshaApplication.getRadius());
		unitMetric.setText(NakshaApplication.getSETTING_UNIT());
		unitPriority.setText(NakshaApplication.getSETTING_PRIORITY());
		
	
		
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			
			@Override
			public void onStopTrackingTouch(final SeekBar seekBar) {
				// TODO Auto-generated method stub
				/*
				 * minLimit.setText(seekbar.getProgress());
				 * SharedPreferences.Editor editor=settingsdata.edit();
				 * editor.putInt(NakshaApplication.SETTING_PREF_RADIUS,
				 * seekbar.getProgress()); editor.commit();
				 */
				
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				final int value=progress;
				DBTask dbt = new DBTask(new IDBTaskListener() {

					@Override
					public void onPreExcute() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPostExcute() {
						// TODO Auto-generated method stub
						NakshaApplication.setRadius(value);
						seekbar.setProgress(NakshaApplication.getRadius());
					}

					@Override
					public void onDoInBackground() {
						// TODO Auto-generated method stub
						WriteSettingJsonfile wr = new WriteSettingJsonfile(
								SettingActivity.this);
						wr.Write(value,
								NakshaApplication.getSETTING_UNIT(),
								NakshaApplication.getSETTING_PRIORITY());

					}
				});
				dbt.execute(new Void[0]);
			}
			
		});
		metric.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						SettingActivity.this);
				final AlertDialog alert = builder.create();
				LinearLayout lin = new LinearLayout(SettingActivity.this);
				lin.setOrientation(LinearLayout.VERTICAL);
				lin.setBackgroundColor(getResources().getColor(R.color.grey));
				Button btn = new Button(SettingActivity.this);
				btn.setWidth(LayoutParams.MATCH_PARENT);
				btn.setText(METRIC1);
				btn.setPadding(0, 1, 0, 1);
				btn.setBackgroundResource(R.drawable.bg_gradient_list_selector2);
				btn.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						DBTask dbt = new DBTask(new IDBTaskListener() {

							@Override
							public void onPreExcute() {
								// TODO Auto-generated method stub

							}

							@Override
							public void onPostExcute() {
								// TODO Auto-generated method stub
								NakshaApplication.setSETTING_UNIT(METRIC1);
								unitMetric.setText(METRIC1);
								alert.dismiss();
							}

							@Override
							public void onDoInBackground() {
								// TODO Auto-generated method stub
								WriteSettingJsonfile wr = new WriteSettingJsonfile(
										SettingActivity.this);
								wr.Write(NakshaApplication.getRadius(),
										METRIC1,
										NakshaApplication.getSETTING_PRIORITY());

							}
						});
						dbt.execute(new Void[0]);
					}
				});
				Button btn1 = new Button(SettingActivity.this);
				btn1.setWidth(LayoutParams.MATCH_PARENT);
				btn1.setText(METRIC2);
				btn1.setPadding(0, 1, 0, 1);
				btn1.setBackgroundResource(R.drawable.bg_gradient_list_selector2);
				btn1.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						DBTask dbt = new DBTask(new IDBTaskListener() {

							@Override
							public void onPreExcute() {
								// TODO Auto-generated method stub

							}

							@Override
							public void onPostExcute() {
								// TODO Auto-generated method stub
								NakshaApplication.setSETTING_UNIT(METRIC2);
								unitMetric.setText(METRIC2);
								alert.dismiss();
							}

							@Override
							public void onDoInBackground() {
								// TODO Auto-generated method stub
								WriteSettingJsonfile wr = new WriteSettingJsonfile(
										SettingActivity.this);
								wr.Write(NakshaApplication.getRadius(),
										METRIC2,
										NakshaApplication.getSETTING_PRIORITY());

							}
						});
						dbt.execute(new Void[0]);
					}
				});
				lin.addView(btn);
				lin.addView(btn1);
				alert.setView(lin);
				alert.show();

			}
		});

		priority.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						SettingActivity.this);
				final AlertDialog alert = builder.create();
				LinearLayout lin = new LinearLayout(SettingActivity.this);
				lin.setOrientation(LinearLayout.VERTICAL);
				lin.setBackgroundColor(getResources().getColor(R.color.grey));
				Button btn = new Button(SettingActivity.this);
				btn.setWidth(LayoutParams.MATCH_PARENT);
				btn.setText(PRIORITY1);
				btn.setPadding(0, 1, 0, 1);
				btn.setBackgroundResource(R.drawable.bg_gradient_list_selector2);
				btn.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						DBTask dbt = new DBTask(new IDBTaskListener() {

							@Override
							public void onPreExcute() {
								// TODO Auto-generated method stub

							}

							@Override
							public void onPostExcute() {
								// TODO Auto-generated method stub
								NakshaApplication.setSETTING_PRIORITY(PRIORITY1);
								unitPriority.setText(PRIORITY1);
								alert.dismiss();
							}

							@Override
							public void onDoInBackground() {
								// TODO Auto-generated method stub
								WriteSettingJsonfile wr = new WriteSettingJsonfile(
										SettingActivity.this);
								wr.Write(NakshaApplication.getRadius(),
										NakshaApplication.getSETTING_UNIT(),
										PRIORITY1);

							}
						});
						dbt.execute(new Void[0]);
					}
				});
				Button btn1 = new Button(SettingActivity.this);
				btn1.setWidth(LayoutParams.MATCH_PARENT);
				btn1.setText(PRIORITY2);
				btn1.setPadding(0, 1, 0, 1);
				btn1.setBackgroundResource(R.drawable.bg_gradient_list_selector2);
				btn1.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						DBTask dbt = new DBTask(new IDBTaskListener() {

							@Override
							public void onPreExcute() {
								// TODO Auto-generated method stub

							}

							@Override
							public void onPostExcute() {
								// TODO Auto-generated method stub
								NakshaApplication.setSETTING_PRIORITY(PRIORITY2);
								unitPriority.setText(PRIORITY2);
								alert.dismiss();
							}

							@Override
							public void onDoInBackground() {
								// TODO Auto-generated method stub
								WriteSettingJsonfile wr = new WriteSettingJsonfile(
										SettingActivity.this);
								wr.Write(NakshaApplication.getRadius(),
										NakshaApplication.getSETTING_UNIT(),
										PRIORITY2);

							}
						});
						dbt.execute(new Void[0]);
					}
				});
				Button btn2 = new Button(SettingActivity.this);
				btn2.setWidth(LayoutParams.MATCH_PARENT);
				btn2.setText(PRIORITY3);
				btn2.setPadding(0, 1, 0, 1);
				btn2.setBackgroundResource(R.drawable.bg_gradient_list_selector2);
				btn2.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						DBTask dbt = new DBTask(new IDBTaskListener() {

							@Override
							public void onPreExcute() {
								// TODO Auto-generated method stub

							}

							@Override
							public void onPostExcute() {
								NakshaApplication.setSETTING_PRIORITY(PRIORITY3);
								unitPriority.setText(PRIORITY3);
								alert.dismiss();
							}

							@Override
							public void onDoInBackground() {
								// TODO Auto-generated method stub
								WriteSettingJsonfile wr = new WriteSettingJsonfile(
										SettingActivity.this);
								wr.Write(NakshaApplication.getRadius(),
										NakshaApplication.getSETTING_UNIT(),
										PRIORITY3);

							}
						});
						dbt.execute(new Void[0]);
					}
				});
				lin.addView(btn);
				lin.addView(btn1);
				lin.addView(btn2);
				alert.setView(lin);
				alert.show();
			}
		});

	}

	private void connectView() {
		seekbar = (SeekBar) findViewById(R.id.seekBar);
		minLimit = (TextView) findViewById(R.id.tv_min_radius);
		maxLimit = (TextView) findViewById(R.id.tv_max_radius);
		unitMetric = (TextView) findViewById(R.id.tv_metric);
		unitPriority = (TextView) findViewById(R.id.tv_piority);
		metric = (RelativeLayout) findViewById(R.id.layout_metric);
		priority = (RelativeLayout) findViewById(R.id.layout_piority);
		account = (RelativeLayout) findViewById(R.id.layout_account);
	}

}
