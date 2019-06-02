  package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.helper.DaoHelpers;
import vn.edu.vnuk.fashion.model.Price;
import vn.edu.vnuk.fashion.rowmapper.PriceRowMapper;

@Repository
public class PriceDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    //  CREATE
    public void create(Price price) throws SQLException{

        String sqlQuery = "insert into prices (products_size_id, "
        		+ "products_color_id, "
        		+ "products_pattern_id, "
        		+ "products_length_id, "
        		+ "seller_id, "
        		+ "price_type_id, "
        		+ "value) "
                + "values (?, ?, ?, ?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new customer in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								price.getProductsSizeId(),
            								price.getProductsColorId(),
            								price.getProductsPatternId(),
            								price.getProductsLengthId(),
            								price.getSellerId(),
            								price.getPriceTypeId(),
            								price.getValue()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
    }
    
    
    //  READ (List of Tasks)
    public List<Price> read(Price price) throws SQLException {
    	
    	String sqlQuery = "select prices.id as id"
    			+ "		, prices.value as value"
    			+ "		, products_patterns.id as product_pattern_id"
    			+ "		, products_colors.id as product_color_id"
    			+ "		, products_lengths.id as product_length_id"
    			+ "		, products_sizes.id as product_size_id"
    			+ "		, price_types.id as price_type_id"
    			+ "		, price_types.label as price_type_label"
    			+ "		, sellers.id as seller_id"
    			+ "		, sellers.label as seller_label "
				+ "from prices "
				+ "inner join products_patterns on prices.products_pattern_id = products_patterns.id "
				+ "inner join products_colors on prices.products_color_id = products_colors.id "
				+ "inner join products_lengths on prices.products_length_id = products_lengths.id "
				+ "inner join products_sizes on prices.products_size_id = products_sizes.id "
				+ "inner join price_types on prices.price_type_id = price_types.id "
				+ "inner join sellers on prices.seller_id = sellers.id ";
    	
    	if (price.getProductsPatternId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products_patterns.id", String.valueOf(price.getProductsPatternId()));
    	
    	if (price.getProductsColorId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products_colors.id", String.valueOf(price.getProductsColorId()));
    	
    	if (price.getProductsLengthId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products_lengths.id", String.valueOf(price.getProductsLengthId()));
    	
    	if (price.getProductsSizeId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "products_sizes.id", String.valueOf(price.getProductsSizeId()));
    	
    	if (price.getPriceTypeId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "price_types.id", String.valueOf(price.getPriceTypeId()));
    	
    	if (price.getSellerId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "sellers.id", String.valueOf(price.getSellerId()));
    	
    	sqlQuery += " order by prices.id asc, "
    			+ "products_patterns.id asc, "
    			+ "products_colors.id asc, "
    			+ "products_lengths.id asc, "
    			+ "products_sizes.id asc,"
    			+ "price_types.id asc,"
    			+ "sellers.id asc;";
    	
    	
        try {
        	
        	return new PriceRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		return null;
    }


    //  READ (Single Price)
    public Price read(Long id) throws SQLException{
    	String sqlQuery = "select prices.id as id"
    			+ "		, prices.value as value"
    			+ "		, products_patterns.id as product_pattern_id"
    			+ "		, products_colors.id as product_color_id"
    			+ "		, products_lengths.id as product_length_id"
    			+ "		, products_sizes.id as product_size_id"
    			+ "		, price_types.id as price_type_id"
    			+ "		, price_types.label as price_type_label"
    			+ "		, sellers.id as seller_id"
    			+ "		, sellers.label as seller_label "
				+ "from prices "
				+ "inner join products_patterns on prices.products_pattern_id = products_patterns.id "
				+ "inner join products_colors on prices.products_color_id = products_colors.id "
				+ "inner join products_lengths on prices.products_length_id = products_lengths.id "
				+ "inner join products_sizes on prices.products_size_id = products_sizes.id "
				+ "inner join price_types on prices.price_type_id = price_types.id "
				+ "inner join sellers on prices.seller_id = sellers.id "
				+ "where prices.id = ?;";
    	
    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new PriceRowMapper()
        	);
    }  
    
    //  UPDATE
    public void update(Price price) throws SQLException {
        String sqlQuery = "update prices set "
        		+ "products_size_id=?, "
        		+ "products_color_id=?, "
        		+ "products_pattern_id=?, "
        		+ "products_length_id=?, "
        		+ "seller_id=?, "
        		+ "price_type_id=?, "
        		+ "value=? "
        		+ "where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							price.getProductsSizeId(),
							price.getProductsColorId(),
							price.getProductsPatternId(),
							price.getProductsLengthId(),
							price.getSellerId(),
							price.getPriceTypeId(),
							price.getValue(),
							price.getId()
						}
				);
            
            
            System.out.println("Price successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	String sqlQuery = "delete from prices where id=?";

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