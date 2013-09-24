
package lee;
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
public class Functions
{
	//对字符串进行反转
	public static String reverse( String text )
	{
		return new StringBuffer( text ).reverse().toString();
	}
	//统计字符串的个数
	public static int countChar( String text )
	{
		return text.length();
	}
}
