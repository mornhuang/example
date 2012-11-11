package cn.hxex.springapp.spring.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import cn.hxex.springapp.bus.Product;
import cn.hxex.springapp.bus.ProductManager;

public class ProductAddFormController extends SimpleFormController {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private ProductManager prodMan;

	public ModelAndView onSubmit(Object command) throws ServletException {

		Product p = (Product)command;

		logger.info("Add Product");
		prodMan.addProduct( p );
		logger.info("returning from PriceIncreaseForm view to "
				+ getSuccessView());

		return new ModelAndView(new RedirectView(getSuccessView()));
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException {
		Product product = new Product();
		product.setPrice( new Double( 0.0 ) );
		return product;
	}

	public void setProductManager(ProductManager pm) {
		prodMan = pm;
	}

	public ProductManager getProductManager() {
		return prodMan;
	}
}
