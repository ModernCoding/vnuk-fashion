package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Sleeve;



@Repository
public class SleeveDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public SleeveDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Sleeve sleeve) throws SQLException{

        String sqlQuery = "insert into sleeves (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new collar in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {sleeve.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Sleeves)
    public List<Sleeve> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM sleeves",
        			new BeanPropertyRowMapper<Sleeve>(Sleeve.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Sleeve)
    public Sleeve read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM sleeves where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Sleeve>(Sleeve.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Sleeve sleeve) throws SQLException {
    	
        String sqlQuery = "update sleeves set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							sleeve.getLabel(),
							sleeve.getId()
						}
				);
            
            
            System.out.println("Sleeve successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from sleeves where id=?";

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