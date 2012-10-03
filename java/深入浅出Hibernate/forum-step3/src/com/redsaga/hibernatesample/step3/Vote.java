package com.redsaga.hibernatesample.step3;

import com.redsaga.hibernatesample.step3.base.BaseVote;

/**
 * This is the object class that relates to the vote table.
 * Any customizations belong here.
 */
public class Vote extends BaseVote {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Vote () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Vote (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public Vote (
		java.lang.Integer _id,
		com.redsaga.hibernatesample.step3.User _createBy,
		com.redsaga.hibernatesample.step3.Article _parent,
		com.redsaga.hibernatesample.step3.Board _board,
		com.redsaga.hibernatesample.step3.User _lastUpdateBy,
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
}