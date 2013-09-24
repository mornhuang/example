package lee;

import java.util.*;
import java.io.*;

import javax.mail.*;
import javax.mail.internet.*;
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
public class ReceiveMail
{
	private final static String SSL_FACTORY 
		= "javax.net.ssl.SSLSocketFactory";
	//用户帐号
	private String account;
	//密码
	private String password;
	//pop3邮件服务器
	private String pop3Host;
	//pop3的端口
	private int pop3Port;
	//account属性的setter和getter方法
	public void setAccount(String account)
	{
		this.account = account;
	}
	public String getAccount()
	{
		return this.account;
	}
	//password属性的setter和getter方法
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return this.password;
	}
	//pop3Host属性的setter和getter方法
	public void setPop3Host(String pop3Host)
	{
		this.pop3Host = pop3Host;
	}
	public String getPop3Host()
	{
		return this.pop3Host;
	}
	//pop3Port属性的setter和getter方法
	public void setPop3Port(int pop3Port)
	{
		this.pop3Port = pop3Port;
	}
	public int getPop3Port()
	{
		return this.pop3Port;
	}
	private Store store;
	public ReceiveMail()
	{
	}	
	public ReceiveMail(String account , String password
		, String pop3Host , int pop3Port)
	{
		this.account = account;
		this.password = password;
		this.pop3Host = pop3Host;
		this.pop3Port = pop3Port;
	}	
	public Store getStore() 
	{
		if (this.store == null 
			|| !this.store.isConnected()) 
		{
			try 
			{
				Properties props = new Properties();
				if (isGmail()) 
				{
					props.setProperty("mail.pop3.socketFactory.class"
						, SSL_FACTORY);
				}
				//创建mail的Session
				Session session = Session.getDefaultInstance(props);
				//使用pop3协议接收邮件
				URLName url = new URLName("pop3", getPop3Host()
					, getPop3Port(), null ,  getAccount(), getPassword());
				//得到邮箱的存储对象
				Store store = session.getStore(url);
				store.connect();
				this.store = store;
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("连接邮箱异常，请检查连接信息");
			}
		}
		return this.store;
	}
	//对于Gmail邮箱系统需要特别处理	
	private boolean isGmail() 
	{
		if (this.account == null 
			|| this.account.trim().equals("")) 
			return false;
		if (this.account.lastIndexOf("@gmail.com") != -1)
		{
			return true;
		}
		return false;
	}
	public void getMessages()
	{
		//获取收件箱
		try 
		{
			Folder inbox = getStore().getFolder("INBOX");
			inbox.open(Folder.READ_WRITE);
			//得到INBOX里的所有信息
			Message[] messages = inbox.getMessages();
			for (int i = 0 ; i < messages.length ; i++)
			{
				System.out.println("----第" + (i + 1) + "封邮件的信息---");
				printMessage(messages[i]);
			}

		} 
		catch (Exception e) 
		{
			System.out.println("读取邮件信息出错！");
			e.printStackTrace();
		}
	}	
	//得到接收的日期, 优先返回发送日期, 其次返回收信日期
	public Date getReceivedDate(Message m)
		throws Exception 
	{
		if (m.getSentDate() != null) 
			return m.getSentDate();
		if (m.getReceivedDate() != null)
			return m.getReceivedDate();
		return new Date();
	}
	//得到抄送的地址
	public List<String> getCC(Message m) 
		throws Exception 
	{
		Address[] addresses = m.getRecipients(
			Message.RecipientType.CC);
		return getAddresses(addresses);
	}
	//得到文件名的后缀
	private String getFileSuffix(String fileName)
	{
		if (fileName == null || fileName.trim().equals(""))
			return "";
		int dotPos = fileName.lastIndexOf(".");
		if (dotPos != -1)
		{
			return fileName.substring(dotPos);
		}
		return "";
	}
	//获得邮件的附件
	public List<FileObject> getFiles(Message m)
		throws Exception 
	{
		List<FileObject> files = new ArrayList<FileObject>();
		//是混合类型, 就进行处理
		if (m.isMimeType("multipart/mixed"))
		{
			Multipart mp = (Multipart)m.getContent();
			//得到邮件内容的Multipart对象并得到内容中Part的数量
			int count = mp.getCount();
			for (int i = 1; i < count; i++)
			{
				//获取附件
				Part part = mp.getBodyPart(i);
				//获取邮件附件名
				String serverFileName = MimeUtility
					.decodeText(part.getFileName());
				//生成UUID作为在本地系统中唯一的文件标识
				String fileName = UUID.randomUUID().toString();
				File file = new File(fileName 
					+ getFileSuffix(serverFileName));
				//读写文件
				FileOutputStream fos = new FileOutputStream(file);
				InputStream is = part.getInputStream();
				BufferedOutputStream outs = new BufferedOutputStream(fos);
				//使用IO流读取邮件附件
				byte[] b = new byte[1024];
				int hasRead = 0;
				while((hasRead = is.read(b)) > 0)
				{
					outs.write(b , 0 , hasRead);
				}				
				outs.close();
				is.close();
				fos.close();
				//封装对象
				FileObject fileObject = new FileObject(serverFileName, file);
				files.add(fileObject);
			}
		}
		return files;
	}
	//处理邮件正文的工具方法
	private StringBuffer getContent(Part part
		, StringBuffer result) throws Exception
	{
		if (part.isMimeType("multipart/*"))
		{
			Multipart p = (Multipart)part.getContent();
			int count = p.getCount();
			//Multipart的第一部分是text/plain, 
			//第二部分是text/html的格式, 只解析第一部分即可
			if (count > 1) count = 1; 
			for(int i = 0; i < count; i++) 
			{
				BodyPart bp = p.getBodyPart(i);
				//递归调用
				getContent(bp, result);
			}
		} 
		else if (part.isMimeType("text/*"))
		{
			//遇到文本格式或者html格式, 直接得到内容
			result.append(part.getContent());
		}
		return result;
	}
	//返回邮件正文内容
	public String getContent(Message m) 
		throws Exception
	{
		StringBuffer sb = new StringBuffer("");
		return getContent(m , sb).toString();
	}
	//判断一封邮件是否已读, true表示已读取, false表示没有读取
	public boolean hasRead(Message m) 
		throws Exception
	{
		Flags flags = m.getFlags();
		return flags.contains(Flags.Flag.SEEN);
	}
	//得到一封邮件的所有收件人
	public List<String> getAllRecipients(Message m)
		throws Exception 
	{
		Address[] addresses = m.getAllRecipients();
		return getAddresses(addresses);
	}
	//工具方法, 将参数的地址字符串封装成集合
	public List<String> getAddresses(Address[] addresses)
	{
		List<String> result = new ArrayList<String>();
		if (addresses == null) return result;
		//遍历Address[]数组，将每个元素转换为字符串后收集起来
		for (Address a : addresses)
		{
			result.add(a.toString());
		}
		return result;
	}
	//得到发送人的地址
	public String getSender(Message m) 
		throws Exception 
	{
		Address[] addresses = m.getFrom();
		return MimeUtility.decodeText(addresses[0].toString());
	}
	//打印Message的信息
	public void printMessage(Message m)
		throws Exception
	{
		System.out.println(m.toString());
		System.out.println("接收日期：" + getReceivedDate(m));
		System.out.println("抄送地址：" + getReceivedDate(m));
		System.out.println("是否已读：" + hasRead(m));
		System.out.println("所有收件人：" + getAllRecipients(m));
		System.out.println("发件人地址：" + getSender(m));
		System.out.println("==邮件正文==\n" + getContent(m));
		System.out.println("附件信息" + getFiles(m));

	}
	public static void main(String[] args)
		throws Exception
	{
		ReceiveMail receiveMail = new ReceiveMail();
		receiveMail.setPop3Host("pop3.sina.com");
		receiveMail.setPop3Port(110);
		receiveMail.setAccount("spring_test");
		receiveMail.setPassword("123abc");
		receiveMail.getMessages();
	}
}