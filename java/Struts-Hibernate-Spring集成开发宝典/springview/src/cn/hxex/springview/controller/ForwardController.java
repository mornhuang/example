package cn.hxex.springview.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ForwardController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map map = new HashMap();
	    List wordList = new ArrayList();
	        
	    wordList.add("hello");
	    wordList.add("world");
	       
	    map.put("wordList", wordList);

		String viewName = request.getParameter( "view" );
		return new ModelAndView( viewName, map );
	}

}
