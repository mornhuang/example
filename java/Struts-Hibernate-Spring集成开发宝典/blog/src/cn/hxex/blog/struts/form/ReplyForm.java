//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.blog.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-24-2006
 * 
 * XDoclet definition:
 * @struts.form name="replyForm"
 */
public class ReplyForm extends ValidatorForm {

	// --------------------------------------------------------- Instance Variables

	/** title property */
	private String title;

	/** username property */
	private String username;

	/** content property */
	private String content;
	
	/** messageId property */
	private String messageId;

	// --------------------------------------------------------- Methods

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		this.username = null;
		this.content = null;
		this.title = null;
	}

	/** 
	 * Returns the title.
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/** 
	 * Set the title.
	 * @param title The title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * Returns the content.
	 * @return String
	 */
	public String getContent() {
		return content;
	}

	/** 
	 * Set the content.
	 * @param content The content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/** 
	 * Returns the messageId.
	 * @return String
	 */
	public String getMessageId() {
		return messageId;
	}

	/** 
	 * Set the messageId.
	 * @param messageId The messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}

