package com.amaker.dao;
import com.amaker.entity.People;
/**
 * @author 郭宏志
 * 在逃人口查询接口
 */
public interface PeopleDao {
	/*
	 * 返回People信息字符串
	 */
	public String getString(String idno);
	/*
	 * 返回People对象
	 */
	public People get(String idno);
}
