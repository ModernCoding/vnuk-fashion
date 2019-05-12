package vn.edu.vnuk.fashion.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Category;

@Repository
public class CategoryDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public CategoryDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


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
    
    
    //  READ (List of Tasks)
    @SuppressWarnings("finally")
    public List<Category> read() throws SQLException {

        String sqlQuery = "select * from categories";
        PreparedStatement statement;
        List<Category> categories = new ArrayList<Category>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Category category = new Category();
                category.setId(results.getLong("id"));
                category.setLabel(results.getString("label"));
                
                categories.add(category);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return categories;
        }


    }


    //  READ (Single Category)
    public Category read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Category category) throws SQLException {
        String sqlQuery = "update categories label=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, category.getLabel());
            
            statement.execute();
            statement.close();
            
            System.out.println("Category successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        finally {
            connection.close();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from categories where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Category successfully deleted.");

        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        finally {
            connection.close();
        }

    }
  
    
    //  PRIVATE
    
    @SuppressWarnings("finally")
    private Category read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from categories where id=?";

        PreparedStatement statement;
        Category category = new Category();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                category.setId(results.getLong("id"));
                category.setLabel(results.getString("label"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return category;	
        }

    }

}