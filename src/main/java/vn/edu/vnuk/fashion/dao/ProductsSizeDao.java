package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.ProductsSize;
import vn.edu.vnuk.fashion.rowmapper.ProductsSizeRowMapper;



@Repository
public class ProductsSizeDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ProductsSizeDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(ProductsSize  productsSize) throws SQLException{

        String sqlQuery = "insert into productsSizes (product_id, size_id) "
                        +	"values (? , ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new productsSize in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								productsSize.getSizeId(),
            								productsSize.getProductId()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of ProductsSizes)
    public List<ProductsSize> read(String productId , String sizeId) throws SQLException {
    	
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
		    			+ "     , t03.id as size_id"
		    			+ "     , t03.universal"
		    			+ "     , t03.us"
		    			+ "     , t03.uk"
		    			+ "     , t03.france"
		    			+ "     , t03.italy"
		    			+ "     , t03.germany"
		    			+ "     , t03.australia"
		    			+ "     , t03.japan"
						+ "  from productsSizes t01, products t02, sizes t03"

						+ " where t02.id = t01.product_id"
						+ "and t03.id = t01.size_id"
				;
    	
    	if (productId != null && sizeId != null) {
    		sqlQuery += String.format("   and t02.id = %s", productId, "   and t03.id = %s", sizeId );
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t03.id asc, t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new ProductsSizeRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single ProductsSize)
    public ProductsSize read(Long id) throws SQLException{

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
    			+ "     , t03.id as size_id"
    			+ "     , t03.universal"
    			+ "     , t03.us"
    			+ "     , t03.uk"
    			+ "     , t03.france"
    			+ "     , t03.italy"
    			+ "     , t03.germany"
    			+ "     , t03.australia"
    			+ "     , t03.japan"
				+ "  from productsPatterns t01, products t02, sizes t03"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.product_id"
				+ "   and t03.id = t01.size_id"	
				+ " order by t01.id asc, t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new ProductsSizeRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(ProductsSize productsSize) throws SQLException {
        
    	String sqlQuery = "update productsSizes set product_id=?, size_id=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							productsSize.getId(),
							productsSize.getProductId(),
							productsSize.getSizeId()
						}
				);
            
            
            System.out.println("ProductsSize successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from productsSizes where id=?";

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