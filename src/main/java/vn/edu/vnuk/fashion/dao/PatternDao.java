package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Pattern;


@Repository
public class PatternDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PatternDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Pattern pattern) throws SQLException{

        String sqlQuery = "insert into patterns (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new pattern in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {pattern.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Patterns)
    public List<Pattern> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM patterns",
        			new BeanPropertyRowMapper<Pattern>(Pattern.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Pattern)
    public Pattern read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM patterns where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Pattern>(Pattern.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Pattern pattern) throws SQLException {
    	
        String sqlQuery = "update patterns set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							pattern.getLabel(),
							pattern.getId()
						}
				);
            
            
            System.out.println("Pattern successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from patterns where id=?";

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