package com.amaker.ch17.app;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author 郭宏志
 * 备忘录常量类
 */
public final class TaskList {
	// 授权常量
    public static final String AUTHORITY = "com.amaker.provider.TaskList";
    
    private TaskList() {}
    // 内部类
    public static final class Tasks implements BaseColumns {
       
        private Tasks() {}
        // 访问Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/taskLists");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.amaker.tasklist";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.amaker.tasklist";
        
        // 默认排序常量
        public static final String DEFAULT_SORT_ORDER = "created DESC";
        // 表字段常量
        public static final String CONTENT = "content";
        public static final String CREATED= "created";
        public static final String DATE1 = "date1";
        public static final String TIME1 = "time1";
        public static final String ON_OFF = "on_off";
        public static final String ALARM = "alarm";

    }
}
