package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.Color;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsColor;

public class ProductsColorRowMapper implements RowMapper<ProductsColor> {

	@Override
	public ProductsColor mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Color color = new Color();
		
		Product product = new Product();
		ProductsColor productsColor = new ProductsColor();
		
		color.setId(rs.getLong("color_id"));
		color.setLabel(rs.getString("color_label"));

		product.setId(rs.getLong("product_id"));
		product.setName(rs.getString("product_name"));
		
		productsColor.setId(rs.getLong("id"));
		productsColor.setProductId(rs.getLong("product_id"));
		productsColor.setProduct(product);
		productsColor.setColorId(rs.getLong("color_id"));
		productsColor.setColor(color);
		
		return productsColor;
	}
	
	
	public List<ProductsColor> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<ProductsColor> productsColors = new ArrayList<ProductsColor>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Color color = new Color();
    		Product product = new Product();
    		ProductsColor productsColor = new ProductsColor();
    		
    		color.setId((Long) row.get("color_id"));
    		color.setLabel((String) row.get("color_label"));    		
    		
    		product.setId((Long) row.get("product_id"));
    		product.setName((String) row.get("product_name"));
    		
    		productsColor.setId((Long) row.get("id"));
    		productsColor.setProductId((Long) row.get("product_id"));
    		productsColor.setProduct(product);
    		productsColor.setColorId((Long) row.get("color_id"));
    		productsColor.setColor(color);
			
    		productsColors.add(productsColor);
			
		}
		
    	
		return productsColors;

	}

}
