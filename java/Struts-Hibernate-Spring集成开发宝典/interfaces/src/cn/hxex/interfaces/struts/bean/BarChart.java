package cn.hxex.interfaces.struts.bean;

public class BarChart {
	
	private String 	item;
	private int 	percent;

	public BarChart( String item, int percent )
	{
		this.item = item;
		this.percent = percent;
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}

}
