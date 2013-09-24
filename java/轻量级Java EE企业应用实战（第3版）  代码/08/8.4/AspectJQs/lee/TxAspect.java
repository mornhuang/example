// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TxAspect.javapackage lee;import java.io.PrintStream;
import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;public class TxAspect
{	private static Throwable ajc$initFailureCause; /* synthetic field */
	public static final TxAspect ajc$perSingletonInstance; /* synthetic field */	public TxAspect()
	{
	}	public void ajc$around$lee_TxAspect$1$f54fe983(AroundClosure ajc_aroundClosure)
	{
		System.out.println("开始事务...");
		ajc$around$lee_TxAspect$1$f54fe983proceed(ajc_aroundClosure);
		System.out.println("事务结束...");
	}	static void ajc$around$lee_TxAspect$1$f54fe983proceed(TxAspect this)
		throws Throwable
	{
		Conversions.voidValue(this.run(new Object[0]));
	}	public static TxAspect aspectOf()
	{
		if (ajc$perSingletonInstance == null)
			throw new NoAspectBoundException("lee_TxAspect", ajc$initFailureCause);
		else
			return ajc$perSingletonInstance;
	}	public static boolean hasAspect()
	{
		return ajc$perSingletonInstance != null;
	}	private static void ajc$postClinit()
	{
		ajc$perSingletonInstance = new TxAspect();
	}	static 
	{
		try
		{
			ajc$postClinit();
		}
		catch (Throwable throwable)
		{
			ajc$initFailureCause = throwable;
		}
	}
}
