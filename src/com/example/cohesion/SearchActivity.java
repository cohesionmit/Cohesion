package com.example.cohesion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class SearchActivity extends Activity {
	
	ArrayList<Users> users;
	TableLayout table_layout;
	Map<String,String> remaining;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		Map<String,String> nCourses = new HashMap<String, String>();
        nCourses.put("6.006", "DONE");
        nCourses.put("6.888", "STARTED");
		Users nikki=new Users("Nikki","Ivers",1000,nCourses);
		Map<String,String> dCourses = new HashMap<String, String>();
        dCourses.put("21W.789", "TO DO");
		Users dan=new Users("Dan","Patrickson",1400,dCourses);
		Map<String,String> lCourses = new HashMap<String, String>();
        lCourses.put("6.886", "STARTED");
		Users lauren=new Users("Lauren","Linwood",5280,lCourses);
		users=new ArrayList<Users>();
		users.add(nikki);
		users.add(dan);
		users.add(lauren);
		
		table_layout = (TableLayout) findViewById(R.id.tableLayout1);
		updateTable();
	}
	
	public void updateTable() {
		for (Users user: users)
			addRow(user);
	}
	
	public void addRow(Users user) {
		int count=0;
		remaining=user.getCourses();
		TableRow row = new TableRow(this);
		   row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		     LayoutParams.WRAP_CONTENT));
		TextView name = new TextView(this);
		name.setText(user.getFirstName()+" "+user.getLastName());
		row.addView(name);
		for (Map.Entry<String,String> entry: remaining.entrySet()) {
			if (count==0) {
				TextView course = new TextView(this);
				course.setText(entry.getKey());
				TextView status = new TextView(this);
				status.setText(entry.getValue());
				row.addView(course);
				row.addView(status);
				table_layout.addView(row);
				count++;
				row=new TableRow(this);
			}
			else if (count==1) {
				TextView loc = new TextView(this);
				loc.setText(""+user.getLocation());
				row.addView(loc);
				TextView course = new TextView(this);
				course.setText(entry.getKey());
				TextView status = new TextView(this);
				status.setText(entry.getValue());
				row.addView(course);
				row.addView(status);
				table_layout.addView(row);
				count++;
				row=new TableRow(this);
			}
			else {
				row.addView(new TextView(this));
				TextView course = new TextView(this);
				course.setText(entry.getKey());
				TextView status = new TextView(this);
				status.setText(entry.getValue());
				row.addView(course);
				row.addView(status);
				table_layout.addView(row);
				count++;
				row=new TableRow(this);
			}
		}
		if (count==1) {
			TextView loc = new TextView(this);
			loc.setText(""+user.getLocation());
			row.addView(loc);
			table_layout.addView(row);
		}
		if (count==0) {
			TextView tname = new TextView(this);
			tname.setText(user.getFirstName()+" "+user.getLastName());
			row.addView(tname);
			table_layout.addView(row);
			TextView loc = new TextView(this);
			loc.setText(""+user.getLocation());
			row.addView(loc);
			table_layout.addView(row);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
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
}
