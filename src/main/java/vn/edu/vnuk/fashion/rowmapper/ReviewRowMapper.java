package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.Order;
import vn.edu.vnuk.fashion.model.Review;

public class ReviewRowMapper implements RowMapper<Review> {

	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		Order order = new Order();
		Review review = new Review();
			
		order.setId(rs.getLong("order_id"));
		order.setPriceId(rs.getLong("price_id"));
		order.setCustomerId(rs.getLong("customer_id"));
		order.setQty(rs.getInt("order_qty"));
		
		review.setId(rs.getLong("id"));
		review.setOrderId(rs.getLong("order_id"));
		review.setOrder(order);
		review.setRating(rs.getInt("rating"));
		review.setDescription(rs.getString("description"));
		
		return review;
	}
	
	
	public List<Review> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Review> reviews = new ArrayList<Review>();
		
		
    	for (Map<String, Object> row : rows) {			
    		Order order = new Order();
    		Review review = new Review();
    		
    		order.setId((Long) row.get("order_id"));
    		order.setCustomerId((Long) row.get("customer_id"));
    		order.setQty((int) row.get("order_qty"));
    		order.setPriceId((Long) row.get("price_id"));
    		
    		review.setId((Long) row.get("id"));
    		review.setOrderId((Long) row.get("order_id"));
    		review.setRating((int) row.get("rating"));
    		review.setDescription((String) row.get("description"));
    		review.setOrder(order);
    		
			reviews.add(review);			
		}
		
    	
		return reviews;

	}

}
