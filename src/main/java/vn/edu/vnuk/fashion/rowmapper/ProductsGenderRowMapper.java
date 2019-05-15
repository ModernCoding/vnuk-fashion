package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.Category;
import vn.edu.vnuk.fashion.model.Collar;
import vn.edu.vnuk.fashion.model.Gender;
import vn.edu.vnuk.fashion.model.Height;
import vn.edu.vnuk.fashion.model.Maker;
import vn.edu.vnuk.fashion.model.Material;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsGender;
import vn.edu.vnuk.fashion.model.Shape;
import vn.edu.vnuk.fashion.model.Sleeve;
import vn.edu.vnuk.fashion.model.Subcategory;

public class ProductsGenderRowMapper implements RowMapper<ProductsGender> {

	@Override
	public ProductsGender mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		Category category = new Category();
		Subcategory subcategory = new Subcategory();
		Sleeve sleeve = new Sleeve();
		Shape shape = new Shape();
		Collar collar = new Collar();
		Height height = new Height();
		Material material = new Material();
		Maker maker = new Maker();
		Product product = new Product();
		Gender gender = new Gender();
		ProductsGender productsGender = new ProductsGender();
		
		
		category.setId(rs.getLong("category_id"));
		category.setLabel(rs.getString("category_label"));
		
		subcategory.setId(rs.getLong("subcategory_id"));
		subcategory.setCategoryId(rs.getLong("category_id"));
		subcategory.setLabel(rs.getString("subcategory_label"));
		subcategory.setCategory(category);
		
		sleeve.setId(rs.getLong("sleeve_id"));
		sleeve.setLabel(rs.getString("sleeve_label"));
		
		shape.setId(rs.getLong("shape_id"));
		shape.setLabel(rs.getString("shape_label"));
		
		collar.setId(rs.getLong("collar_id"));
		collar.setLabel(rs.getString("collar_label"));
		
		height.setId(rs.getLong("height_id"));
		height.setLabel(rs.getString("height_label"));
		
		material.setId(rs.getLong("material_id"));
		material.setLabel(rs.getString("material_label"));
		
		maker.setId(rs.getLong("maker_id"));
		maker.setLabel(rs.getString("maker_label"));
		
		product.setId(rs.getLong("product_id"));
		product.setName(rs.getString("name"));
		product.setSubcategoryId(rs.getLong("subcategory_id"));
		product.setSubcategory(subcategory);
		product.setSleeveId(rs.getLong("sleeve_id"));
		product.setSleeve(sleeve);
		product.setShapeId(rs.getLong("shape_id"));
		product.setShape(shape);
		product.setCollarId(rs.getLong("collar_id"));
		product.setCollar(collar);
		product.setHeightId(rs.getLong("height_id"));
		product.setHeight(height);
		product.setMaterialId(rs.getLong("material_id"));
		product.setMaterial(material);
		product.setMakerId(rs.getLong("maker_id"));
		product.setMaker(maker);
		
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
			
    		Category category = new Category();
    		Subcategory subcategory = new Subcategory();
    		Sleeve sleeve = new Sleeve();
    		Shape shape = new Shape();
    		Collar collar = new Collar();
    		Height height = new Height();
    		Material material = new Material();
    		Maker maker = new Maker();
    		Product product = new Product();
    		Gender gender = new Gender();
    		ProductsGender productsGender = new ProductsGender();
    		
    		category.setId((Long) row.get("category_id"));
    		category.setLabel((String) row.get("category_label"));
    		
    		subcategory.setId((Long) row.get("subcategory_id"));
    		subcategory.setCategoryId((Long) row.get("category_id"));
    		subcategory.setLabel((String) row.get("subcategory_label"));
    		subcategory.setCategory(category);
    		
    		sleeve.setId((Long) row.get("sleeve_id"));
    		sleeve.setLabel((String) row.get("sleeve_label"));
    		
    		shape.setId((Long) row.get("shape_id"));
    		shape.setLabel((String) row.get("shape_label"));
    		
    		collar.setId((Long) row.get("collar_id"));
    		collar.setLabel((String) row.get("collar_label"));
    		
    		height.setId((Long) row.get("height_id"));
    		height.setLabel((String) row.get("height_label"));
    		
    		material.setId((Long) row.get("material_id"));
    		material.setLabel((String) row.get("material_label"));
    		
    		maker.setId((Long) row.get("maker_id"));
    		maker.setLabel((String) row.get("maker_label"));
    		
    		product.setId((Long) row.get("product_id"));
    		product.setName((String) row.get("name"));
    		product.setSubcategoryId((Long) row.get("subcategory_id"));
    		product.setSubcategory(subcategory);
    		product.setSleeveId((Long) row.get("sleeve_id"));
    		product.setSleeve(sleeve);
    		product.setShapeId((Long) row.get("shape_id"));
    		product.setShape(shape);
    		product.setCollarId((Long) row.get("collar_id"));
    		product.setCollar(collar);
    		product.setHeightId((Long) row.get("height_id"));
    		product.setHeight(height);
    		product.setMaterialId((Long) row.get("material_id"));
    		product.setMaterial(material);
    		product.setMakerId((Long) row.get("maker_id"));
    		product.setMaker(maker);
    		
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
