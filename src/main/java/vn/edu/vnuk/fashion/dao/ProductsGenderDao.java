package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.ProductsGender;
import vn.edu.vnuk.fashion.rowmapper.ProductsGenderRowMapper;




@Repository
public class ProductsGenderDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ProductsGenderDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(ProductsGender  productsGender) throws SQLException{

        String sqlQuery = "insert into products_genders (product_id, gender_id) "
                        +	"values (? , ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new productsGender in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								productsGender.getGenderId(),
            								productsGender.getProductId()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of ProductsGenders)
    public List<ProductsGender> read(String productId , String genderId) throws SQLException {
    	
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
		    			+ "     , t03.id as gender_id"
		    			+ "     , t03.label"
						+ "  from products_genders t01, products t02, genders t03"

						+ " where t02.id = t01.product_id"
						+ "and t03.id = t01.gender_id"
				;
    	
    	if (productId != null && genderId != null) {
    		sqlQuery += String.format("   and t02.id = %s", productId, "   and t03.id = %s", genderId );
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t03.id asc, t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new ProductsGenderRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single ProductsGender)
    public ProductsGender read(Long id) throws SQLException{

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
    			+ "     , t03.id as gender_id"
    			+ "     , t03.label"
				+ "  from products_genders t01, products t02, genders t03"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.product_id"
				+ "   and t03.id = t01.gender_id"	
				+ " order by t01.id asc, t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new ProductsGenderRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(ProductsGender productsGender) throws SQLException {
        
    	String sqlQuery = "update products_genders set product_id=?, gender_id=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							productsGender.getId(),
							productsGender.getProductId(),
							productsGender.getGenderId()
						}
				);
            
            
            System.out.println("ProductsGender successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from products_genders where id=?";

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