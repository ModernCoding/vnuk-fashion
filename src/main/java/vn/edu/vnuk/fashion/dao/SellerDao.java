package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Seller;

public class SellerDao {
	
    private Connection connection;

    public SellerDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public SellerDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Seller seller) throws SQLException{

        String sqlQuery = "insert into sellers (label , address , phone , email) "
                        +	"values (? , ? , ? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, seller.getLabel());
                statement.setString(2, seller.getAddress());
                statement.setString(3, seller.getPhone());
                statement.setString(4, seller.getEmail());

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
    public List<Seller> read() throws SQLException {

        String sqlQuery = "select * from sellers";
        PreparedStatement statement;
        List<Seller> sellers = new ArrayList<Seller>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Seller seller = new Seller();
                seller.setId(results.getLong("id"));
                seller.setLabel(results.getString("label"));
                seller.setAddress(results.getString("address"));
                seller.setPhone(results.getString("phone"));
                seller.setEmail(results.getString("email"));
                
                sellers.add(seller);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return sellers;
        }


    }


    //  READ (Single Seller)
    public Seller read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Seller seller) throws SQLException {
        String sqlQuery = "update sellers label=? address=? phone=? email=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, seller.getLabel());
            statement.setString(2, seller.getAddress());
            statement.setString(3, seller.getPhone());
            statement.setString(4, seller.getEmail());
            
            statement.execute();
            statement.close();
            
            System.out.println("Seller successfully modified.");
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
        String sqlQuery = "delete from sellers where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Seller successfully deleted.");

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
    private Seller read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from sellers where id=?";

        PreparedStatement statement;
        Seller seller = new Seller();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                seller.setId(results.getLong("id"));
                seller.setLabel(results.getString("label"));
                seller.setAddress(results.getString("address"));
                seller.setPhone(results.getString("phone"));
                seller.setEmail(results.getString("email"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return seller;
        }

    }

}