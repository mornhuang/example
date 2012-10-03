package com.itsz.sht.struts.form.ipo;


import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import com.itsz.sht.common.Constants;
import com.itsz.sht.struts.form.ITSZForm;

/**
 * @struts.form name="IPOCancelForm"
 */

public class IPOCancelForm extends ITSZForm {
	private static final long serialVersionUID = 1L;
	private String accountId;
    private long applyId;
    private String password;
    private String deadLine;
    private Long ipoMasterId;
    private String status;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }

    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();

        if (request.getParameter(Constants.REQUEST_TOKEN_KEY) != null) {

            Locale locale = (Locale) request.getSession().getAttribute(Globals.
                LOCALE_KEY);
            MessageResources mr = (MessageResources) request.getAttribute(
                Globals.MESSAGES_KEY);
            mr.setReturnNull(true);
            /*
                 if (mcsOrderId == null || "".equals(mcsOrderId)) {
                 String key = mr.getMessage(locale, "label.orderRequestStatusQueryForm.orderid");
                 errors.add("orderId", new WebActionError("errors.required", new Object[]{key}));
                         }
                         else if (password == null && "".equals(password)) {
                String key = mr.getMessage(locale, "label.general.password");
                errors.add("password", new WebActionError("errors.required", new Object[]{key}));
                         }
             */
        }

        return errors;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public long getApplyId() {
        return applyId;
    }
    public void setApplyId(long applyId) {
        this.applyId = applyId;
    }
    public String getDeadLine() {
        return deadLine;
    }
    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }
    public Long getIpoMasterId() {
        return ipoMasterId;
    }
    public void setIpoMasterId(Long ipoMasterId) {
        this.ipoMasterId = ipoMasterId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
