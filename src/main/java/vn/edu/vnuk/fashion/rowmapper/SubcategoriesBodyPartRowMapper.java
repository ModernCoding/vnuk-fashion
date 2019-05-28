package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.BodyPart;
import vn.edu.vnuk.fashion.model.Category;
import vn.edu.vnuk.fashion.model.SubcategoriesBodyPart;
import vn.edu.vnuk.fashion.model.Subcategory;

public class SubcategoriesBodyPartRowMapper implements RowMapper<SubcategoriesBodyPart> {

	@Override
public SubcategoriesBodyPart mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BodyPart bodyPart = new BodyPart();
		Subcategory subcategory = new Subcategory();
		SubcategoriesBodyPart subcategoriesBodyPart = new SubcategoriesBodyPart();
		
		bodyPart.setId(rs.getLong("body_part_id"));
		bodyPart.setLabel(rs.getString("body_part_label"));
		
		subcategory.setId(rs.getLong("subcategory_id"));
		subcategory.setLabel(rs.getString("subcategory_label"));
		
		subcategoriesBodyPart.setId(rs.getLong("id"));
		subcategoriesBodyPart.setSubcategoryId(rs.getLong("subcategory_id"));
		subcategoriesBodyPart.setBodyPartId(rs.getLong("body_part_id"));
		subcategoriesBodyPart.setSubcategory(subcategory);
		subcategoriesBodyPart.setBodyPart(bodyPart);
		
		return subcategoriesBodyPart;
	}
	
	
	public List<SubcategoriesBodyPart> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<SubcategoriesBodyPart> subcategoriesBodyParts = new ArrayList<SubcategoriesBodyPart>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		BodyPart bodyPart = new BodyPart();
    		Subcategory subcategory = new Subcategory();
    		SubcategoriesBodyPart subcategoriesBodyPart = new SubcategoriesBodyPart();
			
    		bodyPart.setId((Long) row.get("body_part_id"));
    		bodyPart.setLabel((String) row.get("body_part_label"));
    		
			subcategory.setId((Long) row.get("subcategory_id"));
			subcategory.setLabel((String) row.get("subcategory_label"));
			
			subcategoriesBodyPart.setId((Long) row.get("id"));
			subcategoriesBodyPart.setSubcategoryId((Long) row.get("subcategory_id"));
			subcategoriesBodyPart.setBodyPartId((Long) row.get("body_part_id"));
			subcategoriesBodyPart.setSubcategory(subcategory);
			subcategoriesBodyPart.setBodyPart(bodyPart);
			
			subcategoriesBodyParts.add(subcategoriesBodyPart);
			
		}
		
    	
		return subcategoriesBodyParts;

	}

}
