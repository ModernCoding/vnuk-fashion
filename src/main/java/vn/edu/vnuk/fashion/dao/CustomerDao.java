package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Customer;

public class CustomerDao {
	
    private Connection connection;

    public CustomerDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public CustomerDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Customer customer) throws SQLException{

        String sqlQuery = "insert into customers (title_id, label, address, phone, email) "
                        +	"values (? , ? , ? , ? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, customer.getTitle().getId());
                statement.setString(2, customer.getLabel());
                statement.setString(3, customer.getAddress());
                statement.setString(4, customer.getPhone());
                statement.setString(5, customer.getEmail());

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
    
    
    //  READ (List of Customers)
    @SuppressWarnings("finally")
    public List<Customer> read() throws SQLException {

        String sqlQuery = "select * from customers";
        PreparedStatement statement;
        List<Customer> customers = new ArrayList<Customer>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Customer customer = new Customer();
                customer.setId(results.getLong("id"));
                TitleDao titleDao = new TitleDao();
                customer.setTitle(titleDao.read(results.getLong("title_id")));
                customer.setAddress(results.getString("address"));
                customer.setPhone(results.getString("phone"));
                customer.setEmail(results.getString("email"));
                
                customers.add(customer);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return customers;
        }


    }


    //  READ (Single Customer)
    public Customer read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Customer customer) throws SQLException {
        String sqlQuery = "update customers title_id=? label=? address=? phone=? email=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, customer.getTitle().getId());
            statement.setString(2, customer.getLabel());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getEmail());
            
            
            statement.execute();
            statement.close();
            
            System.out.println("Customer successfully modified.");
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
        String sqlQuery = "delete from customers where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Customer successfully deleted.");

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
    private Customer read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from customers where id=?";

        PreparedStatement statement;
        Customer customer = new Customer();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                customer.setId(results.getLong("id"));
                TitleDao titleDao = new TitleDao();
                customer.setTitle(titleDao.read(results.getLong("title_id")));
                customer.setAddress(results.getString("address"));
                customer.setPhone(results.getString("phone"));
                customer.setEmail(results.getString("email"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return customer;
        }

    }

}