package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.Category;
import vn.edu.vnuk.fashion.model.Subcategory;

public class SubcategoryRowMapper implements RowMapper<Subcategory> {

	@Override
	public Subcategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Category category = new Category();
		Subcategory subcategory = new Subcategory();
		
		category.setId(rs.getLong("category_id"));
		category.setLabel(rs.getString("category_label"));
		
		subcategory.setId(rs.getLong("id"));
		subcategory.setCategoryId(rs.getLong("category_id"));
		subcategory.setLabel(rs.getString("label"));
		subcategory.setCategory(category);
		
		return subcategory;
	}
	
	
	public List<Subcategory> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Subcategory> subcategories = new ArrayList<Subcategory>();
		
		
    	for (Map<String, Object> row : rows) {
			
			Category category = new Category();
			Subcategory subcategory = new Subcategory();
			
			category.setId((Long) row.get("category_id"));
			category.setLabel((String) row.get("category_label"));
			
			subcategory.setId((Long) row.get("id"));
			subcategory.setCategoryId((Long) row.get("category_id"));
			subcategory.setLabel((String) row.get("label"));
			subcategory.setCategory(category);
			
			subcategories.add(subcategory);
			
		}
		
    	
		return subcategories;

	}

}
