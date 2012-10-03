//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.action.struts.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

/** 
 * MyEclipse Struts
 * Creation date: 06-21-2006
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class OpenFileAction extends DownloadAction {

	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String contentType = (String) session.getAttribute("contentType");
		String fileName = (String) session.getAttribute("fileName");

		// Set the content disposition
		response.setHeader("Content-disposition", "inline; filename="
				+ fileName);

		File file = new File( "c:\\" + fileName);
		return new FileStreamInfo(contentType, file);

	}

}
