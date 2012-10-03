/*
 * ProjectTestCase.java
 * 11-3-28 17:43:24创建的 Jtest。
 */

package jtest;

import org.junit.Assert;

/**
 * ProjectTestCase 是一个在
 *  'trade.jtest'项目中的抽象单元测试类。
 * @author Parasoft Jtest 9.0
 */
public abstract class ProjectTestCase extends Assert {

	/**
	 * 为被命名参数指定的测试构造一个测试用例。
	 * @param name 测试用例的名称
	 * @author Parasoft Jtest 9.0
	 */
	public ProjectTestCase() {

		/*
		 * 这个构造器不应该被修改。  任何初始化代码 
		 * 应该 被放置在 setUp() 方法中。
		 */
	}

	/**
	 * 用来创建测试用例。此方法在每个
	 * 测试用例执行前会被 JUnit 调用。
	 * @see junit.framework.TestCase#setUp()
	 * @author Parasoft Jtest 9.0
	 */
	public void setUp() throws Exception {
		projectSetUp(this);
	}

	/**
	 * 用于对此项目创建任意测试。 当每个 
	 * JUnit 或其他类型测试用例被执行前此方法会被调用。
	 * @param 测试一个实例对象表述了当前的测试
	 * @author Parasoft Jtest 9.0
	 */
	public static void projectSetUp(Object test) throws Exception {
		/*
		 * 在这添加必要的初始化代码 (例如， 打开一个 socket)。
		 * 当测试时调用 Repository.putTemporary() 提供初始化实例对象。
		 */
		// jtest.Repository.putTemporary("name", object);
	}

	/**
	 * 用于测试后的整理。这个方法会在每个测试完成后被 JUnit调用。
	 * @see junit.framework.TestCase#tearDown()
	 * @author Parasoft Jtest 9.0
	 */
	public void tearDown() throws Exception {
		projectTearDown(this);
	}

	/**
	 * 用于整理此项目中任意的测试。 当每个 JUnit 或其他类型测试用例被执行后此方法会被调用。
	 * @param 测试一个实例对象表述了当前的测试
	 * @author Parasoft Jtest 9.0
	 */
	public static void projectTearDown(Object test) throws Exception {
		/*
		 * 在这添加必要的清除代码 (例如, 关闭socket)。
		 */
	}

	/**
	 * 获得类对象的类将被测试。
	 * @return 的类将被测试
	 * @author Parasoft Jtest 9.0
	 */
	public abstract Class getTestedClass();

}
