

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class NewsReview
{
	//消息回复的主键
	private Long id;
	//消息回复的内容
	private String content;
	//消息回复的回复时间
	private Date postDate;
	//回复的最后修改时间
	private Date lastModifyDate;
	//消息回复对应的消息
	private News news;
	//此处省略了各属性的setter和getter方法
	...
	//重写NewsReview的equals方法
	public boolean equals(Object object)
	{
		if(this == object)
		{
			return true;
		}
		if (object != null && 
			object.getClass() == NewsReview.class)
		{
			NewsReview rhs = (NewsReview) object;
			return this.poster.equals(rhs.getPoster()) 
				&& this.postDate.equals(rhs.getPostDate());
		}
		return false;
	}
	//重写NewsReview的hashCode方法
	public int hashCode() 
	{
		return this.poster.hashCode()
			+ this.postDate.hashCode() * 29;
	}
	//重写NewsReview的toString方法
	public String toString()
	{
		return new ToStringBuilder(this)
			.append("id" , this.id)
			.append("postDate" , this.postDate)
			.append("lastModifyDate" , this.lastModifyDate)
			.append("content" , this.content)
			.append("poster" , this.poster)
			.append("news" , this.news).toString();
	}
}
