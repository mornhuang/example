

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class ImageProxy implements Image
{
	//组合一个image实例，作为被代理的对象
	private Image image;
	//使用抽象实体来初始化代理对象
	public ImageProxy(Image image) 
	{
		this.image = image;
	}
	/**
	 * 重写Image接口的show()方法
	 * 该方法用于控制对被代理对象的访问，
	 * 并根据需要负责创建和删除被代理对象
	 */
	public void show()
	{
		//只有当真正需要调用image的show方法时才创建被代理对象
		if (image == null) 
		{
			image = new BigImage();
		}
		image.show();
	}
}