package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Maker;

public class MakerDao {
	
    private Connection connection;

    public MakerDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public MakerDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Maker maker) throws SQLException{

        String sqlQuery = "insert into makers (label , address , phone , email) "
                        +	"values (? , ? , ? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, maker.getLabel());
                statement.setString(2, maker.getAddress());
                statement.setString(3, maker.getPhone());
                statement.setString(4, maker.getEmail());

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
    public List<Maker> read() throws SQLException {

        String sqlQuery = "select * from makers";
        PreparedStatement statement;
        List<Maker> makers = new ArrayList<Maker>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Maker maker = new Maker();
                maker.setId(results.getLong("id"));
                maker.setLabel(results.getString("label"));
                maker.setAddress(results.getString("address"));
                maker.setPhone(results.getString("phone"));
                maker.setEmail(results.getString("email"));
                
                makers.add(maker);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return makers;
        }


    }


    //  READ (Single Maker)
    public Maker read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Maker maker) throws SQLException {
        String sqlQuery = "update makers label=? address=? phone=? email=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, maker.getLabel());
            statement.setString(2, maker.getAddress());
            statement.setString(3, maker.getPhone());
            statement.setString(4, maker.getEmail());
            
            statement.execute();
            statement.close();
            
            System.out.println("Maker successfully modified.");
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
        String sqlQuery = "delete from makers where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Maker successfully deleted.");

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
    private Maker read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from makers where id=?";

        PreparedStatement statement;
        Maker maker = new Maker();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                maker.setId(results.getLong("id"));
                maker.setLabel(results.getString("label"));
                maker.setAddress(results.getString("address"));
                maker.setPhone(results.getString("phone"));
                maker.setEmail(results.getString("email"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return maker;
        }

    }

}