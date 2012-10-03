package cn.itsz.newsim.dao;

import java.util.List;

import cn.itsz.newsim.dao.hibernate.model.ParameterModel;
import cn.itsz.newsim.dto.request.ParameterRequest;


public interface ParameterDao {
	public int getCount(ParameterRequest paramRequest);
	public boolean saveParameter(ParameterModel param);
	public boolean updateParameter(ParameterModel param);
	public boolean deleteParameter(ParameterModel param);
	public ParameterModel findParameter(String pname);
	public List<ParameterModel> findParameterList(ParameterRequest paramRequest);
	public boolean isParameterExist(ParameterModel parameterModel);
}
