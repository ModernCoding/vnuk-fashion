package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Order;
import vn.edu.vnuk.fashion.rowmapper.OrderRowMapper;


@Repository
public class OrderDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public OrderDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


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
    public List<Order> read(String customerId, String priceId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.qty"
		    			+ "     , t02.id as customer_id"
						+ "     , t02.title_id as title_id"
						+ "     , t02.label as customer_title"
						+ "     , t02.address as customer_address"
						+ "     , t02.phone as customer_phone"
						+ "     , t02.email as customer_email"
						+ "     , t03.id as price_id"
						+ "     , t03.products_size_id as products_size_id"
						+ "     , t03.products_color_id as products_color_id"
						+ "     , t03.products_pattern_id as products_pattern_id"
						+ "     , t03.products_length_id as products_length_id"
						+ "     , t03.seller_id as seller_id"
						+ "     , t03.price_type_id as price_type_id"
						+ "     , t03.value as price_value"
						+ "  from orders t01, customers t02, prices t03"
						+ " where t02.id = t01.customer_id and t03.id = t01.price_id"
				;
    	
    	if (customerId != null && priceId != null) {
    		sqlQuery += String.format("   and t02.id = %s", customerId, " and t03.id = %s", priceId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t03.id asc, t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new OrderRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single order)
    public Order read(Long id) throws SQLException{

    	String sqlQuery =  "select t01.id"
    			+ "     , t01.qty"
    			+ "     , t02.id as customer_id"
				+ "     , t02.title_id as title_id"
				+ "     , t02.label as customer_title"
				+ "     , t02.address as customer_address"
				+ "     , t02.phone as customer_phone"
				+ "     , t02.email as customer_email"
				+ "     , t03.id as price_id"
				+ "     , t03.products_size_id as products_size_id"
				+ "     , t03.products_color_id as products_color_id"
				+ "     , t03.products_pattern_id as products_pattern_id"
				+ "     , t03.products_length_id as products_length_id"
				+ "     , t03.seller_id as seller_id"
				+ "     , t03.price_type_id as price_type_id"
				+ "     , t03.value as price_value"
				+ "  from orders t01, customers t02, prices t03"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.customer_id and t03.id = t01.price_id"
				+ " order by t03.id asc, t02.id asc, t01.id asc"
				+ ";"
		;

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