/**
 * 
 */
package com.itsz.sht.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.AutoPurchase;

/**
 * @author cyzeng
 *
 */
public interface AutoPurchaseDao {
	public void addAutoPurchase(AutoPurchase autoPurchase) throws DataAccessException;
	public List<AutoPurchase> getAutoPurchaseList(String status, String startTime, String endTime) throws DataAccessException;
}
