package com.amaker.test;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	private Button b1,b2;
	private TextView tv1,tv2;
	private Calendar c;
	private int m_year,m_month,m_day;
	private int m_hour,m_minute;
	
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        b1 = (Button)findViewById(R.id.Button01);
        b2 = (Button)findViewById(R.id.Button02);
        
        c = Calendar.getInstance();
        
        m_year = c.get(Calendar.YEAR);
        m_month = c.get(Calendar.MONTH);
        m_day = c.get(Calendar.DAY_OF_MONTH);
        
        m_hour = c.get(Calendar.HOUR);
        m_minute = c.get(Calendar.MINUTE);
        
        tv1 = (TextView)findViewById(R.id.TextView01);
        
        tv1.setText(m_year+":"+(m_month+1)+":"+m_day);
        
        tv2 = (TextView)findViewById(R.id.TextView02);
        tv2.setText(m_hour+":"+m_minute);
        
        b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(0);
			}
		});
        
        b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(1);
			}
		});
    }
    protected Dialog onCreateDialog(int id) {
    	if(id==0)
    	return new DatePickerDialog(this,
                l1,
                m_year, m_month, m_day);
    	else
    		return new TimePickerDialog(this,
                    l2, m_hour, m_minute, false);
    }
    
    private OnDateSetListener l1 = new OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			m_year = year;
			m_month = monthOfYear;
			m_day = dayOfMonth;
			tv1.setText(m_year+":"+(m_month+1)+":"+m_day);
		}
	};
	
	private OnTimeSetListener l2 = new OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			m_hour = hourOfDay;
			m_minute = minute;
			tv2.setText(m_hour+":"+m_minute);
		}
	};
}