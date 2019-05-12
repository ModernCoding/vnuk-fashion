package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Order;

public class OrderDao {
	
    private Connection connection;

    public OrderDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public OrderDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Order order) throws SQLException{

        String sqlQuery = "insert into orders (customer_id , price_id , qty) "
                        +	"values (? , ? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, order.getCustomer().getId());
                statement.setLong(2, order.getPrice().getId());
                statement.setInt(3, order.getQty());

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
    
    
    //  READ (List of Orders)
    @SuppressWarnings("finally")
    public List<Order> read() throws SQLException {

        String sqlQuery = "select * from orders";
        PreparedStatement statement;
        List<Order> orders = new ArrayList<Order>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Order order = new Order();
                order.setId(results.getLong("id"));
                CustomerDao customerDao = new CustomerDao();
                order.setCustomer(customerDao.read(results.getLong("customer_id")));
                PriceDao priceDao = new PriceDao();
                order.setPrice(priceDao.read(results.getInt("price_id")));
                order.setQty(results.getInt("qty"));
                
                orders.add(order);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return orders;
        }


    }


    //  READ (Single Order)
    public Order read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Order order) throws SQLException {
        String sqlQuery = "update orders customer_id=? price_id=? qty=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, order.getCustomer().getId());
            statement.setLong(2, order.getPrice().getId());
            statement.setInt(3, order.getQty());
            
            
            statement.execute();
            statement.close();
            
            System.out.println("Order successfully modified.");
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
        String sqlQuery = "delete from orders where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Order successfully deleted.");

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
    private Order read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from orders where id=?";

        PreparedStatement statement;
        Order order = new Order();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                order.setId(results.getLong("id"));
                CustomerDao customerDao = new CustomerDao();
                order.setCustomer(customerDao.read(results.getLong("customer_id")));
                PriceDao priceDao = new PriceDao();
                order.setPrice(priceDao.read(results.getInt("price_id")));
                order.setQty(results.getInt("qty"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return order;
        }

    }

}