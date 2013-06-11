package com.amaker.ch17.app;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemClickListener;

import com.amaker.ch17.app.TaskList.Tasks;
/**
 * 
 * @author 郭宏志
 * 备忘录详细信息类，该信息
 */
public class TaskDetailActivity extends ListActivity {
	// 备忘录信息列表
	private ListView listView = null;
	// 提醒日期
	private int mYear;
	private int mMonth;
	private int mDay;
	// 提醒时间
	private int mHour;
	private int mMinute;
	// 日期显示TextView
	private TextView dateName, dateDesc;
	// 时间显示TextView
	private TextView timeName, timeDesc;
	// 提醒内容TextView
	private TextView contentName, contentDesc;
	// 是否开启提醒
	private int on_off = 0;
	// 是否声音警告
	private int alarm = 0;
	
	// 显示日期、时间对话框常量
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;
	
	// 保存内容、日期、时间字符串
	private String content, date1, time1;
	// 备忘录ID
	private int id1;
	// 多选框
	private CheckedTextView ctv1, ctv2;
	// 访问布局实例
	private LayoutInflater li;
	
	// 初始化方法
	private void init(Intent intent) {
		Bundle b = intent.getBundleExtra("b");
		if (b != null) {
			id1 = b.getInt("id");
			content = b.getString("content");
			date1 = b.getString("date1");
			time1 = b.getString("time1");
			on_off = b.getInt("on_off");
			alarm = b.getInt("alarm");

			if (date1 != null && date1.length() > 0) {
				String[] strs = date1.split("/");
				mYear = Integer.parseInt(strs[0]);
				mMonth = Integer.parseInt(strs[1]);
				mDay = Integer.parseInt(strs[2]);
			}

			if (time1 != null && time1.length() > 0) {
				String[] strs = time1.split(":");
				mHour = Integer.parseInt(strs[0]);
				mMinute = Integer.parseInt(strs[1]);
			}
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 获得ListView
		listView = getListView();
		// 实例化LayoutInflater
		li = getLayoutInflater();
		// 设置ListView Adapter 
		listView.setAdapter(new ViewAdapter());
		// 可多选
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		// 获得Calendar实例
		final Calendar c = Calendar.getInstance();
		// 获得当前日期、时间
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
		
		// 响应列表单击事件
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> av, View v, int position,
					long id) {

				switch (position) {
				// 设置是否开启提醒
				case 0:
					ctv1 = (CheckedTextView) v;
					if (ctv1.isChecked()) {
						on_off = 0;
					} else {
						on_off = 1;
					}
					break;
				// 设置提醒日期
				case 1:
					showDialog(DATE_DIALOG_ID);
					break;
				// 设置提醒时间
				case 2:
					showDialog(TIME_DIALOG_ID);
					break;
				// 设置提醒内容
				case 3:
					showDialog1("请输入内容：");
					break;
				// 设置是否开启语音提醒
				case 4:
					ctv2 = (CheckedTextView) v;
					if (ctv2.isChecked()) {
						alarm = 0;
						setAlarm(false);
					} else {
						alarm = 1;
						setAlarm(true);
					}
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 初始化列表
		init(getIntent());
	}
	// ListView Adatper，该类实现了列表的每一项通过自定义视图实现
	class ViewAdapter extends BaseAdapter {
		// 列表显示内容
		String[] strs = { "是否开启", "日期", "时间", "内容", "开启闹钟" };
		// 获得列表列数
		@Override
		public int getCount() {
			return strs.length;
		}
		// 返回列表项
		@Override
		public Object getItem(int position) {
			return position;
		}
		// 返回列表ID
		@Override
		public long getItemId(int position) {
			return position;
		}
		// 获得当前列表项视图
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = li.inflate(R.layout.item_row, null);
			switch (position) {
			// 是否开启该条备忘录
			case 0:
				ctv1 = (CheckedTextView) li
						.inflate(
								android.R.layout.simple_list_item_multiple_choice,
								null);
				ctv1.setText(strs[position]);
				if (on_off == 0) {
					ctv1.setChecked(false);
				} else {
					ctv1.setChecked(true);
				}
				return ctv1;
			// 提醒日期
			case 1:
				dateName = (TextView) v.findViewById(R.id.name);
				dateDesc = (TextView) v.findViewById(R.id.desc);
				dateName.setText(strs[position]);
				dateDesc.setText(mYear + "/" + mMonth + "/" + mDay);
				return v;
			// 提醒时间
			case 2:
				timeName = (TextView) v.findViewById(R.id.name);
				timeDesc = (TextView) v.findViewById(R.id.desc);
				timeName.setText(strs[position]);
				timeDesc.setText(mHour + ":" + mMinute);
				return v;
			// 提醒内容
			case 3:
				contentName = (TextView) v.findViewById(R.id.name);
				contentDesc = (TextView) v.findViewById(R.id.desc);
				contentName.setText(strs[position]);
				contentDesc.setText(content);
				return v;
			// 是否声音提示
			case 4:
				ctv2 = (CheckedTextView) li
						.inflate(
								android.R.layout.simple_list_item_multiple_choice,
								null);
				ctv2.setText(strs[position]);

				if (alarm == 0) {
					ctv2.setChecked(false);
				} else {
					ctv2.setChecked(true);
				}
				return ctv2;
			default:
				break;
			}

			return null;
		}
	}
	
	// 显示对话框
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		// 显示日期对话框
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		// 显示时间对话框
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute,
					false);
		}
		return null;
	}
	
	// 设置通知提示
	private void setAlarm(boolean flag) {
		final String BC_ACTION = "com.amaker.ch17.TaskReceiver";
		// 获得AlarmManager实例
		final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		// 实例化Intent
		Intent intent = new Intent();
		// 设置Intent action属性
		intent.setAction(BC_ACTION);
		intent.putExtra("msg", content);
		// 实例化PendingIntent
		final PendingIntent pi = PendingIntent.getBroadcast(
				getApplicationContext(), 0, intent, 0);
		// 获得系统时间
		final long time1 = System.currentTimeMillis();
		Calendar c = Calendar.getInstance();
		c.set(mYear, mMonth, mDay, mHour, mMinute);
		long time2 = c.getTimeInMillis();
		if (flag&&(time2-time1)>0&&on_off==1){
			am.set(AlarmManager.RTC_WAKEUP, time2, pi);
		}else{
			am.cancel(pi);
		}
	}

	/*
	 * 设置提示日期对话框
	 */
	private void showDialog1(String msg) {
		View v = li.inflate(R.layout.item_content, null);
		final EditText contentET = (EditText) v.findViewById(R.id.content);
		contentET.setText(content);
		new AlertDialog.Builder(this).setView(v).setMessage(msg).setCancelable(
				false).setPositiveButton("确定",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						content = contentET.getText().toString();
						contentDesc.setText(content);
					}
				}).show();
	}
	// 时间选择对话框
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = 
		new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			timeDesc.setText(mHour + ":" + mMinute);
		}
	};
	// 日期选择对话框
	private DatePickerDialog.OnDateSetListener mDateSetListener = 
		new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			dateDesc.setText(mYear + "/" + mMonth + "/" + mDay);
		}
	};
	// 保存或修改备忘录信息
	protected void onPause() {
		super.onPause();
		saveOrUpdate();
	};
	
	// 保存或修改备忘录信息
	private void saveOrUpdate() {
		ContentValues values = new ContentValues();

		values.clear();

		values.put(Tasks.CONTENT, contentDesc.getText().toString());
		values.put(Tasks.DATE1, dateDesc.getText().toString());
		values.put(Tasks.TIME1, timeDesc.getText().toString());

		values.put(Tasks.ON_OFF, ctv1.isChecked() ? 1 : 0);
		values.put(Tasks.ALARM, ctv2.isChecked() ? 1 : 0);

		// 修改
		if (id1 != 0) {
			Uri uri = ContentUris.withAppendedId(Tasks.CONTENT_URI, id1);
			getContentResolver().update(uri, values, null, null);
		// 保存
		} else {
			Uri uri = TaskList.Tasks.CONTENT_URI;
			getContentResolver().insert(uri, values);
		}

	}

}