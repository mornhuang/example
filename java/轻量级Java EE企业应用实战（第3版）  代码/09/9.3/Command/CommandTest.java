

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
public class CommandTest
{
	public static void main(String[] args) 
	{
		ProcessArray pa = new ProcessArray();
		int[] target = {3, -4, 6, 4};
		//第一次处理数组，具体处理行为取决于Command对象
		pa.each(target , new Command()
		{
			//重写process()方法，决定具体的处理行为
			public void process(int[] target)
			{
				for (int tmp : target )
				{
					System.out.println("迭代输出目标数组的元素:" + tmp);
				}
			}
		});
		System.out.println("------------------");
		//第二次处理数组，具体处理行为取决于Command对象
		pa.each(target , new Command()
		{
			//重写process方法，决定具体的处理行为
			public void process(int[] target)
			{
				int sum = 0;
				for (int tmp : target )
				{
					sum += tmp;			
				}
				System.out.println("数组元素的总和是:" + sum);
			}
		});
	}
}
