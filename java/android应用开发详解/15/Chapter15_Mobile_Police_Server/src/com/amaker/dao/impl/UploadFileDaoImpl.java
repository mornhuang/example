package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amaker.dao.UploadFileDao;
import com.amaker.entity.UploadFile;
import com.amaker.util.DBUtil;
/**
 * 
 * @author 郭宏志
 * 上传文件DAO实现
 */
public class UploadFileDaoImpl implements UploadFileDao {
	// 保存上传文件
	public boolean save(UploadFile f) {
		String sql = " insert into UploadFileTbl(uploadTime,fileDesc,filePath)"
				+ " values(?,?,?)  ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, f.getUploadTime());
			pstmt.setString(2, f.getFileDesc());
			pstmt.setString(3, f.getFilePath());

			if (pstmt.executeUpdate() > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return false;
	}
}
