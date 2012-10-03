package com.itsz.sht.service.mcs;

import com.itsz.sht.common.model.common.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.model.common.response.VerifyPasswordResponseModel;
import com.itsz.sht.exception.ITSZException;

public interface McsService {

	public VerifyPasswordResponseModel callVerifyPassword(VerifyPasswordRequestModel requestModel) throws ITSZException;

}
