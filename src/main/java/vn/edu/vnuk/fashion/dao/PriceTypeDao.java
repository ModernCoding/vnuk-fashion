package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.PriceType;

public class PriceTypeDao {
	
    private Connection connection;

    public PriceTypeDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public PriceTypeDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(PriceType priceType) throws SQLException{

        String sqlQuery = "insert into price_types (label) "
                        +	"values (?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, priceType.getLabel());

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
    public List<PriceType> read() throws SQLException {

        String sqlQuery = "select * from price_types";
        PreparedStatement statement;
        List<PriceType> priceTypes = new ArrayList<PriceType>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                PriceType priceType = new PriceType();
                priceType.setId(results.getLong("id"));
                priceType.setLabel(results.getString("label"));
                
                priceTypes.add(priceType);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return priceTypes;
        }


    }


    //  READ (Single Collar)
    public PriceType read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(PriceType priceType) throws SQLException {
        String sqlQuery = "update price_types label=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, priceType.getLabel());
            
            statement.execute();
            statement.close();
            
            System.out.println("PriceType successfully modified.");
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
        String sqlQuery = "delete from price_types where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("PriceType successfully deleted.");

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
    private PriceType read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from price_types where id=?";

        PreparedStatement statement;
        PriceType priceType = new PriceType();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

            	priceType.setId(results.getLong("id"));
            	priceType.setLabel(results.getString("label"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return priceType;
        }

    }

}