package vn.edu.vnuk.fashion.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.fashion.helper.DaoHelpers;
import vn.edu.vnuk.fashion.model.Customer;
import vn.edu.vnuk.fashion.rowmapper.CustomerRowMapper;


@Repository
public class CustomerDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
    
    //  CREATE
    public void create(Customer customer) throws SQLException{

        String sqlQuery = "insert into customers (title_id, label, address, phone, email) "
                        +	"values (?, ?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new customer in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								customer.getTitleId(),
            								customer.getLabel(),
            								customer.getAddress(),
            								customer.getPhone(),
            								customer.getEmail()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Customers)
    public List<Customer> read(Customer customer) throws SQLException {
    	
    	String sqlQuery = "select customers.id"
		    			+ "     , customers.label "
		    			+ "     , titles.id as title_id"
						+ "     , titles.label as title_label"
						+ "     , customers.address "
						+ "     , customers.phone "
						+ "     , customers.email "
						+ "from customers "
						+ "inner join titles on customers.title_id = titles.id ";
    	
    	if (customer.getTitleId() != null)
    		sqlQuery = DaoHelpers.addConditionForQuery(sqlQuery, "titles.id", String.valueOf(customer.getTitleId()));
    	
    	sqlQuery += " order by customers.id asc, titles.id asc;";
    	
    	
        try {
        	
        	return new CustomerRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single Customer)
    public Customer read(Long id) throws SQLException{

    	String sqlQuery = "select customers.id"
    			+ "     , customers.label as label"
    			+ "     , titles.id as title_id"
				+ "     , titles.label as title_label"
				+ "     , customers.address as address"
				+ "     , customers.phone as phone"
				+ "     , customers.email as email "
				+ "from customers "
				+ "inner join titles on customers.title_id = titles.id "
				+ "where customers.id = ?;";

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new CustomerRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(Customer customer) throws SQLException {
        
    	String sqlQuery = "update customers set title_id=?, label=?, address=?, phone=?, email=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							customer.getTitleId(),
							customer.getLabel(),
							customer.getAddress(),
							customer.getPhone(),
							customer.getEmail(),
							customer.getId()
						}
				);
            
            
            System.out.println("Customer successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from customers where id=?";

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