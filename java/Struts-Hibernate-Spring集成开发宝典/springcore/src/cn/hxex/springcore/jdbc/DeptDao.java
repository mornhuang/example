package cn.hxex.springcore.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class DeptDao extends JdbcDaoSupport implements IDeptDao {

	public List getDepts() {
		String sql = "SELECT deptno, dname, loc FROM dept";
		
		return super.getJdbcTemplate().query( sql, new DeptRowMapper() );
	}
	
	class DeptRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dept dept = new Dept();
			dept.setDeptNo( new Integer( rs.getString(1) ) );
			dept.setDName( rs.getString( 2 ) );
			dept.setLoc( rs.getString( 3 ) );
			return dept;
		}
		
	}
}
