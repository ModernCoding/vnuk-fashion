package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.helper.DaoHelpers;
import vn.edu.vnuk.fashion.model.SubcategoriesBodyPart;
import vn.edu.vnuk.fashion.rowmapper.SubcategoriesBodyPartRowMapper;


@Repository
public class SubcategoriesBodyPartDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    //  CREATE
    public void create(SubcategoriesBodyPart subcategoriesBodyPart) throws SQLException{

        String sqlQuery = "insert into subcategories_body_parts (subcategory_id, body_part_id) "
                        +	"values (? , ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new subcategoryBpdyPart in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								subcategoriesBodyPart.getSubcategoryId(),
            								subcategoriesBodyPart.getBodyPartId()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of SubcategoriesBodyPart)
    public List<SubcategoriesBodyPart> read(String subcategoryId, String bodyPartId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t02.id as subcategory_id"
						+ "     , t02.label as subcategory_label"
						+ "     , t03.id as body_part_id"
						+ "     , t03.label as body_part_label "
						+ "from subcategories_body_parts t01 "
						+ "inner join subcategories t02 on t01.subcategory_id = t02.id "
						+ "inner join body_parts t03 on t01.body_part_id = t03.id ";
    	
    	if (subcategoryId != null)
    		sqlQuery += DaoHelpers.addConditionForQuery(sqlQuery, "t01.subcategory_id", subcategoryId);
    	
    	if (bodyPartId != null)
    		sqlQuery += DaoHelpers.addConditionForQuery(sqlQuery, "t01.body_part_id", bodyPartId);
    	
    	sqlQuery += " order by t01.id asc, t02.id asc, t03.id asc;";
    	
    	
        try {
        	
        	return new SubcategoriesBodyPartRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single SubcategoryBodyPart)
    public SubcategoriesBodyPart read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as subcategory_id"
				+ "     , t02.label as subcategory_label"
				+ "     , t02.category_id as category_id"
				+ "     , t03.id as body_part_id"
				+ "     , t03.label as body_part_label "
				+ "from subcategories_body_parts t01 "
				+ "inner join subcategories t02 on t01.subcategory_id = t02.id "
				+ "inner join body_parts t03 on t01.body_part_id = t03.id "
				+ "where t01.id = ? "
				+ "order by t01.id asc, t02.id asc, t03.id asc;";

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new SubcategoriesBodyPartRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(SubcategoriesBodyPart subcategoriesBodyPart) throws SQLException {
        
    	String sqlQuery = "update subcategories_body_parts set subcategory_id=?, body_part_id=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							subcategoriesBodyPart.getSubcategoryId(),
							subcategoriesBodyPart.getBodyPartId(),
							subcategoriesBodyPart.getId(),
						}
				);
            
            
            System.out.println("SubcategoryBodyPart successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from subcategories_body_parts where id=?";

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