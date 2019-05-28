package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.helper.DaoHelpers;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.rowmapper.ProductRowMapper;

@Repository
public class ProductDao {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //  CREATE
    public void create(Product product) throws SQLException{

        String sqlQuery = "insert into products (name , subcategory_id , sleeve_id , shape_id , collar_id , height_id , material_id , maker_id) "
                        +	"values (? , ? , ? , ? , ? , ? , ? , ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new product in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								product.getName(),
            				                product.getSubcategoryId(),
            				                product.getSleeveId(),
            				                product.getShapeId(),
            				                product.getCollarId(),
            				                product.getHeightId(),
            				                product.getMaterialId(),
            				                product.getMakerId()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
    }
    
    
    //  READ (List of Products)
    public List<Product> read(Product product) {

        String sqlQuery = "select subcategories.id as subcategory_id, "
        		+ "subcategories.label as subcategory_label, "
        		+ "sleeves.id as sleeve_id, "
        		+ "sleeves.label as sleeve_label, "
        		+ "shapes.id as shape_id, "
        		+ "shapes.label as shape_label, "
        		+ "collars.id as collar_id, "
        		+ "collars.label as collar_label, "
        		+ "heights.id as height_id, "
        		+ "heights.label as height_label, "
        		+ "materials.id as material_id, "
        		+ "materials.label as material_label, "
        		+ "makers.id as maker_id, "
        		+ "makers.label as maker_label, "
        		+ "products.id as id, "
        		+ "products.name as name "
        		+ "from products "
        		+ "inner join subcategories on products.subcategory_id = subcategories.id "
        		+ "inner join sleeves on products.sleeve_id = sleeves.id "
        		+ "inner join shapes on products.shape_id = shapes.id "
        		+ "inner join collars on products.collar_id = collars.id "
        		+ "inner join heights on products.height_id = heights.id "
        		+ "inner join materials on products.material_id = materials.id "
        		+ "inner join makers on products.maker_id = makers.id ";
        
        if (product.getSubcategoryId() != null)
        	sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products.subcategory_id", String.valueOf(product.getSubcategoryId()));
        
        if (product.getSleeveId() != null) 
        	sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products.sleeve_id", String.valueOf(product.getSleeveId()));
        
        if (product.getShapeId() != null)
        	sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products.shape_id", String.valueOf(product.getShapeId()));
        
        if (product.getCollarId() != null)
        	sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products.collar_id", String.valueOf(product.getCollarId()));
        
        if (product.getHeightId() != null)
        	sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products.height_id", String.valueOf(product.getHeightId()));
        
        if (product.getMaterialId() != null)
        	sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products.material_id", String.valueOf(product.getMaterialId()));
        
        if (product.getMakerId() != null)
        	sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products.maker_id", String.valueOf(product.getMakerId()));
        
        sqlQuery += " order by products.id asc;";
        
        try {
        	
        	return new ProductRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
            
        }
        
        return null;
        
    }


    //  READ (Single Category)
    public Product read(Long id) throws SQLException{
    	String sqlQuery = "select subcategories.id as subcategory_id, "
        		+ "subcategories.label as subcategory_label, "
        		+ "sleeves.id as sleeve_id, "
        		+ "sleeves.label as sleeve_label, "
        		+ "shapes.id as shape_id, "
        		+ "shapes.label as shape_label, "
        		+ "collars.id as collar_id, "
        		+ "collars.label as collar_label, "
        		+ "heights.id as height_id, "
        		+ "heights.label as height_label, "
        		+ "materials.id as material_id, "
        		+ "materials.label as material_label, "
        		+ "makers.id as maker_id, "
        		+ "makers.label as maker_label, "
        		+ "products.id, "
        		+ "products.name "
        		+ "from products "
        		+ "inner join subcategories on products.subcategory_id = subcategories.id "
        		+ "inner join sleeves on products.sleeve_id = sleeves.id "
        		+ "inner join shapes on products.shape_id = shapes.id "
        		+ "inner join collars on products.collar_id = collars.id "
        		+ "inner join heights on products.height_id = heights.id "
        		+ "inner join materials on products.material_id = materials.id "
        		+ "inner join makers on products.maker_id = makers.id "
        		+ "where products.id = ? "
        		+ "order by products.id asc;";
        
        return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new ProductRowMapper()
        	);
    }  

    
    //  UPDATE
    public void update(Product product) throws SQLException {
        
    	String sqlQuery = "update products "
    			+ "set name=?, "
    			+ "subcategory_id=?, "
    			+ "sleeve_id=?, "
    			+ "shape_id=?, "
    			+ "collar_id=?, "
    			+ "height_id=?, "
    			+ "material_id=?, "
    			+ "maker_id=? "
    			+ "where id=?;";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							product.getName(),
							product.getSubcategoryId(),
							product.getSleeveId(),
				            product.getShapeId(),
				            product.getCollarId(),
				            product.getHeightId(),
				            product.getMaterialId(),
				            product.getMakerId(),
				            product.getId()
						}
				);
            
            
            System.out.println("Product successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	String sqlQuery = "delete from products where id=?;";

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