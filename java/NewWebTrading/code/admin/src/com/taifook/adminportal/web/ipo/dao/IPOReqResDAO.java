package com.taifook.adminportal.web.ipo.dao;

import java.util.ArrayList;

import com.taifook.adminportal.web.ipo.dto.IPOReqRes;
import com.taifook.adminportal.exceptions.DAOException;

public interface IPOReqResDAO {

    public ArrayList getIPOReqRes(long ipoMasterId) throws DAOException;

    public void exportFileByIPO(long ipoMasterId, String filePath,
                                String ipoName) throws
        DAOException;

    public ArrayList getIPORequestOrder(String aeCode, long ipoMasterId,ArrayList acList) throws
        DAOException;

    public ArrayList getIPORequestOrder(String aeCode, String acCode,
                                        long ipoMasterId,ArrayList acList) throws
        DAOException;

    public ArrayList getIPORequestOrder(String aeCode, String acCode,ArrayList acList) throws
        DAOException;

}
