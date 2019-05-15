package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.Title;
import vn.edu.vnuk.fashion.model.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Title title = new Title();
		Customer customer = new Customer();
		
		title.setId(rs.getLong("title_id"));
		title.setLabel(rs.getString("title_label"));
		
		customer.setId(rs.getLong("id"));
		customer.setTitleId(rs.getLong("title_id"));
		customer.setLabel(rs.getString("label"));
		customer.setAddress(rs.getString("address"));
		customer.setPhone(rs.getString("phone"));
		customer.setEmail(rs.getString("email"));
		customer.setTitle(title);
		
		return customer;
	}
	
	
	public List<Customer> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Customer> customers = new ArrayList<Customer>();
		
		
    	for (Map<String, Object> row : rows) {
			
			Title title = new Title();
			Customer customer = new Customer();
			
			title.setId((Long) row.get("title_id"));
			title.setLabel((String) row.get("title_label"));
			
			customer.setId((Long) row.get("id"));
			customer.setTitleId((Long) row.get("title_id"));
			customer.setLabel((String) row.get("label"));
			customer.setAddress((String) row.get("address"));
			customer.setEmail((String) row.get("email"));
			customer.setPhone((String) row.get("phone"));
			
			customer.setTitle(title);
			
			customers.add(customer);
			
		}
		
    	
		return customers;

	}

}
