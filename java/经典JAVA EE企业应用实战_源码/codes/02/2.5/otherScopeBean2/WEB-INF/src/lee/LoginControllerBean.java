package lee;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class LoginControllerBean
{
	private UserBean user;
	private LoginBackBean backBean;
	//user属性的setter和getter方法
	public void setUser(UserBean user)
	{
		this.user = user;
	}
	public UserBean getUser()
	{
		return this.user;
	}

	//backBean属性的setter和getter方法
	public void setBackBean(LoginBackBean backBean)
	{
		this.backBean = backBean;
	}
	public LoginBackBean getBackBean()
	{
		return this.backBean;
	}
	//该方法被绑定到UI组件（按钮）的action属性
	public String valid()
	{
		if (backBean.getName().equals("crazyit") 
			&& backBean.getPass().equals("leegang"))
		{
			//这里实际上应该从数据库读取该用户的状态信息
			getUser().setName("疯狂Java");
			getUser().setGender("男");
			return "success";
		}
		backBean.setErr("您的用户名和密码不符合");
		return "failure";
	}
}