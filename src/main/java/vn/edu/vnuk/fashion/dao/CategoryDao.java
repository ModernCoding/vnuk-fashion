package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Category;


@Repository
public class CategoryDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
    
    //  CREATE
    public void create(Category category) throws SQLException{

        String sqlQuery = "insert into categories (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new category in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {category.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Categories)
    public List<Category> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM categories",
        			new BeanPropertyRowMapper<Category>(Category.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Category)
    public Category read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM categories where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Category>(Category.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Category category) throws SQLException {
    	
        String sqlQuery = "update categories set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							category.getLabel(),
							category.getId()
						}
				);
            
            
            System.out.println("Category successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from categories where id=?";

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