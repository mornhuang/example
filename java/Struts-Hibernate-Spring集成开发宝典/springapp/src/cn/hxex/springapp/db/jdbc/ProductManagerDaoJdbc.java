package cn.hxex.springapp.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import cn.hxex.springapp.bus.Product;
import cn.hxex.springapp.db.ProductManagerDao;

public class ProductManagerDaoJdbc implements ProductManagerDao {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	private int maxID = -1;
	
	private synchronized int getID() {
		if( maxID>0 ) {
			return ++maxID;
		}
		
		JdbcTemplate template = new JdbcTemplate( ds );
		maxID = template.queryForInt( "SELECT max(id) FROM products" );
		return ++maxID;
	}
	
	public List getProductList() {

		logger.info("Getting products!");
        ProductQuery pq = new ProductQuery( ds );
        return pq.execute();

	}

	public void addProduct(Product pct) {
		
		logger.info("Increasing price by " + pct + "%");
        SqlUpdate su = 
            new SqlUpdate( ds, "insert into products( id, description, price ) values( ?, ?, ? )" );
        su.declareParameter( new SqlParameter("id", Types.INTEGER) );
        su.declareParameter( new SqlParameter("description", Types.VARCHAR));
        su.declareParameter( new SqlParameter("price", Types.DOUBLE));
        su.compile();
        Object[] oa = new Object[3];
        oa[0] = new Integer( getID() );
        oa[1] = pct.getDescription();
        oa[2] = pct.getPrice();
        int count = su.update( oa );
        logger.info( "Rows affected: " + count);
        
	}

	class ProductQuery extends MappingSqlQuery {

		ProductQuery(DataSource ds) {
			super(ds, "SELECT id, description, price from products");
			compile();
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {

			Product prod = new Product();
			prod.setId(rs.getInt("id"));
			prod.setDescription(rs.getString("description"));
			prod.setPrice(new Double(rs.getDouble("price")));
			
			return prod;

		}
	}

}
