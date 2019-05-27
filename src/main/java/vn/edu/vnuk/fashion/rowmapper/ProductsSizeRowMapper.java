package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsSize;
import vn.edu.vnuk.fashion.model.Size;

public class ProductsSizeRowMapper implements RowMapper<ProductsSize> {

	@Override
public ProductsSize mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Product product = new Product();
		Size size = new Size();
		ProductsSize productsSize = new ProductsSize();
		
		product.setId(rs.getLong("product_id"));
		product.setName(rs.getString("product_name"));
		
		size.setId(rs.getLong("size_id"));
		size.setUniversal(rs.getString("size_universal"));
		
		productsSize.setId(rs.getLong("id"));
		productsSize.setProductId(rs.getLong("product_id"));
		productsSize.setProduct(product);
		productsSize.setSizeId(rs.getLong("size_id"));
		productsSize.setSize(size);
		
		return productsSize;
	}
	
	
	public List<ProductsSize> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<ProductsSize> productsSizes = new ArrayList<ProductsSize>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Product product = new Product();
    		Size size = new Size();
    		ProductsSize productsSize = new ProductsSize();
    		
    		product.setId((Long) row.get("product_id"));
    		product.setName((String) row.get("product_name"));
    		
    		size.setId((Long) row.get("size_id"));
    		size.setUniversal((String) row.get("size_universal"));
    		
    		productsSize.setId((Long) row.get("id"));
    		productsSize.setProductId((Long) row.get("product_id"));
    		productsSize.setProduct(product);
    		productsSize.setSizeId((Long) row.get("size_id"));
    		productsSize.setSize(size);
    		    		
    		productsSizes.add(productsSize);
			
		}		
    	
		return productsSizes;
	}

}
