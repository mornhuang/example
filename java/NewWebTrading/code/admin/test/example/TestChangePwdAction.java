package example;

/**
 * 
 */


import servletunit.struts.MockStrutsTestCase;

/**
 * @author swliu
 *
 */
public class TestChangePwdAction extends MockStrutsTestCase {

	public TestChangePwdAction(String testName) {
		super(testName);
	}

	public void setUp() throws Exception {
		super.setUp();
		this.addRequestParameter("userid", "swliu");
		this.addRequestParameter("password", "000000");
		this.setRequestPathInfo("/login.do");
		this.actionPerform();
		this.verifyForward("success");
		this.assertNotNull(getSession().getAttribute("session_user"));
	}

	public void testExample1_success() {//输入正确用户名,正确旧密码,符合要求的新密码���������
		System.out.println("++++++++++++++++++++<<<ChangePwdAction example 1(正确用户名,正确旧密码,符合要求的新密码)>>>+++++++++++++++++++++");
		this.clearRequestParameters();		
		this.addRequestParameter("userId", "swliu");
		this.addRequestParameter("oldPwd", "000000");
		this.addRequestParameter("newPwd", "000000");
		this.setRequestPathInfo("/changepwd.do");		
		this.actionPerform();
		this.verifyForward("success");
		this.assertNull(this.getRequest().getAttribute("GlobalError"));
	}

	public void testExample2_fail() {//输入正确用户名,错误旧密码,符合要求的新密码
		System.out.println("++++++++++++++++++++<<<ChangePwdAction example 2(正确用户名,错误旧密码,符合要求的新密码)>>>+++++++++++++++++++++");
		this.clearRequestParameters();
		this.addRequestParameter("userId", "swliu");
		this.addRequestParameter("oldPwd", "abcd");
		this.addRequestParameter("newPwd", "000000");
		this.setRequestPathInfo("/changepwd.do");
		this.actionPerform();
		this.verifyForward("fail");
		this.assertNotNull(this.getRequest().getAttribute("GlobalError"));
	}

	public void testExample3_fail() {//输入正确用户名,正确旧密码,不符合要求的新密码
		System.out.println("++++++++++++++++++++<<<ChangePwdAction example 3(正确用户名,正确旧密码,不符合要求的新密码)>>>+++++++++++++++++++++");
		this.clearRequestParameters();
		this.addRequestParameter("userId", "swliu");
		this.addRequestParameter("oldPwd", "000000");
		this.addRequestParameter("newPwd", "123456789012345678901234567890");
		this.setRequestPathInfo("/changepwd.do");
		this.actionPerform();
		this.verifyForward("fail");
		this.assertNotNull(this.getRequest().getAttribute("GlobalError"));
	}

	public void testExample4_fail() {//输入错误用户名,正确旧密码,符合要求的新密码
		System.out.println("++++++++++++++++++++<<<ChangePwdAction example 4(错误用户名,正确旧密码,合要求的新密码)>>>+++++++++++++++++++++");
		this.clearRequestParameters();
		this.addRequestParameter("userId", "abcd123");
		this.addRequestParameter("oldPwd", "000000");
		this.addRequestParameter("newPwd", "000000");
		this.setRequestPathInfo("/changepwd.do");
		this.actionPerform();
		this.verifyForward("fail");
		this.assertNotNull(this.getRequest().getAttribute("GlobalError"));
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestChangePwdAction.class);
	}

}
