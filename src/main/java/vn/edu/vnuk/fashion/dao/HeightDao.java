package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Height;


@Repository
public class HeightDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public HeightDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Height height) throws SQLException{

        String sqlQuery = "insert into height (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new height in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {height.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Heights)
    public List<Height> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM heights",
        			new BeanPropertyRowMapper<Height>(Height.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Height)
    public Height read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM heights where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Height>(Height.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Height height) throws SQLException {
    	
        String sqlQuery = "update heights set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							height.getLabel(),
							height.getId()
						}
				);
            
            
            System.out.println("Height successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from heights where id=?";

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