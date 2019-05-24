package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Review;
import vn.edu.vnuk.fashion.rowmapper.ReviewRowMapper;


@Repository
public class ReviewDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ReviewDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Review review) throws SQLException{

        String sqlQuery = "insert into reviews (order_id, rating, description) "
                        +	"values (? , ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new review in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								review.getOrderId(),
            								review.getRating(),
            								review.getDescription()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of review)
    public List<Review> read(String orderId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.rating"
		    			+ "     , t01.description"
		    			+ "     , t02.id as order_id"
						+ "     , t02.customer_id as customer_id"
						+ "     , t02.price_id as price_id"
						+ "     , t02.qty as order_qty"
						
						+ "  from reviews t01, orders t02"
						+ " where t02.id = t01.order_id"
				;
    	
    	if (orderId != null) {
    		sqlQuery += String.format("   and t02.id = %s", orderId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new ReviewRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single review)
    public Review read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t01.rating"
    			+ "     , t01.description"
    			+ "     , t02.id as order_id"
				+ "     , t02.customer_id as customer_id"
				+ "     , t02.price_id as price_id"
				+ "     , t02.qty as order_qty"
				+ "  from reviews t01, orders t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.order_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new ReviewRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(Review review) throws SQLException {
        
    	String sqlQuery = "update reviews set order_id=?, rating=?, description=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							review.getOrderId(),
							review.getRating(),
							review.getDescription(),
							review.getId(),
						}
				);
            
            
            System.out.println("Review successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from reviews where id=?";

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