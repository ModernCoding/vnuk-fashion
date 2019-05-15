package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Size;




@Repository
public class SizeDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public SizeDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Size size) throws SQLException{

        String sqlQuery = "insert into sizes (universal, us, uk, france, italy, germany, australia, japan) values (? , ? , ? , ? , ? , ? , ? , ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new collar in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								size.getUniversal(),
            								size.getUs(),
            								size.getUk(),
            								size.getFrance(),
            								size.getItaly(),
            								size.getGermany(),
            								size.getAustralia(),
            								size.getJapan()
            								}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Sizes)
    public List<Size> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM sizes",
        			new BeanPropertyRowMapper<Size>(Size.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Size)
    public Size read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM sleeves where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Size>(Size.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Size size) throws SQLException {
    	
        String sqlQuery = "update sizes set universal=? us=? uk=? france=? italy=? germany=? australia=? japan=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							size.getUniversal(),
							size.getUs(),
							size.getUk(),
							size.getFrance(),
							size.getItaly(),
							size.getGermany(),
							size.getAustralia(),
							size.getJapan(),
							size.getId()
						}
				);
            
            
            System.out.println("Size successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from sizes where id=?";

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