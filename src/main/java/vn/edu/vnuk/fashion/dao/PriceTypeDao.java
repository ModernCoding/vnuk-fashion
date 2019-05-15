package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.PriceType;


@Repository
public class PriceTypeDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PriceTypeDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(PriceType priceType) throws SQLException{

        String sqlQuery = "insert into price_types (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new priceType in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {priceType.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of PriceTypes)
    public List<PriceType> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM price_types",
        			new BeanPropertyRowMapper<PriceType>(PriceType.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single PriceType)
    public PriceType read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM price_types where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<PriceType>(PriceType.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(PriceType priceType) throws SQLException {
    	
        String sqlQuery = "update price_types set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							priceType.getLabel(),
							priceType.getId()
						}
				);
            
            
            System.out.println("PriceType successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from price_types where id=?";

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