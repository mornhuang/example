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
public class LoginBean
{
	//下面的三个属性都会直接与JSF标签绑定
	private String name;
	private String pass;
	private String err;
	private UserBean user;
	
	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	//pass属性的setter和getter方法
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getPass()
	{
		return this.pass;
	}

	//err属性的setter和getter方法
	public void setErr(String err)
	{
		this.err = err;
	}
	public String getErr()
	{
		return this.err;
	}
	//user属性的setter和getter方法
	public void setUser(UserBean user)
	{
		this.user = user;
	}
	public UserBean getUser()
	{
		return this.user;
	}

	//该方法被绑定到UI组件（按钮）的action属性
	public String valid()
	{
		if (name.equals("crazyit") && pass.equals("leegang"))
		{
			//这里实际上应该从数据库读取该用户的状态信息
			getUser().setName("疯狂Java");
			getUser().setGender("男");
			return "success";
		}
		setErr("您的用户名和密码不符合");
		return "failure";
	}

}