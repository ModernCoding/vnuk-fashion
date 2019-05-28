package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.helper.DaoHelpers;
import vn.edu.vnuk.fashion.model.ProductsSize;
import vn.edu.vnuk.fashion.rowmapper.ProductsSizeRowMapper;

@Repository
public class ProductsSizeDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
    

    //  CREATE
    public void create(ProductsSize  productsSize) throws SQLException{

        String sqlQuery = "insert into products_sizes (product_id, size_id) "
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
    	
    	String sqlQuery = "select products.id as product_id,"
    			+ "	products.name as product_name, "
    			+ " sizes.id as size_id, "
    			+ " sizes.universal as size_universal, "
    			+ " products_sizes.id as id "
    			+ "from products_sizes "
    			+ "inner join products on products_sizes.product_id = products.id "
    			+ "inner join sizes on products_sizes.size_id = sizes.id ";
    	
    	if (productId != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "product_id", productId);
    	
    	if (sizeId != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "size_id", sizeId);
    	
    	sqlQuery += " order by products_sizes.id asc, products.id asc, sizes.id asc;";
    	
        try {
        	
        	return new ProductsSizeRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }

    //  READ (Single ProductsSize)
    public ProductsSize read(Long id) throws SQLException{

    	String sqlQuery = "select p.id as product_id,"
    			+ "	p.name as product_name, "
    			+ " s.id as size_id, "
    			+ " s.universal as size_universal, "
    			+ " ps.id as id "
    			+ "from products_sizes ps "
    			+ "inner join products p on ps.product_id = p.id "
    			+ "inner join sizes s on ps.size_id = s.id "
    			+ "where ps.id=?;";

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new ProductsSizeRowMapper()
        	);
    }  
    
    //  UPDATE
    public void update(ProductsSize productsSize) throws SQLException {
        
    	String sqlQuery = "update products_sizes set product_id=?, size_id=? where id=?;";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							productsSize.getProductId(),
							productsSize.getSizeId(),
							productsSize.getId()
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
        
    	String sqlQuery = "delete from products_sizes where id=?";

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
            e.printStackTrace();
        }

    }

}