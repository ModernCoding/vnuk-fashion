package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Category;
import vn.edu.vnuk.fashion.model.Color;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsColor;
import vn.edu.vnuk.fashion.model.Subcategory;

public class ProductsColorDao {
	
    private Connection connection;

    public ProductsColorDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public ProductsColorDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(ProductsColor productsColor) throws SQLException{

        String sqlQuery = "insert into products_colors (product_id, color_id) "
                        +	"values (? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, productsColor.getProduct().getId());
                statement.setLong(2, productsColor.getColor().getId());

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
    
    
    //  READ (List of ProductsColor)
    @SuppressWarnings("finally")
    public List<ProductsColor> read() throws SQLException {

        String sqlQuery = "select * from products_colors";
        PreparedStatement statement;
        List<ProductsColor> productsColors = new ArrayList<ProductsColor>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	ProductsColor productsColor = new ProductsColor();
                
            	productsColor.setId(results.getLong("id"));
                
                // Process foreign key
                Long productIdFromDB = results.getLong("product_id");
                Long colorIdFromDB = results.getLong("color_id");
                
                ProductDao productDao = new ProductDao();
                ColorDao colorDao = new ColorDao();
                
                Product product = productDao.read(productIdFromDB);
                Color color = colorDao.read(colorIdFromDB);
                
                productsColor.setProduct(product);
                productsColor.setColor(color);

                productsColors.add(productsColor);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return productsColors;
        }


    }


    //  READ (Single ProductsColor)
    public ProductsColor read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(ProductsColor productsColor) throws SQLException {
        String sqlQuery = "update products_color product_id=? color_id=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, productsColor.getProduct().getId());
            statement.setLong(2, productsColor.getColor().getId());
            
            statement.execute();
            statement.close();
            
            System.out.println("products_colors successfully modified.");
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
        String sqlQuery = "delete from products_colors where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("products_colors successfully deleted.");

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
    private ProductsColor read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from products_colors where id=?";

        PreparedStatement statement;
        ProductsColor productsColor = new ProductsColor();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

            	productsColor.setId(results.getLong("id"));
                
                // Process foreign key
                Long productIdFromDB = results.getLong("product_id");
                Long colorIdFromDB = results.getLong("color_id");
                
                ProductDao productDao = new ProductDao();
                ColorDao colorDao = new ColorDao();
                
                Product product = productDao.read(productIdFromDB);
                Color color = colorDao.read(colorIdFromDB);
                
                productsColor.setProduct(product);
                productsColor.setColor(color);


            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return productsColor;
        }

    }

}