package com.redsaga.hibnatesample.step2;

import net.sf.hibernate.HibernateException;

import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashSet;

import com.redsaga.hibnatesample.step2.base.BaseArticle;

/**
 * This is the object class that relates to the article table.
 * Any customizations belong here.
 */
public class Article extends BaseArticle {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Article.class);

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Article () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Article (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public Article (
		java.lang.Integer _id,
		com.redsaga.hibnatesample.step2.User _createBy,
		com.redsaga.hibnatesample.step2.Article _parent,
		com.redsaga.hibnatesample.step2.Board _board,
		com.redsaga.hibnatesample.step2.User _lastUpdateBy,
		java.util.Date _lastUpdateTime,
		java.util.Date _createTime,
		java.lang.String _title,
		int _hits,
		java.lang.Integer _articleType) {

		super (
			_id,
			_createBy,
			_parent,
			_board,
			_lastUpdateBy,
			_lastUpdateTime,
			_createTime,
			_title,
			_hits,
			_articleType);
	}
/*[CONSTRUCTOR MARKER END]*/
	protected void initialize () {
		setCreateTime(new Date());
		setArticleType(new Integer(0));
	
	}
	public void calculateTreeIndex()
	{
		if (getParent()==null)
		{
			setNodeLevel(1);
			setTreeIndex("0001");
			logger.info("article"+getTitle()+"'s node level has been set to "+getNodeLevel());
			logger.info("article"+getTitle()+"'s treeIndex has been set to "+getTreeIndex());
		}
		else
		{
			Article parent = getParent();
			setNodeLevel(parent.getNodeLevel()+1);
			logger.info("article"+getTitle()+"'s node level has been set to "+getNodeLevel());
			
			int count = parent.getChildPosts() ==null? 0
						:parent.getChildPosts().size();
			String suffix = "0000".substring(String.valueOf(count).length());
			setTreeIndex(parent.getTreeIndex()+"."+ suffix + count);
			logger.info("article"+getTitle()+"'s treeIndex has been set to "+getTreeIndex());
			
		}
	}
	

}