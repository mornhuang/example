//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.actionform.struts.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 05-31-2006
 * 
 * XDoclet definition:
 * 
 * @struts.form name="listForm"
 */
public class ListForm extends ActionForm {

	// --------------------------------------------------------- Instance
	// Variables
	private List addresses = new ArrayList();

	private List friends = new ArrayList();

	// --------------------------------------------------------- Methods

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// TODO Auto-generated method stub
		this.addresses.clear();
		this.friends.clear();
	}

	public List getAddresses() {
		return addresses;
	}

	public void setAddresses(List address) {
		this.addresses = address;
	}

	public void setAddress(int index, String address) {
		if (this.addresses.size() > index) {
			this.addresses.set(index, address);
		} else {
			while( this.addresses.size() < index ) {
				this.addresses.add( null );
			}
			this.addresses.add(index, address);
		}
	}

	public String getAddress(int index) {
		if (this.addresses.size() > index) {
			return (String) this.addresses.get(index);
		}
		return null;
	}

	public List getFriends() {
		return friends;
	}

	public void setFriends(List friend) {
		this.friends = friend;
	}

	public void setFriend(int index, String friend) {
		if (this.friends.size() > index) {
			this.friends.set(index, friend);
		} else {
			while( this.friends.size() < index )
			{
				this.friends.add( null );
			}
			this.friends.add(index, friend);
		}
	}

	public String getFriend(int index) {
		if (this.friends.size() > index) {
			return (String) this.friends.get(index);
		}
		return null;
	}
}
