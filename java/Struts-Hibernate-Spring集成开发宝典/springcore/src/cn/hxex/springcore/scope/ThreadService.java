package cn.hxex.springcore.scope;

/**
 * 多线程的业务对象
 */
public class ThreadService extends Thread {

	public void run() {

		for( int i=0; i<5; i++ ) {
			
			// 获得Bean的实例
			ThreadBean bean = (ThreadBean)ThreadMain.factory.getBean( "threadBean" );
			// 修改Bean的状态
			bean.setStatus( bean.getStatus() + 10 );
			// 显示Bean的信息
			bean.display();
			
			// 进行随机时间的停留，以使得不同进程每次处理的时间不同
			long interval = (long)( Math.random() * 100 );
			try {
				sleep( interval );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
