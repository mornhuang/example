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
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("Spring");

		Map<String, Double> map = 
			new HashMap<String, Double>();
		map.put("Java" , 80.0);
		map.put("Spring" , 89.0);

		//创建一个ExpressionParser对象，用于解析表达式
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext ctx = new StandardEvaluationContext();
		//设置两个变量
		ctx.setVariable("mylist" , list);
		ctx.setVariable("mymap" , map);
		//访问List集合的第二个元素
		System.out.println(parser
			.parseExpression("#mylist[1]").getValue(ctx));
		//访问Map集合的指定元素
		System.out.println(parser
			.parseExpression("#mymap['Java']").getValue(ctx));
	}
}