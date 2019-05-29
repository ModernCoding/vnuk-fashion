package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.Category;
import vn.edu.vnuk.fashion.model.Collar;
import vn.edu.vnuk.fashion.model.Height;
import vn.edu.vnuk.fashion.model.Length;
import vn.edu.vnuk.fashion.model.Maker;
import vn.edu.vnuk.fashion.model.Material;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsLength;
import vn.edu.vnuk.fashion.model.Shape;
import vn.edu.vnuk.fashion.model.Sleeve;
import vn.edu.vnuk.fashion.model.Subcategory;

public class ProductsLengthRowMapper implements RowMapper<ProductsLength> {

	@Override
	public ProductsLength mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Length length = new Length();
		Product product = new Product();
		ProductsLength productsLength = new ProductsLength();
		
		length.setId(rs.getLong("length_id"));
		length.setLabel(rs.getString("length_label"));
		
		product.setId(rs.getLong("product_id"));
		product.setName(rs.getString("product_name"));
		
		productsLength.setId(rs.getLong("id"));
		productsLength.setProductId(rs.getLong("product_id"));
		productsLength.setProduct(product);
		productsLength.setLengthId(rs.getLong("length_id"));
		productsLength.setLength(length);

		return productsLength;
	}
	
	
	public List<ProductsLength> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<ProductsLength> productsLengths = new ArrayList<ProductsLength>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Length length = new Length();
    		Product product = new Product();
    		ProductsLength productsLength = new ProductsLength();
    		
    		length.setId((Long) row.get("length_id"));
    		length.setLabel((String) row.get("length_label"));
    		
    		
    		product.setId((Long) row.get("product_id"));
    		product.setName((String) row.get("product_name"));
    		
    		productsLength.setId((Long) row.get("id"));
    		productsLength.setProductId((Long) row.get("product_id"));
    		productsLength.setProduct(product);
    		productsLength.setLengthId((Long) row.get("length_id"));
    		productsLength.setLength(length);
			
			productsLengths.add(productsLength);
			
		}
		
    	
		return productsLengths;

	}

}
