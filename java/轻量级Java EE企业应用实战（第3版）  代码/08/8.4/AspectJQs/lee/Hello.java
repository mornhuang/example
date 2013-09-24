// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Hello.javapackage lee;import java.io.PrintStream;
import org.aspectj.runtime.internal.AroundClosure;// Referenced classes of package lee:
//			LogAspect, TxAspectpublic class Hello
{	public Hello()
	{
	}	public void sayHello()
	{
		try
		{
			System.out.println("Hello AspectJ!");
		}
		catch (Throwable throwable)
		{
			LogAspect.aspectOf().ajc$after$lee_LogAspect$1$9fd5dd97();
			throw throwable;
		}
		LogAspect.aspectOf().ajc$after$lee_LogAspect$1$9fd5dd97();
	}	public static void main(String args[])
	{
		Hello h = new Hello();
		Hello hello = h;
		sayHello_aroundBody1$advice(hello, TxAspect.aspectOf(), null);
	}	private static final void sayHello_aroundBody0(Hello hello)
	{
		hello.sayHello();
	}	private static final void sayHello_aroundBody1$advice(TxAspect this, AroundClosure ajc_aroundClosure, AroundClosure aroundclosure)
	{
		System.out.println("开始事务...");
		AroundClosure aroundclosure1 = aroundclosure;
		sayHello_aroundBody0(this);
		System.out.println("事务结束...");
	}
}
