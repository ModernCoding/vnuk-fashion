package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.helper.DaoHelpers;
import vn.edu.vnuk.fashion.model.ProductsColor;
import vn.edu.vnuk.fashion.rowmapper.ProductsColorRowMapper;




@Repository
public class ProductsColorDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

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
            								productsColor.getProductId(),
            								productsColor.getColorId()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of ProductsColors)
    public List<ProductsColor> read(ProductsColor productsColor) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.color_id"
		    			+ "		, t01.product_id"
		    			+ "		, t02.name as product_name"
		    			+ "     , t03.label as color_label "
						+ "from products_colors t01 "
						+ "inner join products t02 on t01.product_id = t02.id "
						+ "inner join colors t03 on t01.color_id = t03.id ";
    	
    	if (productsColor.getProductId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "t02.id", String.valueOf(productsColor.getProductId()));
    	
    	if (productsColor.getColorId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "t03.id", String.valueOf(productsColor.getColorId()));
    	
    	sqlQuery += " order by t01.id asc, t02.id asc, t03.id asc;";
    	
    	
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
    			+ "     , t01.color_id"
    			+ "		, t01.product_id"
    			+ "		, t02.name as product_name"
    			+ "     , t03.label as color_label "
				+ "from products_colors t01 "
				+ "inner join products t02 on t01.product_id = t02.id "
				+ "inner join colors t03 on t01.color_id = t03.id "
				+ "where t01.id = ? "
				+ "order by t01.id asc, t02.id asc, t03.id asc;";
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
							productsColor.getProductId(),
							productsColor.getColorId(),
							productsColor.getId()
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