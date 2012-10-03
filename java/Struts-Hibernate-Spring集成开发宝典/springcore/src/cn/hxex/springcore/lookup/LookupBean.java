package cn.hxex.springcore.lookup;

public abstract class LookupBean {

	private CurrentTime currentTime;
	
	public CurrentTime getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(CurrentTime currentTime) {
		this.currentTime = currentTime;
	}

	public abstract CurrentTime createCurrentTime();
}
