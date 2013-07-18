package mobile.android.ch12.aidl;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service
{ 
	
	public class MyServiceImpl extends IMyService.Stub
	{

		@Override
		public String getValue() throws RemoteException
		{
			// TODO Auto-generated method stub
			return "《Android/OPhone开发完全讲义》";
		}
		
	}

	@Override
	public IBinder onBind(Intent intent)
	{		
		return new MyServiceImpl();
	}

	
}
