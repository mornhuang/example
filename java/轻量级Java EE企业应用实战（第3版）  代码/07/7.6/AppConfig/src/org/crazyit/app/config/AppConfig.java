package org.crazyit.app.config;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.*;

import org.crazyit.app.service.*;
import org.crazyit.app.service.impl.*;
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
@Configuration
public class AppConfig 
{
	//定义需要依赖注入的属性值
	@Value("孙悟空") String personName; 
	//配置一个Bean：chinese
	@Bean(name="chinese") 
	public Person person() 
	{ 
		Chinese p = new Chinese();
		p.setAxe(stoneAxe());
		p.setName(personName);
		return p;
	}
	//配置Bean：stoneAxe
	@Bean(name="stoneAxe")
	public Axe stoneAxe()
	{ 
		return new StoneAxe();
	}
	//配置Bean：steelAxe
	@Bean(name="steelAxe")
	public Axe steelAxe()
	{ 
		return new SteelAxe();
	}
} 

