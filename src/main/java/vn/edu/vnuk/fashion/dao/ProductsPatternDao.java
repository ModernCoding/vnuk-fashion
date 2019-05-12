package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Color;
import vn.edu.vnuk.fashion.model.Length;
import vn.edu.vnuk.fashion.model.Pattern;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsColor;
import vn.edu.vnuk.fashion.model.ProductsLength;
import vn.edu.vnuk.fashion.model.ProductsPattern;

public class ProductsPatternDao {
	
    private Connection connection;

    public ProductsPatternDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public ProductsPatternDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(ProductsPattern productsPattern) throws SQLException{

        String sqlQuery = "insert into products_lengths (product_id, pattern_id) "
                        +	"values (? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, productsPattern.getProduct().getId());
                statement.setLong(2, productsPattern.getPattern().getId());

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
    public List<ProductsPattern> read() throws SQLException {

        String sqlQuery = "select * from products_patterns";
        PreparedStatement statement;
        List<ProductsPattern> productsPatterns = new ArrayList<ProductsPattern>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	ProductsPattern productsPattern = new ProductsPattern();
                
            	productsPattern.setId(results.getLong("id"));
                
                // Process foreign key
                Long productIdFromDB = results.getLong("product_id");
                Long patternIdFromDB = results.getLong("pattern_id");
                
                ProductDao productDao = new ProductDao();
                PatternDao patternDao = new PatternDao();
                
                Product product = productDao.read(productIdFromDB);
                Pattern pattern  = patternDao.read(patternIdFromDB);
                
                productsPattern.setProduct(product);
                productsPattern.setPattern(pattern);
                
                productsPatterns.add(productsPattern);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return productsPatterns;
        }


    }


    //  READ (Single ProductsPattern)
    public ProductsPattern read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(ProductsPattern productsPattern) throws SQLException {
        String sqlQuery = "update products_patterns product_id=? pattern_id=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, productsPattern.getProduct().getId());
            statement.setLong(2, productsPattern.getPattern().getId());
            
            statement.execute();
            statement.close();
            
            System.out.println("products_patterns successfully modified.");
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
        String sqlQuery = "delete from products_patterns where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("products_patterns successfully deleted.");

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
    private ProductsPattern read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from products_patterns where id=?";

        PreparedStatement statement;
        ProductsPattern productsPattern = new ProductsPattern();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

            	productsPattern.setId(results.getLong("id"));
                
                // Process foreign key
                Long productIdFromDB = results.getLong("product_id");
                Long patternIdFromDB = results.getLong("pattern_id");
                
                ProductDao productDao = new ProductDao();
                PatternDao patternDao = new PatternDao();
                
                Product product = productDao.read(productIdFromDB);
                Pattern pattern = patternDao.read(patternIdFromDB);
                
                productsPattern.setProduct(product);
                productsPattern.setPattern(pattern);


            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return productsPattern;
        }

    }

}