package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.model.Maker;


@Repository
public class MakerDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public MakerDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Maker maker) throws SQLException{

        String sqlQuery = "insert into makers (label, address, phone, email) values (?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new maker in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								maker.getLabel(),
            								maker.getAddress(),
            								maker.getPhone(),
            								maker.getEmail()
            								
            								}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Makers)
    public List<Maker> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM makers",
        			new BeanPropertyRowMapper<Maker>(Maker.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Maker)
    public Maker read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM makers where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Maker>(Maker.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Maker maker) throws SQLException {
    	
        String sqlQuery = "update makers set label=? address=? phone=? email=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							maker.getLabel(),
							maker.getAddress(),
							maker.getPhone(),
							maker.getEmail(),
							maker.getId()
						}
				);
            
            
            System.out.println("Maker successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from makers where id=?";

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