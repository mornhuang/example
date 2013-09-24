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
		//创建一个List集合
		Expression exp = parser.parseExpression(
			"{'java' , 'Struts' , 'Spring'}");
		System.out.println(exp.getValue());
		//创建“二维”List集合
		exp = parser.parseExpression(
			"{{'疯狂Java讲义' , ''}, {'左传' , '战国策'}}");
		System.out.println(exp.getValue());
	}
}