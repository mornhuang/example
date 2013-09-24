

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
public class News
{
	//主键
	private Long id;
	//消息标题
	private String title;
	//消息内容
	private String content;
	//消息的发布时间
	private Date postDate;
	//消息的最后修改时间
	private Date lastModifyDate;
	//消息所属分类
	private Category category;
	//消息发布者
	private User poster;
	//消息对应的回复
	private Set newsReviews;
	//下面省略各属性的setter和getter方法
	...
	//重写Domain Object的equals方法
	public boolean equals(Object object) 
	{
		if(this == object)
		{
			return true;
		}
		if (object != null && 
			object.getClass() == News.class)
		{
			News rhs = (News) object;
			return this.poster.equals(rhs.getPoster())
				&& this.postDate.equals(rhs.getPostDate());
		}
		return false;
	}
	//重写News类的hashCode方法
	public int hashCode()
	{
		return this.poster.hashCode() +
			this.postDate.hashCode() * 29;
	}
	//重写News类的toString方法
	public String toString()
	{
		return new ToStringBuilder(this).append("id", this.id)
			.append("title" , this.title)
			.append("postDate" , this.postDate)
			.append("content" , this.content)
			.append("lastModifyDate" , this.lastModifyDate)
			.append("poster" , this.poster)
			.append("category" , this.category)
			.append("newsReviews" , this.newsReviews)
			.toString();
	}
}
