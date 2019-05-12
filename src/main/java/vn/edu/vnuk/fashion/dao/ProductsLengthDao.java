package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Length;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsLength;

public class ProductsLengthDao {
	
    private Connection connection;

    public ProductsLengthDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public ProductsLengthDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(ProductsLength productsLength) throws SQLException{

        String sqlQuery = "insert into products_lengths (product_id, length_id) "
                        +	"values (? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, productsLength.getProduct().getId());
                statement.setLong(2, productsLength.getLength().getId());

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
    public List<ProductsLength> read() throws SQLException {

        String sqlQuery = "select * from products_lengths";
        PreparedStatement statement;
        List<ProductsLength> productsLengths = new ArrayList<ProductsLength>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	ProductsLength productsLength = new ProductsLength();
                
            	productsLength.setId(results.getLong("id"));
                
                // Process foreign key
                Long productIdFromDB = results.getLong("product_id");
                Long lengthIdFromDB = results.getLong("length_id");
                
                ProductDao productDao = new ProductDao();
                LengthDao lengthDao = new LengthDao();
                
                Product product = productDao.read(productIdFromDB);
                Length length = lengthDao.read(lengthIdFromDB);
                
                productsLength.setProduct(product);
                productsLength.setLength(length);
                
                productsLengths.add(productsLength);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return productsLengths;
        }


    }


    //  READ (Single ProductsLength)
    public ProductsLength read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(ProductsLength productsLength) throws SQLException {
        String sqlQuery = "update products_lengths product_id=? length_id=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, productsLength.getProduct().getId());
            statement.setLong(2, productsLength.getLength().getId());
            
            statement.execute();
            statement.close();
            
            System.out.println("products_lengths successfully modified.");
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
        String sqlQuery = "delete from products_lengths where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("products_lengths successfully deleted.");

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
    private ProductsLength read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from products_lengths where id=?";

        PreparedStatement statement;
        ProductsLength productsLength = new ProductsLength();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

            	productsLength.setId(results.getLong("id"));
                
                // Process foreign key
                Long productIdFromDB = results.getLong("product_id");
                Long lengthIdFromDB = results.getLong("length_id");
                
                ProductDao productDao = new ProductDao();
                LengthDao lengthDao = new LengthDao();
                
                Product product = productDao.read(productIdFromDB);
                Length length = lengthDao.read(lengthIdFromDB);
                
                productsLength.setProduct(product);
                productsLength.setLength(length);


            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return productsLength;
        }

    }

}