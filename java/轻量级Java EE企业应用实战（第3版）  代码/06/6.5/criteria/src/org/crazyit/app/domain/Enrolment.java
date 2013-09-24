package org.crazyit.app.domain;



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

public class Enrolment

{

	//定义标识属性

	private Integer enrolmentId;

	//定义选课记录所属学年
	private int year;
	
//定义选课记录所属学期

	private int semester;
	//定义选课记录关联的学生实体

	private Student student;

	//定义选课记录关联的课程实体

	private Course course;

	//无参数的构造器
	public Enrolment()
	{
	}
	//初始化全部属性的构造器
	public Enrolment(Integer enrolmentId , int year , int semester)
	{
		this.enrolmentId = enrolmentId;
		this.year = year;
		this.semester = semester;
	}
	
	//enrolmentId属性的setter和getter方法
	public void setEnrolmentId(Integer enrolmentId)
	{
		this.enrolmentId = enrolmentId;
	}
	public Integer getEnrolmentId()
	{
		return this.enrolmentId;
	}
	
	//year属性的setter和getter方法
	public void setYear(int year)
	{
		this.year = year;
	}
	public int getYear()
	{
		return this.year;
	}
	
	//semester属性的setter和getter方法
	public void setSemester(int semester)
	{
		this.semester = semester;
	}
	public int getSemester()
	{
		return this.semester;
	}
	
	//student属性的setter和getter方法
	public void setStudent(Student student)
	{
		this.student = student;
	}
	public Student getStudent()
	{
		return this.student;
	}
	
	//course属性的setter和getter方法
	public void setCourse(Course course)
	{
		this.course = course;
	}
	public Course getCourse()
	{
		return this.course;
	}
}