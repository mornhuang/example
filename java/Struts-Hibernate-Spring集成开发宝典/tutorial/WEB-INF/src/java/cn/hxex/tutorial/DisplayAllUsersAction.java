package cn.hxex.tutorial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DisplayAllUsersAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Connection conn = DatabaseConnection.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		try {
			List users = new ArrayList();
			statement = conn.createStatement();
			rs = statement
					.executeQuery("SELECT EMAIL, USER_NAME, LOG_NAME, PHONE, FAX FROM USERINFO ");
			while (rs.next()) {
				User user = new User();

				user.setEmail(rs.getString(1));
				user.setUserName(rs.getString(2));
				user.setLogName(rs.getString(3));
				user.setPhone(rs.getString(4));
				user.setFax(rs.getString(5));

				users.add(user);
			}
			request.setAttribute("users", users);

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DatabaseConnection.releaseConnection(conn, statement, rs);
		}
		return mapping.findForward("display");
	}

}
