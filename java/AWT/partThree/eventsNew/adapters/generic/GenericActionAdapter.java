import java.lang.reflect.*;
import java.awt.*;
import java.awt.event.*;

public class GenericActionAdapter implements ActionListener {
	public Object   listener;
	public String   methodName;
	public Method   method;
	public Object[] args = new Object[1];
	public Class[]  classTypes = { ActionEvent.class };

	public GenericActionAdapter(Object listener, 
								String methodName) {
		this.listener   = listener;
		this.methodName = methodName;
		try {
			method = 
			listener.getClass().getMethod(methodName, classTypes);
		}
		catch(NoSuchMethodException e) {
			System.out.println(
				"method " + methodName + " not found");
		}
		catch(SecurityException e) {
			System.out.println(
					"search for method" + methodName + 
					" resulted in a security exception");
		}
	}
	public void actionPerformed(ActionEvent event) {
		args[0] = event;

		try {
			method.invoke(listener, args);
		}
		catch(NullPointerException e) {
			System.out.println("null object, or null method");
		}
		catch(IllegalAccessException e) {
			System.out.println("method " + methodName + 
			                   " cannot be legally accessed");
		}
		catch(IllegalArgumentException e) {
			System.out.println("bad arguments for method " + 
			                    methodName);
		}
		catch(InvocationTargetException e) {
			System.out.println("exception thrown from method" +
			                    methodName);
		}
	}
}
