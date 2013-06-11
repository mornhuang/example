package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amaker.dao.PeopleDao;
import com.amaker.entity.People;
import com.amaker.util.DBUtil;

/**
 * 
 * @author 郭宏志
 * 在逃人口查询实现
 */
public class PeopleDaoImpl implements PeopleDao {
	
	/*
	 * 返回People信息字符串
	 */
	public String getString(String idno) {
		return get(idno).toString();
	}
	
	/*
	 * 返回People对象
	 */
	public People get(String idno) {
		String sql = " select id,name,age,gender,address,crimerecord,idno,pic from escapedpeopletbl where idno=?  ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, idno);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String gender = rs.getString(4);
				String address = rs.getString(5);
				String crimerecord = rs.getString(6);
				//String idno = rs.getString(6);
				String pic = rs.getString(8);
				
				People p = new People();
				p.setId(id);
				p.setName(name);
				p.setAge(age);
				p.setAddress(address);
				p.setCrimerecord(crimerecord);
				p.setGender(gender);
				p.setPic(pic);
				
				p.setIdno(idno);
				
				return p;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
		
	}

}
