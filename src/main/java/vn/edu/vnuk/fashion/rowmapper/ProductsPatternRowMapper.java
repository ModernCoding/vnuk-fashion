package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.Pattern;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsPattern;

public class ProductsPatternRowMapper implements RowMapper<ProductsPattern> {

	@Override
	public ProductsPattern mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Product product = new Product();
		Pattern pattern = new Pattern();
		ProductsPattern productsPattern = new ProductsPattern();
				
		product.setId(rs.getLong("product_id"));
		product.setName(rs.getString("product_name"));
		
		pattern.setId(rs.getLong("pattern_id"));
		pattern.setLabel(rs.getString("pattern_label"));
		
		productsPattern.setId(rs.getLong("id"));
		productsPattern.setPatternId(rs.getLong("pattern_id"));
		productsPattern.setPattern(pattern);
		productsPattern.setProductId(rs.getLong("product_id"));
		productsPattern.setProduct(product);
		
		return productsPattern;
	}
	
	
	public List<ProductsPattern> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<ProductsPattern> productsPatterns = new ArrayList<ProductsPattern>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Product product = new Product();
			Pattern pattern = new Pattern();
			ProductsPattern productsPattern = new ProductsPattern();
			
    		
    		product.setId((Long) row.get("product_id"));
    		product.setName((String) row.get("product_name"));
    		
    		pattern.setId((Long) row.get("pattern_id"));
    		pattern.setLabel((String) row.get("pattern_label"));
    		
    		productsPattern.setId((Long) row.get("id"));
    		productsPattern.setPatternId((Long) row.get("pattern_id"));
    		productsPattern.setPattern(pattern);
    		productsPattern.setProductId((Long) row.get("product_id"));
    		productsPattern.setProduct(product);
			
    		productsPatterns.add(productsPattern);
			
		}
    	
		return productsPatterns;

	}

}
