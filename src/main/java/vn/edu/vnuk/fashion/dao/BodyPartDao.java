package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.BodyPart;

public class BodyPartDao {
	
    private Connection connection;

    public BodyPartDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public BodyPartDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(BodyPart bodyPart) throws SQLException{

        String sqlQuery = "insert into body_parts (label) "
                        +	"values (?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, bodyPart.getLabel());

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
    public List<BodyPart> read() throws SQLException {

        String sqlQuery = "select * from body_parts";
        PreparedStatement statement;
        List<BodyPart> bodyParts = new ArrayList<BodyPart>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                BodyPart bodyPart = new BodyPart();
                bodyPart.setId(results.getLong("id"));
                bodyPart.setLabel(results.getString("label"));
                
                bodyParts.add(bodyPart);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return bodyParts;
        }


    }


    //  READ (Single BodyPart)
    public BodyPart read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(BodyPart bodyPart) throws SQLException {
        String sqlQuery = "update body_parts label=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, bodyPart.getLabel());
            
            statement.execute();
            statement.close();
            
            System.out.println("BodyPart successfully modified.");
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
        String sqlQuery = "delete from body_parts where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("BodyPart successfully deleted.");

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
    private BodyPart read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from body_parts where id=?";

        PreparedStatement statement;
        BodyPart bodyPart = new BodyPart();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                bodyPart.setId(results.getLong("id"));
                bodyPart.setLabel(results.getString("label"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return bodyPart;	
        }

    }

}