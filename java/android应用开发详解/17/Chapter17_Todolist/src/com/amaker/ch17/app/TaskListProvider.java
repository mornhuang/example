package com.amaker.ch17.app;

import java.util.HashMap;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import com.amaker.ch17.app.TaskList.Tasks;

/**
 * @author 郭宏志
 * 提供数据维护方法
 */
public class TaskListProvider extends ContentProvider {
    // 数据库名称常量
    private static final String DATABASE_NAME = "task_list.db";
    // 数据库版本常量
    private static final int DATABASE_VERSION = 1;
    // 表名称常量
    private static final String TASK_LIST_TABLE_NAME = "taskLists";
    // 查询列集合
    private static HashMap<String, String> sTaskListProjectionMap;
    // 查询、更新条件
    private static final int TASKS = 1;
    private static final int TASK_ID = 2;
    // Uri工具类
    private static final UriMatcher sUriMatcher;
    // 数据库工具类实例
    private DatabaseHelper mOpenHelper;
    // 内部工具类，创建或打开数据库，创建或删除表
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        // 创建表
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TASK_LIST_TABLE_NAME + " ("
                    + Tasks._ID + " INTEGER PRIMARY KEY,"
                    + Tasks.DATE1 + " TEXT,"
                    + Tasks.TIME1 + " TEXT,"
                    + Tasks.CONTENT + " TEXT,"
                    + Tasks.ON_OFF + " INTEGER,"
                    + Tasks.ALARM + " INTEGER,"
                    + Tasks.CREATED + " TEXT"
                    + ");");
        }
        // 删除表
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS taskLists");
            onCreate(db);
        }
    }
    
    // 创建或打开数据库
    @Override
    public boolean onCreate() {
        mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }
    
    // 查询
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch (sUriMatcher.match(uri)) {
        // 查询所有
        case TASKS:
            qb.setTables(TASK_LIST_TABLE_NAME);
            qb.setProjectionMap(sTaskListProjectionMap);
            break;
        // 根据ID查询
        case TASK_ID:
            qb.setTables(TASK_LIST_TABLE_NAME);
            qb.setProjectionMap(sTaskListProjectionMap);
            qb.appendWhere(Tasks._ID + "=" + uri.getPathSegments().get(1));
            break;
        default:
            throw new IllegalArgumentException("Uri错误！ " + uri);
        }

        // 使用默认排序
        String orderBy;
        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = TaskList.Tasks.DEFAULT_SORT_ORDER;
        } else {
            orderBy = sortOrder;
        }

        // 获得数据库实例
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        // 返回游标集合
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }
    // 获得类型
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
        case TASKS:
            return Tasks.CONTENT_TYPE;
        case TASK_ID:
            return Tasks.CONTENT_ITEM_TYPE;

        default:
            throw new IllegalArgumentException("错误的 URI： " + uri);
        }
    }
    
    // 保存数据
    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        if (sUriMatcher.match(uri) != TASKS) {
            throw new IllegalArgumentException("错误的 URI： " + uri);
        }
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }
        // 获得数据库实例
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        // 保存数据返回行ID
        long rowId = db.insert(TASK_LIST_TABLE_NAME, Tasks.CONTENT, values);
        if (rowId > 0) {
            Uri taskUri = ContentUris.withAppendedId(TaskList.Tasks.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(taskUri, null);
            return taskUri;
        }
        throw new SQLException("插入数据失败 " + uri);
    }
    
    // 删除数据
    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
    	// 获得数据库实例
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        // 根据指定条件删除
        case TASKS:
            count = db.delete(TASK_LIST_TABLE_NAME, where, whereArgs);
            break;
        // 根据指定条件和ID删除
        case TASK_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.delete(TASK_LIST_TABLE_NAME, Tasks._ID + "=" + noteId
                    + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
            break;

        default:
            throw new IllegalArgumentException("错误的 URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
    
    // 更新数据
    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
    	// 获得数据库实例
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        // 根据指定条件更新
        case TASKS:
            count = db.update(TASK_LIST_TABLE_NAME, values, where, whereArgs);
            break;
        // 根据指定条件和ID更新
        case TASK_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.update(TASK_LIST_TABLE_NAME, values, Tasks._ID + "=" + noteId
                    + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
            break;
        default:
            throw new IllegalArgumentException("错误的 URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    static {
    	// Uri匹配工具类
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(TaskList.AUTHORITY, "taskLists", TASKS);
        sUriMatcher.addURI(TaskList.AUTHORITY, "taskLists/#", TASK_ID);
        
        // 实例化查询列集合
        sTaskListProjectionMap = new HashMap<String, String>();
        // 添加查询列
        sTaskListProjectionMap.put(Tasks._ID, Tasks._ID);
        sTaskListProjectionMap.put(Tasks.CONTENT, Tasks.CONTENT);
        sTaskListProjectionMap.put(Tasks.CREATED, Tasks.CREATED);
        
        sTaskListProjectionMap.put(Tasks.ALARM, Tasks.ALARM);
        sTaskListProjectionMap.put(Tasks.DATE1, Tasks.DATE1);
        sTaskListProjectionMap.put(Tasks.TIME1, Tasks.TIME1);
        
        sTaskListProjectionMap.put(Tasks.ON_OFF, Tasks.ON_OFF);
        
    }
}
