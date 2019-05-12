package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Price;
import vn.edu.vnuk.fashion.model.PriceType;
import vn.edu.vnuk.fashion.model.ProductsColor;
import vn.edu.vnuk.fashion.model.ProductsLength;
import vn.edu.vnuk.fashion.model.ProductsPattern;
import vn.edu.vnuk.fashion.model.ProductsSize;
import vn.edu.vnuk.fashion.model.Seller;

public class PriceDao {
	
    private Connection connection;

    public PriceDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public PriceDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Price price) throws SQLException{

        String sqlQuery = "insert into subcategories (products_size_id, products_color_id, products_pattern_id, products_lenght_id, seller_id, price_type_id, value) "
                        +	"values (? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, price.getProductsSize().getId());
                statement.setLong(2, price.getProductsColor().getId());
                statement.setLong(3, price.getProductsPattern().getId());
                statement.setLong(4, price.getProductsLength().getId());
                statement.setLong(5, price.getPriceType().getId());
                statement.setFloat(6, price.getValue());

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
    public List<Price> read() throws SQLException {

        String sqlQuery = "select * from prices";
        PreparedStatement statement;
        List<Price> prices = new ArrayList<Price>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	Price price = new Price();
                
                price.setValue(results.getFloat("value"));
                
                // Process foreign key
                Long productsSizeIdFromDb = results.getLong("products_size_id");
                Long productsColorIdFromDb = results.getLong("products_color_id");
                Long productsPatternIdFromDb = results.getLong("products_pattern_id");
                Long productsLengthIdFromDb = results.getLong("products_length_id");
                Long sellerIdFromDb = results.getLong("seller_id");
                Long priceTypeIdFromDb = results.getLong("price_type_id");
                
                ProductsSizeDao productsSizeDao = new ProductsSizeDao();
                ProductsColorDao productsColorDao = new ProductsColorDao();
                ProductsPatternDao productsPatternDao = new ProductsPatternDao();
                ProductsLengthDao productsLengthDao = new ProductsLengthDao();
                SellerDao sellerDao = new SellerDao();
                PriceTypeDao priceTypeDao = new PriceTypeDao();
                
                ProductsSize productsSize = productsSizeDao.read(productsSizeIdFromDb);
                ProductsColor productsColor = productsColorDao.read(productsColorIdFromDb);
                ProductsPattern productsPattern = productsPatternDao.read(productsPatternIdFromDb);
                ProductsLength productsLength = productsLengthDao.read(productsLengthIdFromDb);
                Seller seller = sellerDao.read(sellerIdFromDb);
                PriceType priceType = priceTypeDao.read(priceTypeIdFromDb);
                 
                price.setProductsSize(productsSize);
                price.setProductsColor(productsColor);
                price.setProductsPattern(productsPattern);
                price.setProductsLength(productsLength);
                price.setPriceType(priceType);
                price.setSeller(seller);
                
                prices.add(price);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return prices;
        }


    }


    //  READ (Single Price)
    public Price read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Price price) throws SQLException {
        String sqlQuery = "update subcategories products_size_id=? products_color_id=? products_pattern_id=? products_length_id=? seller_id=? price_type_id=? value=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, price.getProductsSize().getId());
            statement.setLong(2, price.getProductsColor().getId());
            statement.setLong(3, price.getProductsPattern().getId());
            statement.setLong(4, price.getProductsLength().getId());
            statement.setLong(5, price.getPriceType().getId());
            statement.setFloat(6, price.getValue());
            
            statement.execute();
            statement.close();
            
            System.out.println("Price successfully modified.");
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
        String sqlQuery = "delete from prices where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Price successfully deleted.");

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
    private Price read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from prices where id=?";

        PreparedStatement statement;
        Price price = new Price();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

            	price.setValue(results.getFloat("value"));
                
                // Process foreign key
            	 Long productsSizeIdFromDb = results.getLong("products_size_id");
                 Long productsColorIdFromDb = results.getLong("products_color_id");
                 Long productsPatternIdFromDb = results.getLong("products_pattern_id");
                 Long productsLengthIdFromDb = results.getLong("products_length_id");
                 Long sellerIdFromDb = results.getLong("seller_id");
                 Long priceTypeIdFromDb = results.getLong("price_type_id");
                 
                 ProductsSizeDao productsSizeDao = new ProductsSizeDao();
                 ProductsColorDao productsColorDao = new ProductsColorDao();
                 ProductsPatternDao productsPatternDao = new ProductsPatternDao();
                 ProductsLengthDao productsLengthDao = new ProductsLengthDao();
                 SellerDao sellerDao = new SellerDao();
                 PriceTypeDao priceTypeDao = new PriceTypeDao();
                 
                 ProductsSize productsSize = productsSizeDao.read(productsSizeIdFromDb);
                 ProductsColor productsColor = productsColorDao.read(productsColorIdFromDb);
                 ProductsPattern productsPattern = productsPatternDao.read(productsPatternIdFromDb);
                 ProductsLength productsLength = productsLengthDao.read(productsLengthIdFromDb);
                 Seller seller = sellerDao.read(sellerIdFromDb);
                 PriceType priceType = priceTypeDao.read(priceTypeIdFromDb);
                 
                 price.setProductsSize(productsSize);
                 price.setProductsColor(productsColor);
                 price.setProductsPattern(productsPattern);
                 price.setProductsLength(productsLength);
                 price.setPriceType(priceType);
                 price.setSeller(seller);


            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return price;
        }

    }

}