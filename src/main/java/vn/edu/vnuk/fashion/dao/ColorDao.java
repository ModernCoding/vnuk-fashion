package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Color;


@Repository
public class ColorDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ColorDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Color color) throws SQLException{

        String sqlQuery = "insert into colors (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new color in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {color.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Colors)
    public List<Color> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM colors",
        			new BeanPropertyRowMapper<Color>(Color.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Color)
    public Color read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM colors where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Color>(Color.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Color color) throws SQLException {
    	
        String sqlQuery = "update colors set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							color.getLabel(),
							color.getId()
						}
				);
            
            
            System.out.println("Color successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from colors where id=?";

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