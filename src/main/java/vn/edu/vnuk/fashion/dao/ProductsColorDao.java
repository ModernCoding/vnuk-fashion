package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.ProductsColor;
import vn.edu.vnuk.fashion.rowmapper.ProductsColorRowMapper;




@Repository
public class ProductsColorDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ProductsColorDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(ProductsColor  productsColor) throws SQLException{

        String sqlQuery = "insert into products_colors (product_id, color_id) "
                        +	"values (? , ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new productsColor in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								productsColor.getColorId(),
            								productsColor.getProductId()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of ProductsColors)
    public List<ProductsColor> read(String productId , String colorId) throws SQLException {
    	
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
		    			+ "     , t03.id as color_id"
		    			+ "     , t03.label"
						+ "  from products_colors t01, products t02, colors t03"

						+ " where t02.id = t01.product_id"
						+ "and t03.id = t01.color_id"
				;
    	
    	if (productId != null && colorId != null) {
    		sqlQuery += String.format("   and t02.id = %s", productId, "   and t03.id = %s", colorId );
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t03.id asc, t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new ProductsColorRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single ProductsColor)
    public ProductsColor read(Long id) throws SQLException{

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
    			+ "     , t03.id as color_id"
    			+ "     , t03.label"
				+ "  from products_colors t01, products t02, colors t03"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.product_id"
				+ "   and t03.id = t01.color_id"	
				+ " order by t01.id asc, t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new ProductsColorRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(ProductsColor productsColor) throws SQLException {
        
    	String sqlQuery = "update products_colors set product_id=?, gender_id=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							productsColor.getId(),
							productsColor.getProductId(),
							productsColor.getColorId()
						}
				);
            
            
            System.out.println("ProductsColor successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from products_colors where id=?";

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