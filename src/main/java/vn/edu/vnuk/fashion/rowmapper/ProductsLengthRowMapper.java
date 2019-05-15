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
		Category category = new Category();
		Subcategory subcategory = new Subcategory();
		Sleeve sleeve = new Sleeve();
		Shape shape = new Shape();
		Collar collar = new Collar();
		Height height = new Height();
		Material material = new Material();
		Maker maker = new Maker();
		Product product = new Product();
		ProductsLength productsLength = new ProductsLength();
		
		length.setId(rs.getLong("length_id"));
		length.setLabel(rs.getString("length_label"));
		
		category.setId(rs.getLong("category_id"));
		category.setLabel(rs.getString("category_label"));
		
		subcategory.setId(rs.getLong("subcategory_id"));
		subcategory.setCategoryId(rs.getLong("category_id"));
		subcategory.setLabel(rs.getString("label"));
		subcategory.setCategory(category);
		
		sleeve.setId(rs.getLong("sleeve_id"));
		sleeve.setLabel(rs.getString("label"));
		
		shape.setId(rs.getLong("shape_id"));
		shape.setLabel(rs.getString("label"));
		
		collar.setId(rs.getLong("collar_id"));
		collar.setLabel(rs.getString("label"));
		
		height.setId(rs.getLong("height_id"));
		height.setLabel(rs.getString("label"));
		
		material.setId(rs.getLong("material_id"));
		material.setLabel(rs.getString("label"));
		
		maker.setId(rs.getLong("maker_id"));
		maker.setLabel(rs.getString("label"));
		
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
    		Category category = new Category();
    		Subcategory subcategory = new Subcategory();
    		Sleeve sleeve = new Sleeve();
    		Shape shape = new Shape();
    		Collar collar = new Collar();
    		Height height = new Height();
    		Material material = new Material();
    		Maker maker = new Maker();
    		Product product = new Product();
    		ProductsLength productsLength = new ProductsLength();
    		
    		length.setId((Long) row.get("length_id"));
    		length.setLabel((String) row.get("length_label"));
    		
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
    		
    		product.setId((Long) row.get("id"));
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
