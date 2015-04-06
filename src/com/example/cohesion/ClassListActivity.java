package com.example.cohesion;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class ClassListActivity extends Activity implements OnClickListener {
	

	ArrayList<String> courses=new ArrayList<String>();
	Button toDo, started, done;
	TableLayout table_layout;
	ArrayList<Button> toDos=new ArrayList<Button>();
	ArrayList<Button> starteds=new ArrayList<Button>();
	ArrayList<Button> dones=new ArrayList<Button>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_list);
		
		courses.add("6.006");
		courses.add("6.886");
		courses.add("6.888");
		courses.add("21W.789");
		table_layout = (TableLayout) findViewById(R.id.tableLayout1);
		updateTable();
	}
	
	public void updateTable() {
		for (String course: courses)
			addRow(course);
	}
	
	public void addRow(String course) {
		TableRow row = new TableRow(this);
		   row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		     LayoutParams.WRAP_CONTENT));
		TextView addClass = new TextView(this);
		addClass.setText(course);
		row.addView(addClass);
		toDo=new Button(this);
		started=new Button(this);
		done=new Button(this);
		toDo.setOnClickListener(this);
		started.setOnClickListener(this);
		done.setOnClickListener(this);
		toDo.setText("To Do");
		started.setText("Started");
		done.setText("Done");
		toDo.setTextSize(14);
		started.setTextSize(14);
		done.setTextSize(14);
		toDo.setBackgroundColor(Color.BLUE);
		started.setBackgroundColor(Color.TRANSPARENT);
		done.setBackgroundColor(Color.TRANSPARENT);
		toDo.setId(toDos.size());
		started.setId(starteds.size()+100);
		done.setId(dones.size()+200);
   	 	row.addView(toDo);
   	 	row.addView(started);
   	 	row.addView(done);
   	 	toDos.add(toDo);
   	 	starteds.add(started);
   	 	dones.add(done);
		table_layout.addView(row);
	}
	
	@Override public void onClick(View v) { 
		for (int i=0; i<toDos.size(); i++) {
			if (v.getId() == toDos.get(i).getId()) {
				findViewById(toDos.get(i).getId()).setBackgroundColor(Color.BLUE);
				findViewById(starteds.get(i).getId()).setBackgroundColor(Color.TRANSPARENT);
				findViewById(dones.get(i).getId()).setBackgroundColor(Color.TRANSPARENT);
			}
		}
		for (int i=0; i<starteds.size(); i++) {
			if (v.getId() == starteds.get(i).getId()) {
				findViewById(toDos.get(i).getId()).setBackgroundColor(Color.TRANSPARENT);
				findViewById(starteds.get(i).getId()).setBackgroundColor(Color.BLUE);
				findViewById(dones.get(i).getId()).setBackgroundColor(Color.TRANSPARENT);
			}
		}
		for (int i=0; i<dones.size(); i++) {
			if (v.getId() == dones.get(i).getId()) {
				findViewById(toDos.get(i).getId()).setBackgroundColor(Color.TRANSPARENT);
				findViewById(starteds.get(i).getId()).setBackgroundColor(Color.TRANSPARENT);
				findViewById(dones.get(i).getId()).setBackgroundColor(Color.BLUE);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.class_list, menu);
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
