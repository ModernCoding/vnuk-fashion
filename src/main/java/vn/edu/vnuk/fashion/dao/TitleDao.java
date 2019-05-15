package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Title;


@Repository
public class TitleDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public TitleDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Title title) throws SQLException{

        String sqlQuery = "insert into titles (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new title in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {title.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Titles)
    public List<Title> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM titles",
        			new BeanPropertyRowMapper<Title>(Title.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Title)
    public Title read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM titles where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Title>(Title.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Title title) throws SQLException {
    	
        String sqlQuery = "update titles set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							title.getLabel(),
							title.getId()
						}
				);
            
            
            System.out.println("Title successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from titles where id=?";

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