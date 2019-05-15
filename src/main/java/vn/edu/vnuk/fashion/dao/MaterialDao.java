package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Material;


@Repository
public class MaterialDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public MaterialDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Material material) throws SQLException{

        String sqlQuery = "insert into material (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new material in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {material.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Materials)
    public List<Material> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM materials",
        			new BeanPropertyRowMapper<Material>(Material.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Material)
    public Material read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM materials where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Material>(Material.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Material material) throws SQLException {
    	
        String sqlQuery = "update materials set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							material.getLabel(),
							material.getId()
						}
				);
            
            
            System.out.println("Material successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from materials where id=?";

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