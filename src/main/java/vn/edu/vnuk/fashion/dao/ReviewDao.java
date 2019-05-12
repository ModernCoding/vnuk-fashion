package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Review;

public class ReviewDao {
	
    private Connection connection;

    public ReviewDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public ReviewDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Review review) throws SQLException{

        String sqlQuery = "insert into reviews (order_id , rating , description) "
                        +	"values (? , ? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, review.getOrder().getId());
                statement.setInt(2, review.getRating());
                statement.setString(3, review.getDescription());

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
    
    
    //  READ (List of Reviews)
    @SuppressWarnings("finally")
    public List<Review> read() throws SQLException {

        String sqlQuery = "select * from reviews";
        PreparedStatement statement;
        List<Review> reviews = new ArrayList<Review>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Review review = new Review();
                review.setId(results.getLong("id"));
                OrderDao orderDao = new OrderDao();
                review.setOrder(orderDao.read(results.getLong("order_id")));
                review.setRating(results.getInt("rating"));
                review.setDescription(results.getString("description"));
                
                reviews.add(review);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return reviews;
        }


    }


    //  READ (Single Review)
    public Review read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Review review) throws SQLException {
        String sqlQuery = "update reviews order_id=? rating=? description=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, review.getOrder().getId());
            statement.setInt(2, review.getRating());
            statement.setString(3, review.getDescription());
            
            
            statement.execute();
            statement.close();
            
            System.out.println("Review successfully modified.");
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
        String sqlQuery = "delete from reviews where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Review successfully deleted.");

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
    private Review read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from reviews where id=?";

        PreparedStatement statement;
        Review review = new Review();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                review.setId(results.getLong("id"));
                OrderDao orderDao = new OrderDao();
                review.setOrder(orderDao.read(results.getLong("order_id")));
                review.setRating(results.getInt("rating"));
                review.setDescription(results.getString("description"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return review;
        }

    }

}