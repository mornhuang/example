package cn.hxex.blog.auth.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.hxex.blog.auth.bean.UserInfo;
import cn.hxex.blog.model.User;

public class AuthorityUtil {

	public static final String SESSION_USERINFO = "authority.userinfo";
	
	public static void saveUser( HttpServletRequest request, User user ) {
		
		if( user==null ) return;
		
		UserInfo ui = getUser( request );
		
		if( ui==null ) ui = new UserInfo();
		
		ui.setUserId( user.getId() );
		saveUser( request, ui );
	}
	
	public static void saveUser( HttpServletRequest request, UserInfo user ) {
		HttpSession session = request.getSession();
		session.setAttribute( SESSION_USERINFO, user );
	}
	
	public static UserInfo getUser( HttpServletRequest request ) {
		HttpSession session = request.getSession();
		return (UserInfo)session.getAttribute( SESSION_USERINFO );
	}
}
