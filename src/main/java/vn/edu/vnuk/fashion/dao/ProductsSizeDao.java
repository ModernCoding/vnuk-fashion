package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsSize;
import vn.edu.vnuk.fashion.model.Size;

public class ProductsSizeDao {
	
    private Connection connection;

    public ProductsSizeDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public ProductsSizeDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(ProductsSize productsSize) throws SQLException{

        String sqlQuery = "insert into products_lengths (product_id, size_id) "
                        +	"values (? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, productsSize.getProduct().getId());
                statement.setLong(2, productsSize.getSize().getId());

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
    public List<ProductsSize> read() throws SQLException {

        String sqlQuery = "select * from products_sizes";
        PreparedStatement statement;
        List<ProductsSize> productsSizes = new ArrayList<ProductsSize>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	ProductsSize productsSize = new ProductsSize();
                
            	productsSize.setId(results.getLong("id"));
                
                // Process foreign key
                Long productIdFromDB = results.getLong("product_id");
                Long sizeIdFromDB = results.getLong("size_id");
                
                ProductDao productDao = new ProductDao();
                SizeDao sizeDao = new SizeDao();
                
                Product product = productDao.read(productIdFromDB);
                Size size  = sizeDao.read(sizeIdFromDB);
                
                productsSize.setProduct(product);
                productsSize.setSize(size);
                
                productsSizes.add(productsSize);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return productsSizes;
        }


    }


    //  READ (Single ProductsSize)
    public ProductsSize read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(ProductsSize productsSize) throws SQLException {
        String sqlQuery = "update products_sizes product_id=? size_id=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, productsSize.getProduct().getId());
            statement.setLong(2, productsSize.getSize().getId());
            
            statement.execute();
            statement.close();
            
            System.out.println("products_sizes successfully modified.");
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
        String sqlQuery = "delete from products_sizes where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("products_sizes successfully deleted.");

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
    private ProductsSize read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from products_sizes where id=?";

        PreparedStatement statement;
        ProductsSize productsSize = new ProductsSize();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

            	productsSize.setId(results.getLong("id"));
                
                // Process foreign key
                Long productIdFromDB = results.getLong("product_id");
                Long sizeIdFromDB = results.getLong("size_id");
                
                ProductDao productDao = new ProductDao();
                SizeDao sizeDao = new SizeDao();
                
                Product product = productDao.read(productIdFromDB);
                Size size  = sizeDao.read(sizeIdFromDB);
                
                productsSize.setProduct(product);
                productsSize.setSize(size);


            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return productsSize;
        }

    }

}