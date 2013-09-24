package org.crazyit.app.domain;


	
import java.util.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */ 
public class Student
{
	//定义学生学号，将作为标识属性
	private Integer studentNumber;
	//定义学生姓名属性
	private String name;
	//定义学生实体关联的选课记录实体
	private Set<Enrolment> enrolments
		= new HashSet<Enrolment>();	//无参数的构造器
	public Student()
	{
	}
	//初始化全部属性的构造器
	public Student(Integer studentNumber , String name)
	{
		this.studentNumber = studentNumber;
		this.name = name;
	}
	
	//studentNumber属性的setter和getter方法
	public void setStudentNumber(Integer studentNumber)
	{
		this.studentNumber = studentNumber;
	}
	public Integer getStudentNumber()
	{
		return this.studentNumber;
	}
	
	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	
	//enrolments属性的setter和getter方法
	public void setEnrolments(Set<Enrolment> enrolments)
	{
		this.enrolments = enrolments;
	}
	public Set<Enrolment> getEnrolments()
	{
		return this.enrolments;
	}
}