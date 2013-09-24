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
		//最简单的字符串表达式
		Expression exp = parser.parseExpression("'HelloWorld'");
		System.out.println("'HelloWorld'的结果： " + exp.getValue());
		//调用方法的表达式
		exp = parser.parseExpression("'HelloWorld'.concat('!')");
		System.out.println("'HelloWorld'.concat('!')的结果： "
			+ exp.getValue());
		//调用对象的getter方法
		exp = parser.parseExpression("'HelloWorld'.bytes");
		System.out.println("'HelloWorld'.bytes的结果： "
			+ exp.getValue());
		//访问对象的属性(d相当于HelloWorld.getBytes().length)
		exp = parser.parseExpression("'HelloWorld'.bytes.length");
		System.out.println("'HelloWorld'.bytes.length的结果："
			+ exp.getValue());
		//使用构造器来创建对象
		exp = parser.parseExpression("new String('helloworld')"
			+ ".toUpperCase()");
		System.out.println("new String('helloworld')"
			+ ".toUpperCase()的结果是： "
			+ exp.getValue(String.class));

		Person person = new Person(1 , "孙悟空", new Date());
		exp = parser.parseExpression("name");
		//以指定对象作为root来计算表达式的值
		//相当于调用person.name表达式的值
		System.out.println("以persn为root，name表达式的值是： "
			+ exp.getValue(person , String.class));		
		exp = parser.parseExpression("name=='孙悟空'");
		StandardEvaluationContext ctx = new StandardEvaluationContext();
		ctx.setRootObject(person);
		//以指定Context来计算表达式的值
		System.out.println(exp.getValue(ctx , Boolean.class));

		List<Boolean> list = new ArrayList<Boolean>();
		list.add(true);
		EvaluationContext ctx2 = new StandardEvaluationContext();
		//将list设置成EvaluationContext的一个变量
		ctx2.setVariable("list" , list);
		//修饰list变量的第一个元素的值
		parser.parseExpression("#list[0]").setValue(ctx2 , "false");
		//list集合的第一个元素被改变
		System.out.println("list集合的第一个元素为： "
			+ list.get(0));
	}
}