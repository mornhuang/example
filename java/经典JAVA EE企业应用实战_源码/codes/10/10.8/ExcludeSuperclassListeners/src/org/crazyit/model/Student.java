
package org.crazyit.model;

import javax.persistence.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Entity
@Table(name="student_table")
//指定父类上声明的监听器不对实体起作用
@ExcludeSuperclassListeners
public class Student extends Person
{
	private int grade;

	//无参数的构造器
	public Student()
	{
	}
	//初始化全部属性的构造器
	public Student(int grade)
	{
		this.grade = grade;
	}

	//grade属性的setter和getter方法
	public void setGrade(int grade)
	{
		this.grade = grade;
	}
	public int getGrade()
	{
		return this.grade;
	}
}