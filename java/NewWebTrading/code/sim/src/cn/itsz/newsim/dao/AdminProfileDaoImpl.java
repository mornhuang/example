package cn.itsz.newsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.common.MD5;
import cn.itsz.newsim.dao.hibernate.model.AdminProfileModel;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.AdminChangePwdRequest;
import cn.itsz.newsim.dto.response.AdminLoginResponse;
import cn.itsz.newsim.dto.response.entity.AdminLoginResponseEntity;
import cn.itsz.newsim.exception.ITSZException;
@Component(value = "adminProfileDao")
public class AdminProfileDaoImpl implements AdminProfileDao {

	private String sql_query = "select * from admin where uname = ?";
	private String sql_udpate = "update admin set upass=? where uname = ? and upass = ?";
	

	@Autowired
	private DataSource dataSource;

	@Override
	public ResultMessage adminLogin(AdminProfileModel adminProfileModel) {
		AdminLoginResponse resultMessage = new AdminLoginResponse();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql_query);
			ps.setString(1, adminProfileModel.getUname());
			rs = ps.executeQuery();
			if (rs.next()) {
				String pass = rs.getString("upass");
				if (pass.equals(MD5.Md5(adminProfileModel.getUpass()))) {
					AdminLoginResponseEntity ale = new AdminLoginResponseEntity();
					ale.setUname(rs.getString("uname"));
					ale.setUpass(pass);
					resultMessage.setReturnCode(Constants.Status.NORMAL);
					resultMessage.setAdminLoginResponseEntity(ale);
				} else {
					resultMessage.setReturnCode(Constants.Status.WARN);
				}
			} else {
				resultMessage.setReturnCode(Constants.Status.WARN);
			}
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMessage;
	}

	@Override
	public boolean adminChangePwd(AdminChangePwdRequest model) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql_udpate);
			ps.setString(1, MD5.Md5(model.getNewUpass()));
			ps.setString(2, model.getUname());
			ps.setString(3, MD5.Md5(model.getUpass()));
			int rl = ps.executeUpdate();
			conn.commit();
			if (rl > 0) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			e.printStackTrace();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
