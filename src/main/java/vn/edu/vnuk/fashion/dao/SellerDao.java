package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Seller;


@Repository
public class SellerDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public SellerDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Seller seller) throws SQLException{

        String sqlQuery = "insert into sellers (label, address, phone, email) values (?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new seller in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								seller.getLabel(),
            								seller.getAddress(),
            								seller.getPhone(),
            								seller.getEmail()
            								
            								}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Sellers)
    public List<Seller> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM sellers",
        			new BeanPropertyRowMapper<Seller>(Seller.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Seller)
    public Seller read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM sellers where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Seller>(Seller.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Seller seller) throws SQLException {
    	
        String sqlQuery = "update sellers set label=?, address=?, phone=?, email=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							seller.getLabel(),
							seller.getAddress(),
							seller.getPhone(),
							seller.getEmail(),
							seller.getId()
						}
				);
            
            
            System.out.println("Seller successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from sellers where id=?";

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