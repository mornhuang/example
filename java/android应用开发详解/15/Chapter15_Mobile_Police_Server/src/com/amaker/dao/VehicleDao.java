package com.amaker.dao;

import com.amaker.entity.VehicleFaultInfo;
/**
 * 
 * @author 郭宏志
 * 保存信息的Dao接口
 */
public interface VehicleDao {
	public boolean save(VehicleFaultInfo v);
}
