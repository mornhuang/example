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
		EvaluationContext ctx = new StandardEvaluationContext();
		//调用Stringd对象的substring方法
		System.out.println(parser
			.parseExpression("'HelloWorld'.substring(2, 5)")
			.getValue());
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("struts");
		list.add("spring");
		list.add("hibernate");		
		//设置一个变量
		ctx.setVariable("mylist" , list);
		System.out.println(parser
			.parseExpression("#mylist.subList(1, 3)").getValue(ctx));
	}
}