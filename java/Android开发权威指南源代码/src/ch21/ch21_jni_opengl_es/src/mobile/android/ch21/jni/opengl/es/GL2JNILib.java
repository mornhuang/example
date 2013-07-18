package mobile.android.ch21.jni.opengl.es;

public class GL2JNILib
{

	static
	{
		System.loadLibrary("gl2jni");
	}

	public static native void init(int width, int height);

	public static native void step();
}
