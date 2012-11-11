//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.actionform.struts.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 06-04-2006
 * 
 * XDoclet definition:
 * @struts.form name="mapForm"
 */
public class MapForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// TODO Auto-generated method stub
	}

    private static String[] skillLevels = 
        new String[] {"Beginner","Intermediate","Advanced"};
    private Map skills = new HashMap( );

    public Object getSkill(String key) { 
        return skills.get(key);
    }

    public void setSkill(String key, Object value) { 
        skills.put(key, value); 
    }
  
    public Map getSkills( ) {
        return skills;
    }
    
    public String[] getSkillLevels( ) {
        return skillLevels;
    }

}

