

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

public class PorkyNoodle extends AbstractNoodle
{
	public PorkyNoodle(Peppery style)
	{
		super(style);
	}
	//实现eat()抽象方法
	public void eat()
	{
		System.out.println("这是一碗稍嫌油腻的猪肉面条。"
			+ super.style.style());
	}
}