package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Gender;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsGender;


public class ProductsGenderDao {
	
    private Connection connection;

    public ProductsGenderDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public ProductsGenderDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(ProductsGender productsGender) throws SQLException{

        String sqlQuery = "insert into products_lengths (product_id, gender_id) "
                        +	"values (? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, productsGender.getProduct().getId());
                statement.setLong(2, productsGender.getGender().getId());

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
    
    
    //  READ (List of ProductsLength)
    @SuppressWarnings("finally")
    public List<ProductsGender> read() throws SQLException {

        String sqlQuery = "select * from products_genders";
        PreparedStatement statement;
        List<ProductsGender> productsGenders = new ArrayList<ProductsGender>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	ProductsGender productsGender = new ProductsGender();
                
            	productsGender.setId(results.getLong("id"));
                
                // Process foreign key
                Long productIdFromDB = results.getLong("product_id");
                Long genderIdFromDB = results.getLong("gender_id");
                
                ProductDao productDao = new ProductDao();
                GenderDao genderDao = new GenderDao();
                
                Product product = productDao.read(productIdFromDB);
                Gender gender  = genderDao.read(genderIdFromDB);
                
                productsGender.setProduct(product);
                productsGender.setGender(gender);
                
                productsGenders.add(productsGender);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return productsGenders;
        }


    }


    //  READ (Single ProductsGender)
    public ProductsGender read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(ProductsGender productsGender) throws SQLException {
        String sqlQuery = "update products_genders product_id=? gender_id=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, productsGender.getProduct().getId());
            statement.setLong(2, productsGender.getGender().getId());
            
            statement.execute();
            statement.close();
            
            System.out.println("products_genders successfully modified.");
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
        String sqlQuery = "delete from products_genders where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("products_genders successfully deleted.");

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
    private ProductsGender read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from products_genders where id=?";

        PreparedStatement statement;
        ProductsGender productsGender = new ProductsGender();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

            	productsGender.setId(results.getLong("id"));
                
                // Process foreign key
                Long productIdFromDB = results.getLong("product_id");
                Long genderIdFromDB = results.getLong("gender_id");
                
                ProductDao productDao = new ProductDao();
                GenderDao genderDao = new GenderDao();
                
                Product product = productDao.read(productIdFromDB);
                Gender gender  = genderDao.read(genderIdFromDB);
                
                productsGender.setProduct(product);
                productsGender.setGender(gender);

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return productsGender;
        }

    }

}