package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Category;
import vn.edu.vnuk.fashion.model.Size;
import vn.edu.vnuk.fashion.model.Subcategory;

public class SubcategoryDao {
	
    private Connection connection;

    public SubcategoryDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public SubcategoryDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Subcategory subcategory) throws SQLException{

        String sqlQuery = "insert into subcategories (category_id, label) "
                        +	"values (? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, subcategory.getCategory().getId());
                statement.setString(2, subcategory.getLabel());

                // 	Executing statement
                statement.execute();

                System.out.println("New record in DB !");

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                System.out.println("Done !");
                connection.close();
        }

    }
    
    
    //  READ (List of Subcategories)
    @SuppressWarnings("finally")
    public List<Subcategory> read() throws SQLException {

        String sqlQuery = "select * from subcategories";
        PreparedStatement statement;
        List<Subcategory> subcategories = new ArrayList<Subcategory>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	Subcategory subcategory = new Subcategory();
                
                subcategory.setId(results.getLong("id"));
                subcategory.setLabel(results.getString("label"));
                
                // Process foreign key
                Long categoryIdFromDB = results.getLong("category_id");
                CategoryDao categoryDao = new CategoryDao();
                Category category = categoryDao.read(categoryIdFromDB);
                subcategory.setCategory(category);

                subcategories.add(subcategory);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return subcategories;
        }


    }


    //  READ (Single Category)
    public Subcategory read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Subcategory subcategory) throws SQLException {
        String sqlQuery = "update subcategories category_id=? label=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, subcategory.getCategory().getId());
            statement.setString(2, subcategory.getLabel());
            
            statement.execute();
            statement.close();
            
            System.out.println("Subcategory successfully modified.");
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
        String sqlQuery = "delete from subcategories where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Subcategory successfully deleted.");

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
    private Subcategory read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from subcategories where id=?";

        PreparedStatement statement;
        Subcategory subcategory = new Subcategory();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

            	subcategory.setId(results.getLong("id"));
                subcategory.setLabel(results.getString("label"));
                
                // Process foreign key
                Long categoryIdFromDB = results.getLong("category_id");
                CategoryDao categoryDao = new CategoryDao();
                Category category = categoryDao.read(categoryIdFromDB);
                subcategory.setCategory(category);

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return subcategory;
        }

    }

}