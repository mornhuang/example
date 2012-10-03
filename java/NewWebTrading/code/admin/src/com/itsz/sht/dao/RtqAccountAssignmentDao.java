package com.itsz.sht.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.RtqAccountAssignment;
import com.itsz.sht.model.RtqAccountAssDto;
import com.taifook.adminportal.common.util.page.Page;

public interface RtqAccountAssignmentDao {

	public void addRtqAccountAssignment(RtqAccountAssignment rtqAccountAssignment) throws DataAccessException;
	public void updateRtqAccountAssignment(RtqAccountAssignment rtqAccountAssignment) throws DataAccessException;
	public void deleteRtqAccountAssignment(String rtqAccountAssignmentId) throws DataAccessException;
	public RtqAccountAssignment getRtqAccountAssignment(String rtqAccountAssignmentId) throws DataAccessException;
	
    public Page getRtqAccountAssignmentByProductId(String productId,Integer pageNumber, Integer pageSize)throws DataAccessException;
    
    public List<RtqAccountAssignment> getRtqAccountAssignmentByProductId(String productId)throws DataAccessException;
   
    public void deleteRtqAccountAssignment(List<String> clintIds, String productId)throws DataAccessException;
    
    public RtqAccountAssignment getRtqAccountAssignmentByClntIdAndProdId(String clientId, String productId)throws DataAccessException;
    
    public RtqAccountAssignment getRtqAccountAssignmentByProdIdAndRtqLoginId(String productId,String rtqLoginId)throws DataAccessException;
    
    public List<RtqAccountAssignment> getRtqAccountAssignment(String productId,String clientId)throws DataAccessException;
    
    public List<RtqAccountAssignment> getRtqAccountByClientId(String clientId)throws DataAccessException;
   
    public void deleteRtqAccountAssignmentList(List<RtqAccountAssignment> rtqAccountAssignmentList)throws DataAccessException;
    
    public RtqAccountAssignment getRtqAccountAssignment(String productId,String rtqLoginId,String clientId) throws DataAccessException;
   //RtqAccount和RtqAccountAssignment左连接查询 查询出所有的RtqAccout如果被分配了就显示clientId如果没有被分配就显示为“未分配”
    public List<RtqAccountAssDto> listRtqAccountAssByProductId(String productId) throws DataAccessException;
    
    public void updateRtqAccountAssignmentPassword(String productId, List<String> rtqLognIdUpdateList, String password) throws DataAccessException;
}
