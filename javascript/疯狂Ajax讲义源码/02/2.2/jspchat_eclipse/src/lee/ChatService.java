package lee;

import java.util.*;
import java.io.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class ChatService 
{
	//使用单例模式来设计ChatService
	private static ChatService cs;
	//使用Properties对象保存系统的所有用户
	private Properties userList;
	//使用LinkedList对象保存聊天信息
	private LinkedList<String> chatMsg;
	//构造器私有
	private ChatService()
	{
	}
	//通过静态方法返回唯一的ChatService对象
	public static ChatService instance()
	{
		if (cs == null)
		{
			cs = new ChatService();
		}
		return cs;
	}
	//验证用户的登录
	public boolean validLogin(String user , String pass) 
		throws IOException
	{
		//根据用户名获取密码
		String loadPass = loadUser().getProperty(user);
		//登录成功
		if (loadPass != null
			&& loadPass.equals(pass))
		{
			return true;
		}
		return false;
	}
	//新注册用户
	public boolean addUser(String name , String pass)
		throws Exception
	{
		//当userList为null，初始化userList对象
		if (userList == null)
		{
			userList = loadUser();
		}
		//如果userList已经所需注册的用户
		if (userList.containsKey(name))
		{
			throw new Exception("用户名已经存在，请重新选择用户名");
		}
		userList.setProperty(name , pass);
		saveUserList();
		return true;
	}
	//获取系统中所有聊天信息
	public String getMsg()
	{
		//如果chatMsg对象为null，表明不曾开始聊天
		if (chatMsg == null)
		{
			chatMsg = new LinkedList<String>();
			return "";
		}
		StringBuilder result = new StringBuilder("");
		//将chatMsg中所有聊天信息拼接起来。
		for (String tmp : chatMsg)
		{
			result.append(tmp + "\n");
		}
		return result.toString();
	}
	//用户发言，添加聊天信息
	public void addMsg(String user , String msg)
	{
		///如果chatMsg对象为null，初始化chatMsg对象
		if (chatMsg == null)
		{
			chatMsg = new LinkedList<String>();
		}
		//最多保存40条聊天信息，当超过40条之后，将前面聊天信息删除
		if (chatMsg.size() > 40)
		{
			chatMsg.removeFirst();
		}
		//添加新的聊天信息
		chatMsg.add(user + "说：" + msg);
	}
	//------------下面是系统的工具方法--------------
	//读取系统用户信息
	private Properties loadUser()throws IOException
	{
		if (userList == null)
		{
			//加载userFile.properties文件
			File f = new File("userFile.properties");
			//如果文件不存在，新建该文件
			if (!f.exists())
			{
				f.createNewFile();
			}
			//新建Properties文件
			userList = new Properties();
			//读取userFile.properties文件里的用户信息
			userList.load(new FileInputStream(f)); 
		}
		return userList;
	}
	//保存系统所有用户
	private boolean saveUserList()throws IOException
	{
		if (userList == null)
		{
			return false;
		}
		//将userList信息保存到Properties文件中
		userList.store(new FileOutputStream("userFile.properties"),
			"Users Info List");
		return true;
	}
}
