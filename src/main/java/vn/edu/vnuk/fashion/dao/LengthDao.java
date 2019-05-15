package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Length;


@Repository
public class LengthDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public LengthDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Length length) throws SQLException{

        String sqlQuery = "insert into lengths (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new length in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {length.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Lengths)
    public List<Length> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM lengths",
        			new BeanPropertyRowMapper<Length>(Length.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Length)
    public Length read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM lengths where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Length>(Length.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Length length) throws SQLException {
    	
        String sqlQuery = "update lengths set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							length.getLabel(),
							length.getId()
						}
				);
            
            
            System.out.println("Length successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from lengths where id=?";

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