import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class NameObserver implements Observer
{
	//实现观察者必须实现的update方法
	public void update(Observable o , Object arg)
	{
		if (arg instanceof String )
		{
			//产品名称改变值在name中
			String name = (String)arg;
			//启动一个JFrame窗口来显示被观察对象的状态改变
			JFrame f = new JFrame("观察者");
			JLabel l = new JLabel("名称改变为：" + name);
			f.add(l);
			f.pack();
			f.setVisible(true);
			System.out.println("名称观察者:" +
				o + "物品名称已经改变为: " + name);
		}
	}
}