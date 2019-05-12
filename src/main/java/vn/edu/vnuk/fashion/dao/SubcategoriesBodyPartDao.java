package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.SubcategoriesBodyPart;

public class SubcategoriesBodyPartDao {
	
    private Connection connection;

    public SubcategoriesBodyPartDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public SubcategoriesBodyPartDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(SubcategoriesBodyPart subcategoriesBodyPart) throws SQLException{

        String sqlQuery = "insert into subcategories_body_parts (subcategory_id , body_part_id) "
                        +	"values (? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, subcategoriesBodyPart.getSubcategory().getId());
                statement.setLong(2, subcategoriesBodyPart.getBodyPart().getId());

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
    
    
    //  READ (List of SubcategoriesBodyPart)
    @SuppressWarnings("finally")
    public List<SubcategoriesBodyPart> read() throws SQLException {

        String sqlQuery = "select * from subcategories_body_parts";
        PreparedStatement statement;
        List<SubcategoriesBodyPart> subcategoriesBodyParts = new ArrayList<SubcategoriesBodyPart>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                SubcategoriesBodyPart subcategoriesBodyPart = new SubcategoriesBodyPart();
                subcategoriesBodyPart.setId(results.getLong("id"));
//                SubcategoryDao subcategoryDao = new SubcategoryDao();
//                subcategoriesBodyPart.setSubcategory(subcategoryDao.read(results.getLong("subcategory_id")));
                BodyPartDao bodyPartDao = new BodyPartDao();
                subcategoriesBodyPart.setBodyPart(bodyPartDao.read(results.getLong("bodyPart_id")));
                
                subcategoriesBodyParts.add(subcategoriesBodyPart);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return subcategoriesBodyParts;
        }


    }


    //  READ (Single SubcategoriesBodyPart)
    public SubcategoriesBodyPart read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(SubcategoriesBodyPart subcategoriesBodyPart) throws SQLException {
        String sqlQuery = "update subcategories_body_parts subcategory_id=? body_part_id=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, subcategoriesBodyPart.getSubcategory().getId());
            statement.setLong(2, subcategoriesBodyPart.getBodyPart().getId());
            
            
            statement.execute();
            statement.close();
            
            System.out.println("SubcategoriesBodyPart successfully modified.");
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
        String sqlQuery = "delete from subcategories_body_parts where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("SubcategoriesBodyPart successfully deleted.");

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
    private SubcategoriesBodyPart read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from subcategories_body_parts where id=?";

        PreparedStatement statement;
        SubcategoriesBodyPart subcategoriesBodyPart = new SubcategoriesBodyPart();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                subcategoriesBodyPart.setId(results.getLong("id"));
//                SubcategoryDao subcategoryDao = new SubcategoryDao();
//                subcategoriesBodyPart.setSubcategory(subcategoryDao.read(results.getLong("subcategory_id")));
                BodyPartDao bodyPartDao = new BodyPartDao();
                subcategoriesBodyPart.setBodyPart(bodyPartDao.read(results.getLong("bodyPart_id")));
                

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return subcategoriesBodyPart;
        }

    }

}
