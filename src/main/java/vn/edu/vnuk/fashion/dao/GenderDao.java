package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Gender;

@Repository
public class GenderDao {
	
	@Autowired
    private  JdbcTemplate jdbcTemplate;
    
    //  CREATE
    public void create(Gender gender) throws SQLException{

        String sqlQuery = "insert into genders (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new collar in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {gender.getLabel()}
        						)
        				)
        		);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //  READ (List of Genders)
    public List<Gender> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM genders",
        			new BeanPropertyRowMapper<Gender>(Gender.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;
    }


    //  READ (Single Gender)
    public Gender read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM genders where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Gender>(Gender.class)
        	);
    }  

    
    //  UPDATE
    public void update(Gender gender) throws SQLException {
    	
        String sqlQuery = "update genders set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							gender.getLabel(),
							gender.getId()
						}
				);
            
            
            System.out.println("Gender successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from genders where id=?";

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