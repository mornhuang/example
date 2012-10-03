/**
 * <p>Title:Channels Server for STT2.5</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common.model.common;

import com.itsz.sht.common.Constants;
import com.taifook.mcs.core.beans.msg.MCSMessage;


public class AFXNewsInfo extends MCSMessage {
    private String afxNews;
    private String afxNewsByStock;
    public AFXNewsInfo() {
        super.setMessageId(Constants.MsgID_AFXNews);
    }
    /**
     * @return Returns the afxNews.
     */
    public String getAfxNews() {
        return afxNews;
    }
    /**
     * @param afxNews The afxNews to set.
     */
    public void setAfxNews(String afxNews) {
        this.afxNews = afxNews;
    }
    /**
     * @return Returns the afxNewsByStock.
     */
    public String getAfxNewsByStock() {
        return afxNewsByStock;
    }
    /**
     * @param afxNewsByStock The afxNewsByStock to set.
     */
    public void setAfxNewsByStock(String afxNewsByStock) {
        this.afxNewsByStock = afxNewsByStock;
    }
}
