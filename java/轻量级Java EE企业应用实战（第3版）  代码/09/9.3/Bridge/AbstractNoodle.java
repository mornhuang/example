

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

public abstract class AbstractNoodle
{
	//组合一个Peppery变量，用于将该维度的变化独立出来
	protected Peppery style;
	//每份Noodle必须组合一个Peppery对象
	public AbstractNoodle(Peppery style)
	{
		this.style = style;
	}
	public abstract void eat();
}