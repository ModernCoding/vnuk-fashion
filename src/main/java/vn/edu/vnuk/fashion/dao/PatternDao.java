package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Pattern;

public class PatternDao {
	
    private Connection connection;

    public PatternDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public PatternDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Pattern pattern) throws SQLException{

        String sqlQuery = "insert into patterns (label) "
                        +	"values (?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, pattern.getLabel());

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
    public List<Pattern> read() throws SQLException {

        String sqlQuery = "select * from patterns";
        PreparedStatement statement;
        List<Pattern> patterns = new ArrayList<Pattern>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Pattern pattern = new Pattern();
                pattern.setId(results.getLong("id"));
                pattern.setLabel(results.getString("label"));
                
                patterns.add(pattern);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return patterns;
        }


    }


    //  READ (Single Collar)
    public Pattern read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Pattern pattern) throws SQLException {
        String sqlQuery = "update patterns label=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, pattern.getLabel());
            
            statement.execute();
            statement.close();
            
            System.out.println("Pattern successfully modified.");
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
        String sqlQuery = "delete from patterns where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Pattern successfully deleted.");

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
    private Pattern read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from patterns where id=?";

        PreparedStatement statement;
        Pattern pattern = new Pattern();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

            	pattern.setId(results.getLong("id"));
            	pattern.setLabel(results.getString("label"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return pattern;
        }

    }

}