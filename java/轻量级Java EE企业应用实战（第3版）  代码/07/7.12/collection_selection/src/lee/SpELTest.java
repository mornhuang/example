package lee;

import org.springframework.expression.*;
import org.springframework.expression.spel.standard.*;
import org.springframework.expression.spel.support.*;

import java.util.*;
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
public class SpELTest
{
	public static void main(String[] args)
	{
		//创建一个ExpressionParser对象，用于解析表达式
		ExpressionParser parser = new SpelExpressionParser();

		List<String> list = new ArrayList<String>();
		list.add("疯狂Java讲义");
		list.add("疯狂Ajax讲义");
		list.add("疯狂XML讲义");
		list.add("经典Java EE企业应用实战");
		EvaluationContext ctx = new StandardEvaluationContext();
		ctx.setVariable("mylist" , list);
		//判断集合元素length()方法的长度大于7，“疯狂XML讲义”被剔除
		Expression expr = parser.parseExpression
			("#mylist.?[length()>7]");
		System.out.println(expr.getValue(ctx));

		Map<String, Double> map = new HashMap<String ,Double>();
		map.put("Java" , 89.0);
		map.put("Spring" , 82.0);
		map.put("英语" , 75.0);
		ctx.setVariable("mymap" , map);
		//判断Map集合的value值大于80，只保留前面2个Entry
		expr = parser.parseExpression
			("#mymap.?[value>80]");
		System.out.println(expr.getValue(ctx));
	}
}