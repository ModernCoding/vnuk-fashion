package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.Customer;
import vn.edu.vnuk.fashion.model.Order;
import vn.edu.vnuk.fashion.model.Price;

public class OrderRowMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Price price = new Price();
		Customer customer = new Customer();
		Order order = new Order();
		
		
		price.setId(rs.getLong("price_id"));
		price.setValue(rs.getFloat("price_value"));
				
		customer.setId(rs.getLong("customer_id"));
		customer.setLabel(rs.getString("customer_label"));
		
		order.setId(rs.getLong("id"));
		order.setPriceId(rs.getLong("price_id"));
		order.setPrice(price);
		order.setCustomerId(rs.getLong("customer_id"));
		order.setCustomer(customer);
		order.setQty(rs.getInt("qty"));
		
		return order;
	}
	
	
	public List<Order> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Order> orders = new ArrayList<Order>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Price price = new Price();
    		Customer customer = new Customer();
    		Order order = new Order();
    		
    		
    		price.setId((Long) row.get("price_id"));
    		price.setValue((Float) row.get("price_value"));
    		
    		
    		customer.setId((Long) row.get("customer_id"));
    		customer.setLabel((String) row.get("customer_label"));
    		
    		order.setId((Long) row.get("id"));
    		order.setCustomerId((Long) row.get("customer_id"));
    		order.setCustomer(customer);
    		order.setQty((int) row.get("qty"));
    		order.setPriceId((Long) row.get("price_id"));
    		order.setPrice(price);

    		
			orders.add(order);
			
		}
		
    	
		return orders;

	}

}
