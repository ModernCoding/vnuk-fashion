package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Shape;

public class ShapeDao {
	
    private Connection connection;

    public ShapeDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public ShapeDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Shape shape) throws SQLException{

        String sqlQuery = "insert into shapes (label) "
                        +	"values (?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, shape.getLabel());

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
    
    
    //  READ (List of Tasks)
    @SuppressWarnings("finally")
    public List<Shape> read() throws SQLException {

        String sqlQuery = "select * from shapes";
        PreparedStatement statement;
        List<Shape> shapes = new ArrayList<Shape>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Shape shape = new Shape();
                shape.setId(results.getLong("id"));
                shape.setLabel(results.getString("label"));
                
                shapes.add(shape);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return shapes;
        }


    }


    //  READ (Single Height)
    public Shape read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Shape shape) throws SQLException {
        String sqlQuery = "update shapes label=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, shape.getLabel());
            
            statement.execute();
            statement.close();
            
            System.out.println("Shape successfully modified.");
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
        String sqlQuery = "delete from shapes where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Shape successfully deleted.");

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
    private Shape read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from shapes where id=?";

        PreparedStatement statement;
        Shape shape = new Shape();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                shape.setId(results.getLong("id"));
                shape.setLabel(results.getString("label"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return shape;
        }

    }

}