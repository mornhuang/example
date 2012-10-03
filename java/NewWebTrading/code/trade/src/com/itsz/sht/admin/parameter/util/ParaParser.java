package com.itsz.sht.admin.parameter.util;

/*
 * Created on 2005-8-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.itsz.sht.admin.service.ParameterMgrService;
import com.itsz.sht.admin.util.xmlparser;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.user.UserManagement;
import com.taifook.adminportal.model.CsParameter;

/**
 * @author lmzhang
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * �滻com.itsz.admin.parameter.util.ParaParser
 */
public class ParaParser extends xmlparser {

	public ParaParser() {

	}

	// �Ѳ������ݿ�������������ڴ�
	public void initParaTable() {
		ParameterMgrService service =ParameterMgrService.getInstance();
		List parainfos = (List) service.findByAllParameter();
		System.out.println("total parameter size: "+parainfos.size());
		if (parainfos == null || parainfos.size() < 1) {
			return;
		}
		Iterator it = parainfos.iterator();
		while (it.hasNext()) {
			CsParameter para = (CsParameter) it.next();
			String key = para.getKey();
			String value = para.getValue();
			if (key != null && !key.equals("")) {				
				ParaManagement.putParaTable(key, value);
				int k = key.indexOf(Constants.VIEW_PROVIDER);
				if (k > 0) {
					UserManagement.setChannelMap(key.substring(0, k));
				}
			}
		}
	}

	// ����ݿ���������е�paraclass��Ϣ
	public List getParaClassList() {
		ParameterMgrService service =ParameterMgrService.getInstance();
		List classlist = new ArrayList();
		List classes = (List) service.findClassIdByAll();
		if (classes == null || classes.size() < 1) {
			return null;
		}
		Iterator it = classes.iterator();
		while (it.hasNext()) {
			ParaClassInfo pcinfo = new ParaClassInfo();
			String id = (String) it.next();
			pcinfo.setId(id);
			String describe = null;// ����ClassID��Descript��Ŀǰ����ݿ��в��޸��ֶ�,�����ڸ���
			pcinfo.setDescribe(describe);
			if (pcinfo.getId() != null && !pcinfo.getId().equals("")) {
				classlist.add(pcinfo);
			}
		}
		return classlist;
	}

	// ����ݿ���ȡ��ָ��classid������parameter
	public List getParaInfoListByClassid(String classid) {
		ParameterMgrService service = ParameterMgrService.getInstance();
		List parainfolist = new ArrayList();
		List paralist = (List) service.findParameterByClassId(classid);
		if (paralist == null || paralist.size() < 1) {
			return null;
		}
		Iterator it = paralist.iterator();
		while (it.hasNext()) {
			ParaInfo pi = new ParaInfo();
			CsParameter para = (CsParameter) it.next();
			pi.setDescribe(para.getDescription());
			pi.setKey(para.getKey());
			pi.setValue(para.getValue());
			if (pi.getKey() != null && !pi.getKey().equals("")) {
				parainfolist.add(pi);
			}
		}
		return parainfolist;
	}

	// ���ָ����key��ȡparameter��Ϣ
	public ParaInfo getParaInfoByKey(String key) {
		ParameterMgrService service = ParameterMgrService.getInstance();
		CsParameter parameter = (CsParameter) service.findParameterByKey(key);
		if (parameter == null) {
			return null;
		}

		String parakey = parameter.getKey();
		if (parakey != null && parakey.equals(key)) {
			ParaInfo pi = new ParaInfo();
			pi.setKey(parakey);
			pi.setDescribe(parameter.getDescription());
			pi.setValue(parameter.getValue());
			return pi;
		} else {
			return null;
		}
	}

	// ����ݿ���������е�parameter��Ϣ
	public List getParaInfoList() {
		String prevClassId = null;
		List classlist = new ArrayList();
		ParameterMgrService service =ParameterMgrService.getInstance();

		List classes = (List) service.findByAllParameter();
		if (classes == null || classes.size() < 1) {
			return null;
		}
		Iterator it = classes.iterator();
		ParaClassInfo pcinfo = null;
		List paralist = null;
		while (it.hasNext()) {
			CsParameter para = (CsParameter) it.next();
			if (!prevClassId.equals(para.getClassid())) {
				if (pcinfo != null) {
					pcinfo.setParaList(paralist);
				}
				if (pcinfo.getId() != null && !pcinfo.getId().equals("")) {
					classlist.add(pcinfo);
				}
				pcinfo = new ParaClassInfo();
				paralist = new ArrayList();

				String id = para.getClassid();
				pcinfo.setId(id);
				String describe = null;
				pcinfo.setDescribe(describe);
				prevClassId = id;
			}

			ParaInfo pi = new ParaInfo();
			pi.setDescribe(para.getDescription());
			pi.setKey(para.getKey());
			pi.setValue(para.getValue());
			if (pi.getKey() != null && !pi.getKey().equals("")) {
				paralist.add(pi);
			}
		}

		return classlist;
	}
	
    //����ݿ��м����µ�parainfo
    public void addParaInfo(ParaInfo pi){
    	ParameterMgrService service =ParameterMgrService.getInstance();
    	CsParameter para = new CsParameter();
    	para.setClassid(pi.getClassid());
    	para.setKey(pi.getKey());
    	para.setValue(pi.getValue());
    	para.setDescription(pi.getDescribe());
    	para.setUpdateTime(new Date());
    	service.saveOrUpdate(para);
    }
    
    //����ݿ���ɾ��ָ��key��parameter��Ϣ
    public void delParaInfo(String key){
    	ParameterMgrService.getInstance().deleteParameterValueByKey(key);
    }

    //�޸�һ��parameter
    public void modParaInfo(ParaInfo pi){
    	ParameterMgrService service =ParameterMgrService.getInstance();
    	CsParameter para = new CsParameter();
    	para.setClassid(pi.getClassid());
    	para.setKey(pi.getKey());
    	para.setValue(pi.getValue());
    	para.setDescription(pi.getDescribe());
    	para.setUpdateTime(new Date());
    	service.update(para);
    }
}
