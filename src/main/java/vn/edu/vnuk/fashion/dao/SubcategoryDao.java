package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Subcategory;
import vn.edu.vnuk.fashion.rowmapper.SubcategoryRowMapper;


@Repository
public class SubcategoryDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    //  CREATE
    public void create(Subcategory subcategory) throws SQLException{

        String sqlQuery = "insert into subcategories (category_id, label) "
                        +	"values (? , ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new subcategory in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								subcategory.getCategoryId(),
            								subcategory.getLabel()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Subcategories)
    public List<Subcategory> read(String categoryId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.label"
		    			+ "     , t02.id as category_id"
						+ "     , t02.label as category_label"
						+ "  from subcategories t01, categories t02"
						+ " where t02.id = t01.category_id"
				;
    	
    	if (categoryId != null) {
    		sqlQuery += String.format("   and t02.id = %s", categoryId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t01.id asc, t02.id asc;";
    	}
    	
    	
        try {
        	
        	return new SubcategoryRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single Subcategory)
    public Subcategory read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t01.label"
    			+ "     , t02.id as category_id"
				+ "     , t02.label as category_label"
				+ "  from subcategories t01, categories t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.category_id"
				+ " order by t01.id asc, t02.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new SubcategoryRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(Subcategory subcategory) throws SQLException {
        
    	String sqlQuery = "update subcategories set category_id=?, label=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							subcategory.getCategoryId(),
							subcategory.getLabel(),
							subcategory.getId(),
						}
				);
            
            
            System.out.println("Subcategory successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from subcategories where id=?";

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