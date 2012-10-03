package com.redsaga.hibnatesample.step1.synchronizer;

import com.redsaga.hibnatesample.step1.synchronizer.base.BaseArticle;

/**
 * This is the object class that relates to the article table.
 * Any customizations belong here.
 */
public class Article extends BaseArticle {

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
		com.redsaga.hibnatesample.step1.synchronizer.User _createBy,
		com.redsaga.hibnatesample.step1.synchronizer.Article _parent,
		com.redsaga.hibnatesample.step1.synchronizer.Board _board,
		com.redsaga.hibnatesample.step1.synchronizer.User _lastUpdateBy,
		java.util.Date _lastUpdateTime,
		java.util.Date _createTime,
		java.lang.String _title,
		java.lang.Integer _hits,
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
}