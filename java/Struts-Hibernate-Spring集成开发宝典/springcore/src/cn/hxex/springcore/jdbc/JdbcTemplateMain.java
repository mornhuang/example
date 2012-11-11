package cn.hxex.springcore.jdbc;

import java.sql.Types;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateMain {

	public static void main(String[] args) {
		
		ClassPathResource resource = new ClassPathResource("cn/hxex/springcore/jdbc/JdbcTemplateBeans.xml");
		BeanFactory factory = new XmlBeanFactory(resource);

		JdbcTemplate template = (JdbcTemplate)factory.getBean( "jdbcTemplate" );
		
		// 增加操作
		String sql = "INSERT INTO dept(deptno, dname, loc) values( ?, ?, ? )";
		int[] argTypes = { Types.NUMERIC, Types.VARCHAR, Types.VARCHAR };
		Object[] sqlArgs = new Object[3];
		sqlArgs[0] = new Integer( "50" );
		sqlArgs[1] = "Sales In China";
		sqlArgs[2] = "China";
		template.update( sql, sqlArgs, argTypes );
		
		// 查询操作
		sql = "SELECT deptno, dname, loc FROM dept";
		List result = template.queryForList( sql );
		for( int i=0; i<result.size(); i++ ) {
			Map map = (Map)result.get( i );
			Iterator keys = map.keySet().iterator();
			while( keys.hasNext() ) {
				Object key = keys.next();
				Object value = map.get( key );
				System.out.print( key + "=" + value + "\t" );
			}
			System.out.println();
		}
		
		// 删除操作
		sql = "DELETE FROM dept WHERE deptno = ?";
		template.update( sql, new Object[]{ new Integer("50") } );
	}

}
