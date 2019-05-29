package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.helper.DaoHelpers;
import vn.edu.vnuk.fashion.model.ProductsLength;
import vn.edu.vnuk.fashion.rowmapper.ProductsLengthRowMapper;





@Repository
public class ProductsLengthDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ProductsLengthDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(ProductsLength  productsLength) throws SQLException{

        String sqlQuery = "insert into products_lengths (product_id, length_id) "
                        +	"values (? , ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new productsLength in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								productsLength.getLengthId(),
            								productsLength.getProductId()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of ProductsLengths)
    public List<ProductsLength> read(ProductsLength productsLength) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.product_id"
		    			+ "     , t01.length_id"
		    			+ "     , t03.label as length_label"
		    			+ "     , t02.name as product_name "
						+ "from products_lengths t01 "
						+ "inner join products t02 on t01.product_id = t02.id "
						+ "inner join lengths t03 on t01.length_id = t03.id ";
    	
    	if (productsLength.getProductId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "t02.id", String.valueOf(productsLength.getProductId()));
    	
    	if (productsLength.getLengthId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "t03.id", String.valueOf(productsLength.getLengthId()));
    	
    	sqlQuery += " order by t01.id asc, t02.id asc, t03.id asc;";
    	
    	
        try {
        	
        	return new ProductsLengthRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single ProductsLength)
    public ProductsLength read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t01.product_id"
    			+ "     , t01.length_id"
    			+ "     , t03.label as length_label"
    			+ "     , t02.name as product_name "
				+ "from products_lengths t01 "
				+ "inner join products t02 on t01.product_id = t02.id "
				+ "inner join lengths t03 on t01.length_id = t03.id "
				+ "where t01.id = ? "
				+ "order by t01.id asc, t02.id asc, t03.id asc;"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new ProductsLengthRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(ProductsLength productsLength) throws SQLException {
        
    	String sqlQuery = "update products_lengths set product_id=?, length_id=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							productsLength.getId(),
							productsLength.getProductId(),
							productsLength.getLengthId()
						}
				);
            
            
            System.out.println("ProductsLength successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from products_lengths where id=?";

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