package cn.itsz.newsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.itsz.newsim.dao.hibernate.model.ParameterModel;
import cn.itsz.newsim.dto.request.ParameterRequest;
import cn.itsz.newsim.exception.ITSZException;

@Component(value = "parameterDao")
public class ParameterDaoImpl implements ParameterDao {

	private String sql_query="SELECT * FROM parameter where pname=? ";
	private String sql_insert="insert into parameter(pname,pvalue,pdesc) values(?,?,?)";
	private String sql_update="update parameter set pvalue=?,pdesc=? where pname=? ";
	private String sql_delete="delete from parameter where pname=? ";
	private String sql_query_count="SELECT count(*) FROM parameter where 1=1 ";
	private String sqlquerypage="SELECT * FROM parameter order by pname OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	   
	@Autowired private DataSource dataSource;

	@Override
	public ParameterModel findParameter(String pname) {
		PreparedStatement ps = null;
		ResultSet rl = null;
		ParameterModel parameterModel = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql_query);
			parameterModel = new ParameterModel();
			ps.setString(1, pname);
			rl = ps.executeQuery();
			if (rl.next()) {
				parameterModel.setPname(rl.getString("pname"));
				parameterModel.setPvalue(rl.getString("pvalue"));
				parameterModel.setPdesc(rl.getString("pdesc"));
			}
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rl != null) {
					rl.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return parameterModel;
	}

	@Override
	public boolean saveParameter(ParameterModel param) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql_insert);
			ps.setString(1, param.getPname());
			ps.setString(2, param.getPvalue());
			ps.setString(3, param.getPdesc());
			int rl = ps.executeUpdate();
			conn.commit();
			if (rl > 0) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			e.printStackTrace();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public int getCount(ParameterRequest paramRequest) {
		Statement ps = null;
		ResultSet rl = null;
		int count = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.createStatement();
			rl = ps.executeQuery(sql_query_count);
			if (rl.next()) {
				count = rl.getInt(1);
			}
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rl != null) {
					rl.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;

	}

	@Override
	public boolean updateParameter(ParameterModel param) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql_update);
			ps.setString(1, param.getPvalue());
			ps.setString(2, param.getPdesc());
			ps.setString(3, param.getPname());
			int rl = ps.executeUpdate();
			conn.commit();
			if (rl > 0) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			e.printStackTrace();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public boolean deleteParameter(ParameterModel param) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql_delete);
			ps.setString(1, param.getPname());
			int rl = ps.executeUpdate();
			conn.commit();
			if (rl > 0) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			e.printStackTrace();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<ParameterModel> findParameterList(ParameterRequest paramRequest) {
		List<ParameterModel> list = new ArrayList<ParameterModel>();
		PreparedStatement ps = null;
		ResultSet rl = null;
		Connection conn = null;

		int pageNo = paramRequest.getPageNo();
		int pageSize = paramRequest.getPageSize();
		int start = 0;
		if (pageNo == 0) {
			pageNo = 1;
			start = 0;
		} else {
			start = pageSize * (pageNo - 1);
		}

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sqlquerypage);
			ps.setInt(1, start);
			ps.setInt(2, pageSize);
			rl = ps.executeQuery();
			while (rl.next()) {
				ParameterModel parameterModel = new ParameterModel();
				parameterModel.setPname(rl.getString("pname"));
				parameterModel.setPvalue(rl.getString("pvalue"));
				parameterModel.setPdesc(rl.getString("pdesc"));
				list.add(parameterModel);
			}
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rl != null) {
					rl.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}

	@Override
	public boolean isParameterExist(ParameterModel parameterModel)
			throws ITSZException {
		PreparedStatement ps = null;
		ResultSet rl = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql_query);
			ps.setString(1, parameterModel.getPname());
			rl = ps.executeQuery();
			if (rl.next()) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rl != null) {
					rl.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
