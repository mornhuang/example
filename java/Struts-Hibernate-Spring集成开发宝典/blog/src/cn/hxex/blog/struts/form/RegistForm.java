//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.blog.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-22-2006
 * 
 * XDoclet definition:
 * @struts.form name="registForm"
 */
public class RegistForm extends ValidatorForm {

	// --------------------------------------------------------- Instance Variables

	/** password property */
	private String password;

	/** username property */
	private String username;

	/** repassword property */
	private String repassword;

	// --------------------------------------------------------- Methods

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		this.username = null;
		this.password = null;
		this.repassword = null;
	}

	/** 
	 * Returns the password.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * Set the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * Returns the username.
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/** 
	 * Set the username.
	 * @param username The username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** 
	 * Returns the repassword.
	 * @return String
	 */
	public String getRepassword() {
		return repassword;
	}

	/** 
	 * Set the repassword.
	 * @param repassword The repassword to set
	 */
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

}

