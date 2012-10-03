package com.itsz.sht.dao.hibernate.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AclRole implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String descr;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;

	private String[] selectFunctionsId;
	private List<AclRoleFnctnPrmisn> roleFunctionPer;
	private Map<String,List<AclRoleFnctnPrmisn>> functionPrmisnMap=new HashMap<String, List<AclRoleFnctnPrmisn>>();
	// Constructors

	/** default constructor */
	public AclRole() {
	}

	/** minimal constructor */
	public AclRole(String roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public AclRole(String roleId, String descr, String initBy, Date initDate,
			String updBy, Date updDate) {
		this.roleId = roleId;
		this.descr = descr;
		this.initBy = initBy;
		this.initDate = initDate;
		this.updBy = updBy;
		this.updDate = updDate;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getInitBy() {
		return this.initBy;
	}

	public void setInitBy(String initBy) {
		this.initBy = initBy;
	}

	public Date getInitDate() {
		return this.initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public String getUpdBy() {
		return this.updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	public boolean checkAllAcl(String functionId){
		if(functionPrmisnMap==null||functionPrmisnMap.size()<=0)
			return false;
		List<AclRoleFnctnPrmisn> aclList=this.functionPrmisnMap.get(functionId);
		if(aclList==null||aclList.size()<=0)
			return false;
		return true;
	}
	
	public boolean checkAcl(String url){
		for(AclRoleFnctnPrmisn rfp:roleFunctionPer){
			String aclUrl=rfp.getFunction().getUrlLink();
			if(null!=aclUrl&&url.indexOf(aclUrl)>-1)
				return true;
		}
		return false;
	}
	
 	public boolean checkViewAcl(String functionId){
		if(functionPrmisnMap==null||functionPrmisnMap.size()<=0)
			return false;
		List<AclRoleFnctnPrmisn> aclList=this.functionPrmisnMap.get(functionId);
		if(aclList==null||aclList.size()<=0)
			return false;
		for(AclRoleFnctnPrmisn rfp:aclList){
			if("V".equals(rfp.getPrmisnCde()))
				return true;	
		}
		return false;
	}

	public String[] getSelectFunctionsId() {
		return selectFunctionsId;
	}

	public void setSelectFunctionsId(String[] selectFunctionsId) {
		this.selectFunctionsId = selectFunctionsId;
	}

	public List<AclRoleFnctnPrmisn> getRoleFunctionPer() {
		return roleFunctionPer;
	}

	public void setRoleFunctionPer(List<AclRoleFnctnPrmisn> roleFunctionPer) {
		this.roleFunctionPer = roleFunctionPer;
	}

	public Map<String, List<AclRoleFnctnPrmisn>> getFunctionPrmisnMap() {
		return functionPrmisnMap;
	}

	public void setFunctionPrmisnMap(
			Map<String, List<AclRoleFnctnPrmisn>> functionPrmisnMap) {
		this.functionPrmisnMap = functionPrmisnMap;
	}
 	

}