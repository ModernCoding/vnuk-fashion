package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.Gender;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsGender;
public class ProductsGenderRowMapper implements RowMapper<ProductsGender> {

	@Override
	public ProductsGender mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Product product = new Product();
		Gender gender = new Gender();
		ProductsGender productsGender = new ProductsGender();
				
		product.setId(rs.getLong("product_id"));
		product.setName(rs.getString("product_name"));
		
		gender.setId(rs.getLong("gender_id"));
		gender.setLabel(rs.getString("gender_label"));
		
		productsGender.setId(rs.getLong("id"));
		productsGender.setGenderId(rs.getLong("gender_id"));
		productsGender.setGender(gender);
		productsGender.setProductId(rs.getLong("product_id"));
		productsGender.setProduct(product);

	
		return productsGender;
	}
	
	
	public List<ProductsGender> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<ProductsGender> productsGenders = new ArrayList<ProductsGender>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Product product = new Product();
    		Gender gender = new Gender();
    		ProductsGender productsGender = new ProductsGender();
    		
    		product.setId((Long) row.get("product_id"));
    		product.setName((String) row.get("product_name"));
    		
    		gender.setId((Long) row.get("gender_id"));
    		gender.setLabel((String) row.get("gender_label"));
    		
    		productsGender.setId((Long) row.get("id"));
    		productsGender.setGenderId((Long) row.get("gender_id"));
    		productsGender.setGender(gender);
    		productsGender.setProductId((Long) row.get("product_id"));
    		productsGender.setProduct(product);
			
    		productsGenders.add(productsGender);
			
		}
		
		return productsGenders;

	}

}
