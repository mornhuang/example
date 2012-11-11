package pl.test;

import java.lang.reflect.*;

/**
 *
 * @author  ArtemRd
 * @version
 */
public class ReflectionTest
{
    private static final int LOOP_SIZE = 10000000;
    /** Creates new ReflectionTest */
    public ReflectionTest()
    {
    }
    
    public int function()
    {
        return 0;
    }
    
    public static void main(String args[]) throws Throwable
    {
        ReflectionTest rt = new ReflectionTest();
        
        
        long begin = 0;
        long end = 0;
        long empty = 0;
        long java = 0;
        long reflection = 0;

        begin = System.currentTimeMillis();
        for(int i = 0; i < LOOP_SIZE; i++)
        {
        }
        end = System.currentTimeMillis();
        empty = end - begin;
        System.out.println("Empty loop: " + empty);
        
        begin = System.currentTimeMillis();
        for(int i = 0; i < LOOP_SIZE; i++)
        {
            rt.function();
        }
        end = System.currentTimeMillis();
        java = end - begin - empty;
        System.out.println("Java invocation: " + java);
        
        Method method = ReflectionTest.class.getMethod("function", new Class[0]);
        Object[] parameters = new Object[0];
        begin = System.currentTimeMillis();
        for(int i = 0; i < LOOP_SIZE; i++)
        {
            method.invoke(rt, parameters);
        }
        end = System.currentTimeMillis();
        reflection = end - begin - empty;
        System.out.println("Reflection invocation: " + reflection);
        
        System.out.println("Reflection is " + (reflection / java) + " times slower than java");
    }
}
