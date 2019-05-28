package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.helper.DaoHelpers;
import vn.edu.vnuk.fashion.model.ProductsPattern;
import vn.edu.vnuk.fashion.rowmapper.ProductsPatternRowMapper;



@Repository
public class ProductsPatternDao {

	@Autowired
    private JdbcTemplate jdbcTemplate;

    //  CREATE
    public void create(ProductsPattern productsPattern) throws SQLException{

        String sqlQuery = "insert into products_patterns (product_id, pattern_id) "
                        +	"values (? , ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new productsPattern in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								productsPattern.getProductId(),
            								productsPattern.getPatternId()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of ProductsPatterns)
    public List<ProductsPattern> read(String productId , String patternId) throws SQLException {
    	
    	String sqlQuery = 
	  			  "select products_patterns.id, "
	  			+ "		products.id as product_id, "
	  			+ "		products.name as product_name, "
	  			+ "		patterns.id as pattern_id, "
	  			+ "		patterns.label as pattern_label "
				+ "from products_patterns "
				+ "inner join products on products_patterns.product_id = products.id "
				+ "inner join patterns on products_patterns.pattern_id = patterns.id ";
    	    	
    	if (productId != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products.id", productId);
    	
    	if (patternId != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "patterns.id", patternId);
    	
    	sqlQuery += " order by products_patterns.id asc, products.id asc, patterns.id asc;";
    	
    	
        try {
        	
        	return new ProductsPatternRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single ProductsPattern)
    public ProductsPattern read(Long id) throws SQLException{

    	String sqlQuery = 
    			  "select products_patterns.id, "
    			+ "		products.id as product_id, "
    			+ "		products.name as product_name, "
    			+ "		patterns.id as pattern_id, "
    			+ "		patterns.label as pattern_label "
				+ "from products_patterns "
				+ "inner join products on products_patterns.product_id = products.id "
				+ "inner join patterns on products_patterns.pattern_id = patterns.id "
				+ "where products_patterns.id = ? "
				+ "order by products_patterns.id asc, products.id asc, patterns.id asc;";

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new ProductsPatternRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(ProductsPattern productsPattern) throws SQLException {
        
    	String sqlQuery = "update products_patterns set product_id=?, pattern_id=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							productsPattern.getProductId(),
							productsPattern.getPatternId(),
							productsPattern.getId()
						}
				);
            
            
            System.out.println("ProductsPattern successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from products_patterns where id=?";

        try {

            System.out.println(
            		String.format(
            				"%s record successfully removed from DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {id}
        						)
        				)
        		);

        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}