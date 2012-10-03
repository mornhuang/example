/*
 * LoginActionTest.java
 * Created by Jtest on 11-3-30 17:45:52.
 */

package com.itsz.sht.struts.action;

import com.itsz.sht.common.ChannelsParamInit;
import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.request.LoginRequestModel;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.service.Facade;
import com.itsz.sht.struts.action.LoginAction;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.struts.form.LoginForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jtest.HttpServletRequestAdapter;
import jtest.Stubs;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * LoginActionTest 是这个 LoginAction 类的单元测试类
 * @see com.itsz.sht.struts.action.LoginAction
 * @author Parasoft Jtest 9.0
 */
public class LoginActionTest extends PackageTestCase {
	/**
	 * 构造一个由名称参数指定测试用例的测试.
	 * @param name 测试用例的名字
	 * @author Parasoft Jtest 9.0
	 */
	public LoginActionTest() {
		/*
		 * 此构造函数不应该被修改. 任何初始化代码
		 * 应该放在 setUp() 方法.
		 */
	}

	/**
	 * 针对方法的测试: executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testExecuteAction1() throws Throwable {
		LoginAction testedObject = new LoginAction();
		_fObject = new Object();
		_fObject2 = new Object();
		_fObject3 = new Object();
		_fObject4 = new Object();
		_fObject5 = new Object();
//		LoginForm form = (LoginForm) Stubs.makeStubObject(LoginForm.class);
		LoginForm form=new LoginForm();
		ActionForward result = testedObject.executeAction((ViewProvider) null,
				(ActionMapping) null, form, (HttpServletRequest) null,
				(HttpServletResponse) null);
		// StringIndexOutOfBoundsException thrown, originator is possible setup problem
		// at java.lang.String.substring(String.java:1934)
		// at com.itsz.sht.common.CLVSplitUtil.getVersionID(CLVSplitUtil.java:151)
		// at com.itsz.sht.common.DataModelUtil.form2Model(DataModelUtil.java:25)
		// at com.itsz.sht.struts.action.LoginAction.executeAction(LoginAction.java:53)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testExecuteAction1 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsExecuteAction1(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if ("com.itsz.sht.common.CLVSplitUtil".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getLanguage", argument_types)) {
				return "language7";
			}
		}
		if (Stubs.matches(method, Field.class)) {
			argument_types = new Class[] { Object.class };
			if (Stubs.matches(method, "get", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("java.lang.reflect.Field.get(java.lang.Object)");
				switch (index) {
				case 1:
					return _fObject;
				case 2:
					return _fObject2;
				case 3:
					return _fObject3;
				case 4:
					return _fObject4;
				case 5:
					return _fObject5;
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
			argument_types = new Class[] { Object.class, Object.class };
			if (Stubs.matches(method, "set", argument_types)) {
				return Stubs.VOID;
			}
		}
		if (Stubs.matches(method, ITSZForm.class)) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getCLV", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("com.itsz.sht.struts.form.ITSZForm.getCLV()");
				switch (index) {
				case 1:
				case 4:
					return "-";
				case 2:
					return "";
				case 3:
					return "cLV75";
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
		}
		if ("com.itsz.sht.common.ServiceLocator".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, argument_types)) {
				return (ServiceLocator) Stubs
						.makeStubObject(ServiceLocator.class);
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getService", argument_types)) {
				return null;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testExecuteAction2() throws Throwable {
		LoginAction testedObject = new LoginAction();
		LoginForm form = (LoginForm) Stubs.makeStubObject(LoginForm.class);
		ActionForward result = testedObject.executeAction((ViewProvider) null,
				(ActionMapping) null, form, (HttpServletRequest) null,
				(HttpServletResponse) null);
		// StringIndexOutOfBoundsException thrown, originator is possible setup problem
		// at java.lang.String.substring(String.java:1934)
		// at com.itsz.sht.common.CLVSplitUtil.getLanguage(CLVSplitUtil.java:117)
		// at com.itsz.sht.common.DataModelUtil.form2Model(DataModelUtil.java:24)
		// at com.itsz.sht.struts.action.LoginAction.executeAction(LoginAction.java:53)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testExecuteAction2 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsExecuteAction2(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, BeanCopyUtils.class)) {
			argument_types = new Class[] { Object.class, Object.class };
			if (Stubs.matches(method, "copy", argument_types)) {
				return Stubs.VOID;
			}
		}
		if (Stubs.matches(method, ITSZForm.class)) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getCLV", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("com.itsz.sht.struts.form.ITSZForm.getCLV()");
				switch (index) {
				case 1:
				case 3:
					return "-";
				case 2:
					return "";
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
		}
		if ("com.itsz.sht.common.ServiceLocator".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, argument_types)) {
				return (ServiceLocator) Stubs
						.makeStubObject(ServiceLocator.class);
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getService", argument_types)) {
				return null;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testExecuteAction3() throws Throwable {
		HttpServletRequestAdapter request = new HttpServletRequestAdapter();
		LoginAction testedObject = new LoginAction();
		_fChannelsParamInit = new ChannelsParamInit();
		LoginForm form = (LoginForm) Stubs.makeStubObject(LoginForm.class);
		ActionForward result = testedObject
				.executeAction((ViewProvider) null, (ActionMapping) null, form,
						request, (HttpServletResponse) null);
		// NullPointerException thrown, originator is possible setup problem
		// at com.itsz.sht.common.CLVSplitUtil.mappingChannelId(CLVSplitUtil.java:166)
		// at com.itsz.sht.common.CLVSplitUtil.getChannelId(CLVSplitUtil.java:103)
		// at com.itsz.sht.common.DataModelUtil.form2Model(DataModelUtil.java:26)
		// at com.itsz.sht.struts.action.LoginAction.executeAction(LoginAction.java:53)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testExecuteAction3 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsExecuteAction3(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, BeanCopyUtils.class)) {
			argument_types = new Class[] { Object.class, Object.class };
			if (Stubs.matches(method, "copy", argument_types)) {
				return Stubs.VOID;
			}
		}
		if ("com.itsz.sht.common.CLVSplitUtil".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getLanguage", argument_types)) {
				return "language4";
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getVersionID", argument_types)) {
				return new Integer(2147483647);
			}
		}
		if (Stubs.matches(method, HttpServletRequest.class)) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getServletPath", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("javax.servlet.http.HttpServletRequest.getServletPath()");
				switch (index) {
				case 1:
					return "servletPath8";
				case 2:
					return "servletPath9";
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
		}
		if (Stubs.matches(method, ITSZForm.class)) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getCLV", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("com.itsz.sht.struts.form.ITSZForm.getCLV()");
				switch (index) {
				case 1:
					return " ";
				case 2:
					return "";
				case 3:
					return "cLV62";
				case 4:
					return "cLV63";
				case 5:
					return "-";
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
		}
		if ("com.itsz.sht.common.ServiceLocator".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, argument_types)) {
				return (ServiceLocator) Stubs
						.makeStubObject(ServiceLocator.class);
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getService", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("com.itsz.sht.common.ServiceLocator.getService(java.lang.String)");
				switch (index) {
				case 1:
					return null;
				case 2:
					return _fChannelsParamInit;
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testExecuteAction4() throws Throwable {
		LoginAction testedObject = new LoginAction();
		_fObject = new Object();
		LoginForm form = (LoginForm) Stubs.makeStubObject(LoginForm.class);
		ActionForward result = testedObject.executeAction((ViewProvider) null,
				(ActionMapping) null, form, (HttpServletRequest) null,
				(HttpServletResponse) null);
		// StringIndexOutOfBoundsException thrown, originator is possible setup problem
		// at java.lang.String.substring(String.java:1934)
		// at com.itsz.sht.common.CLVSplitUtil.getVersionID(CLVSplitUtil.java:151)
		// at com.itsz.sht.common.DataModelUtil.form2Model(DataModelUtil.java:25)
		// at com.itsz.sht.struts.action.LoginAction.executeAction(LoginAction.java:53)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testExecuteAction4 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsExecuteAction4(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if ("com.itsz.sht.common.CLVSplitUtil".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getLanguage", argument_types)) {
				return "language3";
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "mappingChannelType", argument_types)) {
				return "Str 1.2 #";
			}
		}
		if (Stubs.matches(method, Field.class)) {
			argument_types = new Class[] { Object.class };
			if (Stubs.matches(method, "get", argument_types)) {
				return _fObject;
			}
		}
		if (Stubs.matches(method, ITSZForm.class)) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getCLV", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("com.itsz.sht.struts.form.ITSZForm.getCLV()");
				switch (index) {
				case 1:
				case 3:
					return "-";
				case 2:
					return "cLV57";
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
		}
		if ("com.itsz.sht.common.ServiceLocator".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, argument_types)) {
				return (ServiceLocator) Stubs
						.makeStubObject(ServiceLocator.class);
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getService", argument_types)) {
				return null;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testExecuteAction6() throws Throwable {
		LoginAction testedObject = new LoginAction();
		_fChannelsParamInit = new ChannelsParamInit();
		Map channelIdMap = new HashMap();
		Map channelMap = new HashMap();
		Map languageMap = new HashMap();
		_fChannelsParamInit.setChannelIdMap(channelIdMap);
		_fChannelsParamInit.setChannelMap(channelMap);
		_fChannelsParamInit.setLanguageMap(languageMap);
		channelMap.put("-", "value1");
		LoginForm form = (LoginForm) Stubs.makeStubObject(LoginForm.class);
		ActionForward result = testedObject.executeAction((ViewProvider) null,
				(ActionMapping) null, form, (HttpServletRequest) null,
				(HttpServletResponse) null);
		// StringIndexOutOfBoundsException thrown, originator is possible setup problem
		// at java.lang.String.substring(String.java:1934)
		// at com.itsz.sht.common.CLVSplitUtil.getVersionID(CLVSplitUtil.java:151)
		// at com.itsz.sht.common.DataModelUtil.form2Model(DataModelUtil.java:25)
		// at com.itsz.sht.struts.action.LoginAction.executeAction(LoginAction.java:53)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testExecuteAction6 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsExecuteAction6(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, BeanCopyUtils.class)) {
			argument_types = new Class[] { Object.class, Object.class };
			if (Stubs.matches(method, "copy", argument_types)) {
				return Stubs.VOID;
			}
		}
		if ("com.itsz.sht.common.CLVSplitUtil".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] { HttpServletRequest.class,
					HttpServletResponse.class, ITSZForm.class, String.class };
			if (Stubs.matches(method, "transNullCLV", argument_types)) {
				return "hello world!";
			}
		}
		if (Stubs.matches(method, ITSZForm.class)) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getCLV", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("com.itsz.sht.struts.form.ITSZForm.getCLV()");
				switch (index) {
				case 1:
				case 3:
					return "";
				case 2:
				case 4:
					return "-";
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "setCLV", argument_types)) {
				return Stubs.VOID;
			}
		}
		if ("com.itsz.sht.common.ServiceLocator".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, argument_types)) {
				return (ServiceLocator) Stubs
						.makeStubObject(ServiceLocator.class);
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getService", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("com.itsz.sht.common.ServiceLocator.getService(java.lang.String)");
				switch (index) {
				case 1:
					return null;
				case 2:
					return _fChannelsParamInit;
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testExecuteAction8() throws Throwable {
		HttpServletRequestAdapter request = new HttpServletRequestAdapter();
		LoginAction testedObject = new LoginAction();
		ViewProvider vp = (ViewProvider) Stubs
				.makeStubObject(ViewProvider.class);
		LoginForm form = (LoginForm) Stubs.makeStubObject(LoginForm.class);
		ActionForward result = testedObject
				.executeAction(vp, (ActionMapping) null, form, request,
						(HttpServletResponse) null);
		// NullPointerException thrown
		// at com.itsz.sht.struts.action.LoginAction.executeAction(LoginAction.java:58)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testExecuteAction8 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsExecuteAction8(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, BeanCopyUtils.class)) {
			argument_types = new Class[] { Object.class, Object.class };
			if (Stubs.matches(method, "copy", argument_types)) {
				return Stubs.VOID;
			}
		}
		if ("com.itsz.sht.common.CLVSplitUtil".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getLanguage", argument_types)) {
				return " ";
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getVersionID", argument_types)) {
				return new Integer(100);
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "mappingChannelType", argument_types)) {
				return "?";
			}
		}
		if (Stubs.matches(method, Facade.class)) {
			argument_types = new Class[] { LoginRequestModel.class };
			if (Stubs.matches(method, "login", argument_types)) {
				return null;
			}
		}
		if (Stubs.matches(method, HttpServletRequest.class)) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getServletPath", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("javax.servlet.http.HttpServletRequest.getServletPath()");
				switch (index) {
				case 1:
					return "servletPath4";
				case 2:
					return "servletPath5";
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
		}
		if (Stubs.matches(method, ITSZForm.class)) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getCLV", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("com.itsz.sht.struts.form.ITSZForm.getCLV()");
				switch (index) {
				case 1:
				case 5:
					return "";
				case 2:
					return "-";
				case 3:
					return "cLV36";
				case 4:
					return "cLV37";
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
		}
		if ("com.itsz.sht.common.ServiceLocator".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, argument_types)) {
				return (ServiceLocator) Stubs
						.makeStubObject(ServiceLocator.class);
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getService", argument_types)) {
				int index = _stubs_iteration
						.getIterationCount("com.itsz.sht.common.ServiceLocator.getService(java.lang.String)");
				switch (index) {
				case 1:
					return (Facade) Stubs.makeStubObject(Facade.class);
				case 2:
					return null;
				default:
					return Stubs.NO_STUB_GENERATED;
				}
			}
		}
		if (Stubs.matches(method, ServletRequest.class)) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getRemoteAddr", argument_types)) {
				return "remoteAddr9";
			}
		}
		if (Stubs.matches(method, ViewProvider.class)) {
			argument_types = new Class[] { ProcessBean.class };
			if (Stubs.matches(method, "processLogin", argument_types)) {
				return null;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#executeAction(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testExecuteAction27() throws Throwable {
		LoginAction testedObject = new LoginAction();
		ActionForward result = testedObject.executeAction((ViewProvider) null,
				(ActionMapping) null, (ActionForm) null,
				(HttpServletRequest) null, (HttpServletResponse) null);
		// NullPointerException thrown, originator is arg 3 to <Method com.itsz.sht.struts.action.LoginAction.executeAction(Lcom/itsz/sht/vp/ViewProvider;Lorg/apache/struts/action/ActionMapping;Lorg/apache/struts/action/ActionForm;Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)Lorg/apache/struts/action/ActionForward;>
		// at com.itsz.sht.common.DataModelUtil.form2Model(DataModelUtil.java:23)
		// at com.itsz.sht.struts.action.LoginAction.executeAction(LoginAction.java:53)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testExecuteAction27 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsExecuteAction27(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if ("com.itsz.sht.common.ServiceLocator".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, argument_types)) {
				return (ServiceLocator) Stubs
						.makeStubObject(ServiceLocator.class);
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getService", argument_types)) {
				return null;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: isLoginActionExecuted()
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#isLoginActionExecuted()
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testIsLoginActionExecuted1() throws Throwable {
		LoginAction testedObject = new LoginAction();
		boolean result = testedObject.isLoginActionExecuted();
		assertEquals(true, result); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 指定当运行 testIsLoginActionExecuted1 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsIsLoginActionExecuted1(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if ("com.itsz.sht.common.ServiceLocator".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, argument_types)) {
				return (ServiceLocator) Stubs
						.makeStubObject(ServiceLocator.class);
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getService", argument_types)) {
				return null;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: LoginAction()
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#LoginAction()
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testLoginAction1() throws Throwable {
		LoginAction testedObject = new LoginAction();
		assertEquals(true, testedObject.isLoginActionExecuted()); // jtest_unverified
		assertEquals(false, testedObject.isTokenRequired()); // jtest_unverified
		assertEquals(null, testedObject.getServlet()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: processException(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,com.itsz.sht.exception.ITSZException,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#processException(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,com.itsz.sht.exception.ITSZException,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testProcessException1() throws Throwable {
		LoginAction testedObject = new LoginAction();
		ITSZException exceptionBean = new ITSZException();
		exceptionBean.setErrorCode("errorCode18");
		exceptionBean.setErrorMessage("errorMessage13");
		ViewProvider vp = (ViewProvider) Stubs
				.makeStubObject(ViewProvider.class);
		ActionMapping mapping = (ActionMapping) Stubs
				.makeStubObject(ActionMapping.class);
		ActionForward result = testedObject.processException(vp, mapping,
				exceptionBean, (HttpServletRequest) null,
				(HttpServletResponse) null);
		assertEquals(null, result); // jtest_unverified
		assertEquals(true, testedObject.isLoginActionExecuted()); // jtest_unverified
		assertEquals(false, testedObject.isTokenRequired()); // jtest_unverified
		assertEquals(null, testedObject.getServlet()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 指定当运行 testProcessException1 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsProcessException1(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if ("com.itsz.sht.common.ServiceLocator".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, argument_types)) {
				return (ServiceLocator) Stubs
						.makeStubObject(ServiceLocator.class);
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getService", argument_types)) {
				return null;
			}
		}
		if (Stubs.matches(method, ViewProvider.class)) {
			argument_types = new Class[] { ProcessBean.class };
			if (Stubs.matches(method, "processException", argument_types)) {
				return null;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: processException(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,com.itsz.sht.exception.ITSZException,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#processException(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,com.itsz.sht.exception.ITSZException,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testProcessException16() throws Throwable {
		LoginAction testedObject = new LoginAction();
		_fActionForward = new ActionForward("Str 1.2 #", "Mr. Bob Smith",
				false, false);
		ViewProvider vp = (ViewProvider) Stubs
				.makeStubObject(ViewProvider.class);
		ActionForward result = testedObject.processException(vp,
				(ActionMapping) null, (ITSZException) null,
				(HttpServletRequest) null, (HttpServletResponse) null);
		assertEquals(_fActionForward, result); // jtest_unverified
		assertEquals(true, testedObject.isLoginActionExecuted()); // jtest_unverified
		assertEquals(false, testedObject.isTokenRequired()); // jtest_unverified
		assertEquals(null, testedObject.getServlet()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 指定当运行 testProcessException16 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsProcessException16(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, AbstractApplicationContext.class)) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getBean", argument_types)) {
				return null;
			}
		}
		if (Stubs.matches(method, ClassPathXmlApplicationContext.class)) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, argument_types)) {
				return (ClassPathXmlApplicationContext) Stubs
						.makeStubObject(ClassPathXmlApplicationContext.class);
			}
		}
		if (Stubs.matches(method, ViewProvider.class)) {
			argument_types = new Class[] { ProcessBean.class };
			if (Stubs.matches(method, "processException", argument_types)) {
				return _fActionForward;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: processException(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,com.itsz.sht.exception.ITSZException,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see LoginAction#processException(com.itsz.sht.vp.ViewProvider,org.apache.struts.action.ActionMapping,com.itsz.sht.exception.ITSZException,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testProcessException18() throws Throwable {
		LoginAction testedObject = new LoginAction();
		ActionForward result = testedObject.processException(
				(ViewProvider) null, (ActionMapping) null,
				(ITSZException) null, (HttpServletRequest) null,
				(HttpServletResponse) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.struts.action.LoginAction.processException(Lcom/itsz/sht/vp/ViewProvider;Lorg/apache/struts/action/ActionMapping;Lcom/itsz/sht/exception/ITSZException;Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)Lorg/apache/struts/action/ActionForward;>
		// at com.itsz.sht.struts.action.LoginAction.processException(LoginAction.java:78)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testProcessException18 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsProcessException18(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if ("com.itsz.sht.common.ServiceLocator".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, argument_types)) {
				return (ServiceLocator) Stubs
						.makeStubObject(ServiceLocator.class);
			}
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "getService", argument_types)) {
				return null;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 用于建立测试. 这个方法是 JUnit 在每个测试执行前
	 * 所调用的.
	 * @see junit.framework.TestCase#setUp()
	 * @author Parasoft Jtest 9.0
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		/*
		 * 在这里添加必要的初始化代码 (例, open a socket).
		 * 调用 Repository.putTemporary() 来提供测试时所需要的
		 * 对象初始化实例.
		 */
		// jtest.Repository.putTemporary("name", object);
		_stubs_iteration = new jtest.StubIterationCounter();
	}

	/**
	 * 用于在测试后清理. 这个方法是 JUnit 在每个测试完成后
	 * 所调用的.
	 * @see junit.framework.TestCase#tearDown()
	 * @author Parasoft Jtest 9.0
	 */
	@After
	public void tearDown() throws Exception {
		try {
			/*
			 * 在这里添加必要的清理代码 (例, close a socket).
			 */
		} finally {
			super.tearDown();
		}
	}

	/**
	 * 实用程序的主要方法.  运行在测试类中所定义的测试用例.
	 * 
	 * 用法: java LoginActionTest
	 * @param args 不需要命令行参数
	 * @author Parasoft Jtest 9.0
	 */
	public static void main(String[] args) {
		// junit.textui.TestRunner 将打印测试结果到 stdout.

		org.junit.runner.JUnitCore
				.main("com.itsz.sht.struts.action.LoginActionTest");
	}

	/**
	 * 用来获取要测试的类对象.
	 * @return 将被测试的类
	 * @author Parasoft Jtest 9.0
	 */
	public Class getTestedClass() {
		return LoginAction.class;
	}

	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private org.apache.struts.action.ActionForward _fActionForward;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.itsz.sht.common.ChannelsParamInit _fChannelsParamInit;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private Object _fObject;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private Object _fObject2;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private Object _fObject3;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private Object _fObject4;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private Object _fObject5;
	/**
	 * 用来记录桩方法被调用的次数.
	 * @author Parasoft Jtest 9.0
	 */
	private jtest.StubIterationCounter _stubs_iteration;
}
// JTEST_CURRENT_ID=431613881.