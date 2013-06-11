package com.amaker.ch10.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.amaker.ch10.app.Employees.Employee;
/**
 * 
 * @author 郭宏志
 * 数据库工具类
 */
public class DBHelper extends SQLiteOpenHelper{
    // 数据库名称常量
    private static final String DATABASE_NAME = "Employees.db";
    // 数据库版本常量
    private static final int DATABASE_VERSION = 1;
    // 表名称常量
    public static final String EMPLOYEES_TABLE_NAME = "employee";
	// 构造方法
	public DBHelper(Context context) {
		// 创建数据库
		super(context, DATABASE_NAME,null, DATABASE_VERSION);
	}

	// 创建时调用
	public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EMPLOYEES_TABLE_NAME + " ("
                + Employee._ID + " INTEGER PRIMARY KEY,"
                + Employee.NAME + " TEXT,"
                + Employee.GENDER + " TEXT,"
                + Employee.AGE + " INTEGER"
                + ");");
	}

	// 版本更新时调用
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 删除表
		db.execSQL("DROP TABLE IF EXISTS employee");
        onCreate(db);
	}

}
