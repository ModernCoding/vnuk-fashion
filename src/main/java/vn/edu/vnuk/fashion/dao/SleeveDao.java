package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Sleeve;

public class SleeveDao {
	
    private Connection connection;

    public SleeveDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public SleeveDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Sleeve sleeve) throws SQLException{

        String sqlQuery = "insert into sleeves (label) "
                        +	"values (?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, sleeve.getLabel());

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
    public List<Sleeve> read() throws SQLException {

        String sqlQuery = "select * from sleeves";
        PreparedStatement statement;
        List<Sleeve> sleeves = new ArrayList<Sleeve>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Sleeve sleeve = new Sleeve();
                sleeve.setId(results.getLong("id"));
                sleeve.setLabel(results.getString("label"));
                
                sleeves.add(sleeve);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return sleeves;
        }


    }


    //  READ (Single Sleeve)
    public Sleeve read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Sleeve sleeve) throws SQLException {
        String sqlQuery = "update sleeves label=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, sleeve.getLabel());
            
            statement.execute();
            statement.close();
            
            System.out.println("Sleeve successfully modified.");
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
        String sqlQuery = "delete from sleeves where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Sleeve successfully deleted.");

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
    private Sleeve read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from sleeves where id=?";

        PreparedStatement statement;
        Sleeve sleeve = new Sleeve();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                sleeve.setId(results.getLong("id"));
                sleeve.setLabel(results.getString("label"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return sleeve;	
        }

    }

}