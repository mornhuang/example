package org.crazyit.auction.eao;

import java.util.List;
import javax.ejb.*;

import org.crazyit.auction.model.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Local
public interface StateEao
	extends Eao
{
	/**
	 * 查询全部状态
	 * @return 获得全部状态
	 */ 
	List<State> findAll();
}
