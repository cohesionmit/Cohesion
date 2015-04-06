package com.example.cohesion;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

public class SettingsActivity extends Activity implements OnClickListener {

	PopupWindow popupMessage;
	Button insidePopupButton, add, delete;
	TextView popupText, nameField;
	TableLayout table_layout;
	EditText fillClass;
	ArrayList<String> courses=new ArrayList<String>();
	String name;
	ArrayList<Button> deletes=new ArrayList<Button>();
	int currentID=0;
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		courses.add("6.006");
		courses.add("6.886");
		courses.add("6.888");
		courses.add("21W.789");
		table_layout = (TableLayout) findViewById(R.id.tableLayout1);
		nameField = (TextView)findViewById(R.id.name);
		name = nameField.getText().toString();
		for (String course: courses)
			addRow(course);
		add=(Button) findViewById(R.id.addbutton);
		add.setOnClickListener(this);
		fillClass=(EditText)findViewById(R.id.popup);
		insidePopupButton = (Button)findViewById(R.id.popupbutton);
		insidePopupButton.setOnClickListener(this);
//		LinearLayout popupBox = new LinearLayout(this);
//		popupBox.addView(fillClass);
//		popupBox.addView(insidePopupButton);
//		popupMessage = new PopupWindow(popupBox, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
//		popupMessage.setContentView(popupBox);
	} 
	
	@Override public void onClick(View v) { 
		if (v.getId() == add.getId()) { 
//			popupMessage.showAtLocation(add, Gravity.CENTER, 0, 0);
			fillClass.setVisibility(View.VISIBLE);
			insidePopupButton.setVisibility(View.VISIBLE);
			nameField.clearFocus();
			fillClass.requestFocus();
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		} 
		else if (v.getId() == insidePopupButton.getId()) {
			String newClass = fillClass.getText().toString();
			courses.add(newClass);
			addRow(newClass);
			fillClass.setVisibility(View.INVISIBLE);
			insidePopupButton.setVisibility(View.INVISIBLE);
			fillClass.setText("");
		}
		else { 
			int deleteID=-1;
			int deleteIndex=-1;
			for (int i=0; i<deletes.size(); i++) {
				if (v.getId() == deletes.get(i).getId()) {
					deleteID=deletes.get(i).getId();
					deleteIndex=i;
				}
			}
			if (deleteID>-1) {
				deletes.remove(deleteIndex);
				deleteRow(deleteIndex);
			}
		}
	}
	
	public void addRow(String newClass) {
		TableRow row = new TableRow(this);
		   row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		     LayoutParams.WRAP_CONTENT));
		TextView addClass = new TextView(this);
		addClass.setText(newClass);
		row.addView(addClass);
		Button delete=new Button(this);
		delete.setOnClickListener(this);
		delete.setText("x");
		delete.setBackgroundColor(Color.TRANSPARENT);
		delete.setId(currentID);
		currentID++;
		deletes.add(delete);
   	 	row.addView(delete);
		table_layout.addView(row);
	}
	
	public void deleteRow(int deleteIndex) {
		table_layout.removeViewAt(deleteIndex);
	}
	
	public ArrayList<String> getCourses() {
		return courses;
	}
	
	public static void showKeyboard(Activity activity) {
	    if (activity != null) {
	        activity.getWindow()
	                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	    }
	}

	public static void hideKeyboard(Activity activity) {
	    if (activity != null) {
	        activity.getWindow()
	                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	    }
	}
	
	public Activity getActivity() {
		return this;
	}
}



