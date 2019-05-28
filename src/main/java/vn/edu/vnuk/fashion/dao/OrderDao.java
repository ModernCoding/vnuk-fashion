package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.helper.DaoHelpers;
import vn.edu.vnuk.fashion.model.Order;
import vn.edu.vnuk.fashion.rowmapper.OrderRowMapper;


@Repository
public class OrderDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
   
    //  CREATE
    public void create(Order order) throws SQLException{

        String sqlQuery = "insert into orders (customer_id, price_id, qty) "
                        +	"values (? , ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new order in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								order.getCustomerId(),
            								order.getPriceId(),
            								order.getQty()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of order)
    public List<Order> read(Order order) throws SQLException {
    	
    	String sqlQuery = "select orders.id"
		    			+ "     , orders.qty"
		    			+ "     , customers.id as customer_id"
						+ "     , customers.label as customer_label"
						+ "     , prices.id as price_id "
						+ "     , prices.value as price_value "
						+ "from orders "
						+ "inner join customers on orders.customer_id = customers.id "
						+ "inner join prices on orders.price_id = prices.id ";
    	
    	if (order.getCustomerId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "customers.id", String.valueOf(order.getCustomerId()));
    	
    	if (order.getPriceId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "prices.id", String.valueOf(order.getPriceId()));
    	
    	sqlQuery += " order by orders.id asc, customers.id asc, prices.id asc;";
    	
    	
        try {
        	
        	return new OrderRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
		return null;
    }


    //  READ (Single order)
    public Order read(Long id) throws SQLException{

    	String sqlQuery = "select orders.id"
    			+ "     , orders.qty"
    			+ "     , customers.id as customer_id"
				+ "     , customers.label as customer_label"
				+ "     , prices.id as price_id"
				+ "     , prices.value as price_value "
				+ "from orders "
				+ "inner join customers on orders.customer_id = customers.id "
				+ "inner join prices on orders.price_id = prices.id "
				+ "where orders.id = ? "
				+ "order by orders.id asc, customers.id asc, prices.id asc;";

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new OrderRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(Order order) throws SQLException {
        
    	String sqlQuery = "update orders set customer_id=?, price_id=?, qty=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							order.getCustomerId(),
							order.getPriceId(),
							order.getQty(),
							order.getId(),
						}
				);
            
            
            System.out.println("Order successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from orders where id=?";

        try {

            System.out.println(
            		String.format(
            				"%s record successfully removed from DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {id}
        						)
        				)
        		);

        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}