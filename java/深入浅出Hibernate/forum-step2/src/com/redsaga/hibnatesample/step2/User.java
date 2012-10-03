package com.redsaga.hibnatesample.step2;

import com.redsaga.hibnatesample.step2.base.BaseUser;

/**
 * This is the object class that relates to the user table.
 * Any customizations belong here.
 */
public class User extends BaseUser {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public User () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public User (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public User (
		java.lang.Integer _id,
		java.lang.String _name,
		java.lang.String _pwd) {

		super (
			_id,
			_name,
			_pwd);
	}

/*[CONSTRUCTOR MARKER END]*/
}