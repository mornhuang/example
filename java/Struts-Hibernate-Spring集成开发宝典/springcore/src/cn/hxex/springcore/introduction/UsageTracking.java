package cn.hxex.springcore.introduction;

public class UsageTracking {

	public static UsageTracked mixin;

	public void displayUsage(UsageTracked usageTracked) {
		System.out.println( "displayUsage" );
		usageTracked.display();
	}

}