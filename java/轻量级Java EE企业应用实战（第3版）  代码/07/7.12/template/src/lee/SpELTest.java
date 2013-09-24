package lee;

import org.springframework.expression.*;
import org.springframework.expression.spel.standard.*;
import org.springframework.expression.spel.support.*;
import org.springframework.expression.common.*;

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
		Person p1 = new Person(1, "孙悟空" , 162);
		Person p2 = new Person(1, "猪八戒" , 182);
		Expression expr = parser
			.parseExpression("我的名字是#{name},身高是#{height}"
			, new TemplateParserContext());
		//将使用p1对象name、height填充上面表达式模板中的#{}
		System.out.println(expr.getValue(p1));
		//将使用p2对象name、height填充上面表达式模板中的#{}
		System.out.println(expr.getValue(p2));
	}
}