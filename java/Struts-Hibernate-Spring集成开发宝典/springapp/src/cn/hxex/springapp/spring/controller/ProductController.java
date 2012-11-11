package cn.hxex.springapp.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cn.hxex.springapp.bus.ProductManager;

public class ProductController implements Controller {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private ProductManager prodMan;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
        String now = (new java.util.Date()).toString(); 
        logger.info("returning hello view with " + now);

        Map myModel = new HashMap();
        myModel.put("now", now);
        myModel.put("products", getProductManager().getProducts());

        return new ModelAndView("product", "model", myModel);
	}

    public void setProductManager(ProductManager pm) {
        prodMan = pm;
    }

    public ProductManager getProductManager() {
        return prodMan;
    }

}
