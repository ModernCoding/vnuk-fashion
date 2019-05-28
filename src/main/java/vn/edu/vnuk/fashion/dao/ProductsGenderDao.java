package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.helper.DaoHelpers;
import vn.edu.vnuk.fashion.model.ProductsGender;
import vn.edu.vnuk.fashion.rowmapper.ProductsGenderRowMapper;

@Repository
public class ProductsGenderDao {

	@Autowired
    private JdbcTemplate jdbcTemplate;
    
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
            								productsGender.getProductId(),
            								productsGender.getGenderId()
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
    	
    	String sqlQuery = "select products_genders.id"
		    			+ "     , products.id as product_id"
		    			+ "     , products.name as product_name"
		    			+ "     , genders.id as gender_id"
		    			+ "     , genders.label as gender_label"
						+ "  from products_genders"
						+ "  inner join products on products_genders.product_id = products.id"
						+ "  inner join genders on products_genders.gender_id = genders.id ";
    	
		if (productId != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "product_id", productId);
    	
    	if (genderId != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "gender_id", genderId);
    	
    	sqlQuery += " order by products_genders.id asc, products.id asc, genders.id asc;";
    	
    	
        try {
        	
        	return new ProductsGenderRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single ProductsGender)
    public ProductsGender read(Long id) throws SQLException{

    	String sqlQuery = "select products_genders.id"
    			+ "     , products.id as product_id"
    			+ "     , products.name as product_name"
    			+ "     , genders.id as gender_id"
    			+ "     , genders.label as gender_label"
				+ "  from products_genders"
				+ "  inner join products on products_genders.product_id = products.id"
				+ "  inner join genders on products_genders.gender_id = genders.id "
				+ "  where products_genders.id = ?;";

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
							productsGender.getProductId(),
							productsGender.getGenderId(),
							productsGender.getId()
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