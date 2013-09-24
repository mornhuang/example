package lee;

import org.springframework.expression.*;
import org.springframework.expression.spel.standard.*;
import org.springframework.expression.spel.support.*;

import java.util.*;
import org.crazyit.app.domain.*;
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
		//得到的新集合的元素是原集合的每个元素length()方法返回值
		Expression expr = parser.parseExpression
			("#mylist.![length()]");
		System.out.println(expr.getValue(ctx));

		List<Person> list2 = new ArrayList<Person>();
		list2.add(new Person(1, "孙悟空" , 162));
		list2.add(new Person(1, "猪八戒" , 182));
		list2.add(new Person(1, "牛魔王" , 195));
		ctx.setVariable("mylist2" , list2);
		//得到的新集合的元素是原集合的每个元素name属性值
		expr = parser.parseExpression
			("#mylist2.![name]");
		System.out.println(expr.getValue(ctx));
	}
}