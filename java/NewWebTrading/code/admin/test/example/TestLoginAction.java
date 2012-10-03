package example;

/**
 * 
 */


import java.util.List;

import servletunit.struts.MockStrutsTestCase;

/**
 * @author swliu
 *
 */
public class TestLoginAction extends MockStrutsTestCase {

    public TestLoginAction(String testName) {
        super(testName);
    }

    public void setUp() throws Exception {
        super.setUp();
        setInitParameter("validating","false");
    }

    public void testExample1_success() {//正确用户名,正确密码
    	System.out.println("++++++++++++++++++++<<<LoginAction example 1(正确用户名,正确密码)>>>+++++++++++++++++++++");
    	this.clearRequestParameters();
    	this.addRequestParameter("userid","swliu");
    	this.addRequestParameter("password","000000");
    	this.setRequestPathInfo("/login.do");
    	this.actionPerform();
    	this.verifyForward("success");              
    	this.assertNotNull(this.getSession().getAttribute("session_user"));
    	this.assertNull(this.getRequest().getAttribute("GlobalError"));
    	List acList=(List)this.getSession().getAttribute("acList");
    	
    	System.out.println("acList'SIZE: "+acList.size());
    	for(int i=0;i<acList.size();i++){
    		System.out.println("acList: "+acList.get(i));
    	}
    }

  public void testExample2_fail() {//输入正确用户名,错误密码
	    System.out.println("++++++++++++++++++++<<<LoginAction example 2(正确用户名,错误密码)>>>+++++++++++++++++++++");
	    this.clearRequestParameters();
    	this.addRequestParameter("userid","swliu");
    	this.addRequestParameter("password","1234567890");
    	this.setRequestPathInfo("/login.do");
    	this.actionPerform();
    	this.verifyForward("fail");       
    	this.assertNotNull(this.getRequest().getAttribute("GlobalError"));
    }
  
  public void testExample3_fail() {//输入错误用户名,正确密码
	System.out.println("++++++++++++++++++++<<<LoginAction example 3(错误用户名,正确密码)>>>+++++++++++++++++++++");
	this.clearRequestParameters();
  	this.addRequestParameter("userid","abcd123");
  	this.addRequestParameter("password","000000");
  	this.setRequestPathInfo("/login.do");
  	this.actionPerform();
  	this.verifyForward("fail");       
  	this.assertNotNull(this.getRequest().getAttribute("GlobalError"));
  }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestLoginAction.class);
    }


}
