package cn.hxex.tutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserRegistrationAction extends Action {

	public ActionForward execute(ActionMapping mapping, 
			ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)
			throws Exception {

		if (isCancelled(request)) {
			return mapping.findForward("welcome");
		}

		UserRegistrationForm userForm = (UserRegistrationForm) form;
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("insert into USERINFO "
							+ "(EMAIL, USER_NAME, LOG_NAME, PASSWORD, PHONE, FAX)"
							+ " values (?,?,?,?,?,?)");
			statement.setString(1, userForm.getEmail());
			statement.setString(2, userForm.getUserName());
			statement.setString(3, userForm.getLogName());
			statement.setString(4, userForm.getPassword());
			statement.setString(5, userForm.getPhone());
			statement.setString(6, userForm.getFax());
			statement.executeUpdate();
		}catch( SQLException ex ) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DatabaseConnection.releaseConnection( conn, statement, null );
		}
		return mapping.findForward("success");
	}

}