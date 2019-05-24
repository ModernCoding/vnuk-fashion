package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.ProductsPattern;
import vn.edu.vnuk.fashion.rowmapper.ProductsPatternRowMapper;



@Repository
public class ProductsPatternDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ProductsPatternDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


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
            								productsPattern.getPatternId(),
            								productsPattern.getProductId()
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
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t02.id as product_id"
		    			+ "     , t02.name"
		    			+ "     , t02.subcategory_id"
		    			+ "     , t02.sleeve_id"
		    			+ "     , t02.shape_id"
		    			+ "     , t02.collar_id"
		    			+ "     , t02.height_id"
		    			+ "     , t02.material_id"
		    			+ "     , t02.maker_id"
		    			+ "     , t03.id as pattern_id"
		    			+ "     , t03.label"
						+ "  from products_patterns t01, products t02, patterns t03"

						+ " where t02.id = t01.product_id"
						+ "and t03.id = t01.pattern_id"
				;
    	
    	if (productId != null && patternId != null) {
    		sqlQuery += String.format("   and t02.id = %s", productId, "   and t03.id = %s", patternId );
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t03.id asc, t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new ProductsPatternRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single ProductsPattern)
    public ProductsPattern read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as product_id"
    			+ "     , t02.name"
    			+ "     , t02.subcategory_id"
    			+ "     , t02.sleeve_id"
    			+ "     , t02.shape_id"
    			+ "     , t02.collar_id"
    			+ "     , t02.height_id"
    			+ "     , t02.material_id"
    			+ "     , t02.maker_id"
    			+ "     , t03.id as pattern_id"
    			+ "     , t03.id label"
				+ "  from products_patterns t01, products t02, patterns t03"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.product_id"
				+ "   and t03.id = t01.pattern_id"	
				+ " order by t01.id asc, t02.id asc, t01.id asc"
				+ ";"
		;

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
							productsPattern.getId(),
							productsPattern.getPatternId(),
							productsPattern.getProductId()
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