package com.amaker.ch10.app;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author 郭宏志
 * 通讯录常量类
 */
public final class Employees {
	// 授权常量
    public static final String AUTHORITY = "com.amaker.provider.Employees";
    private Employees() {}
    // 内部类
    public static final class Employee implements BaseColumns {
    	// 构造方法
        private Employee() {}
        // 访问Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/employee");
        // 内容类型
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.amaker.employees";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.amaker.employees";
        
        // 默认排序常量
        public static final String DEFAULT_SORT_ORDER = "name DESC";// 按姓名排序
        // 表字段常量
        public static final String NAME = "name";					// 姓名
        public static final String GENDER= "gender";				// 性别
        public static final String AGE = "age"; 					// 年龄
    }
}
