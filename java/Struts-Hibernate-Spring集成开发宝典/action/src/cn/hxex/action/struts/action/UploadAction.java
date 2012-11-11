//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.action.struts.action;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import cn.hxex.action.struts.form.UploadForm;

/**
 * MyEclipse Struts Creation date: 06-21-2006
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/upload" name="uploadForm" input="/upload.jsp"
 *                scope="request"
 */
public class UploadAction extends Action {

	// --------------------------------------------------------- Instance
	// Variables

	// --------------------------------------------------------- Methods

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UploadForm uploadForm = (UploadForm) form;

		// Process the FormFile
		FormFile myFile = uploadForm.getTheFile();
		String contentType = myFile.getContentType();
		String fileName = myFile.getFileName();
		int fileSize = myFile.getFileSize();
		byte[] fileData = myFile.getFileData();
		System.out.println("contentType: " + contentType);
		System.out.println("File Name: " + fileName);
		System.out.println("File Size: " + fileSize);

		FileOutputStream out = new FileOutputStream(new File("c:\\" + fileName));
		out.write(fileData);
		out.close();

		HttpSession session = request.getSession();
		session.setAttribute("contentType", contentType);
		session.setAttribute("fileName", fileName);
		session.setAttribute("fileSize", Integer.valueOf(fileSize));

		return mapping.findForward("download");
	}

}
