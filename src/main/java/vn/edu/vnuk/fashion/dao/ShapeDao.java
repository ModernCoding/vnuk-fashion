package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Shape;


@Repository
public class ShapeDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ShapeDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Shape shape) throws SQLException{

        String sqlQuery = "insert into shape (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new shape in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {shape.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Shapes)
    public List<Shape> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM shapes",
        			new BeanPropertyRowMapper<Shape>(Shape.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Shape)
    public Shape read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM shapes where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Shape>(Shape.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Shape shape) throws SQLException {
    	
        String sqlQuery = "update shapes set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							shape.getLabel(),
							shape.getId()
						}
				);
            
            
            System.out.println("Shape successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from shapes where id=?";

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