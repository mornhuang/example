package com.redsaga.hibernatesample.step4;

import com.redsaga.hibernatesample.step4.base.BaseVoteOption;

/**
 * This is the object class that relates to the vote_option table.
 * Any customizations belong here.
 */
public class VoteOption extends BaseVoteOption {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public VoteOption () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public VoteOption (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public VoteOption (
		java.lang.Integer _id,
		com.redsaga.hibernatesample.step4.Vote _poll) {

		super (
			_id,
			_poll);
	}

/*[CONSTRUCTOR MARKER END]*/
}