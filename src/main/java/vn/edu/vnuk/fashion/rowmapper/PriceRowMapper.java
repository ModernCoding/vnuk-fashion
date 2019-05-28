package vn.edu.vnuk.fashion.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.fashion.model.Category;
import vn.edu.vnuk.fashion.model.Collar;
import vn.edu.vnuk.fashion.model.Color;
import vn.edu.vnuk.fashion.model.Height;
import vn.edu.vnuk.fashion.model.Length;
import vn.edu.vnuk.fashion.model.Maker;
import vn.edu.vnuk.fashion.model.Material;
import vn.edu.vnuk.fashion.model.Pattern;
import vn.edu.vnuk.fashion.model.Price;
import vn.edu.vnuk.fashion.model.PriceType;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsColor;
import vn.edu.vnuk.fashion.model.ProductsLength;
import vn.edu.vnuk.fashion.model.ProductsPattern;
import vn.edu.vnuk.fashion.model.ProductsSize;
import vn.edu.vnuk.fashion.model.Seller;
import vn.edu.vnuk.fashion.model.Shape;
import vn.edu.vnuk.fashion.model.Size;
import vn.edu.vnuk.fashion.model.Sleeve;
import vn.edu.vnuk.fashion.model.Subcategory;

public class PriceRowMapper implements RowMapper<Price> {

	@Override
	public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ProductsSize productsSize = new ProductsSize();
		ProductsPattern productsPattern = new ProductsPattern();
		ProductsLength productsLength = new ProductsLength();
		ProductsColor productsColor = new ProductsColor();
		Seller seller = new Seller();
		PriceType priceType = new PriceType();
		Price price = new Price();
		
		seller.setId(rs.getLong("seller_id"));
		seller.setLabel(rs.getString("seller_label"));
		
		priceType.setId(rs.getLong("price_type_id"));
		priceType.setLabel(rs.getString("price_type_label"));
		
		price.setId(rs.getLong("id"));
		price.setValue(rs.getFloat("value"));
		price.setProductsSizeId(rs.getLong("products_size_id"));
		price.setProductsSize(productsSize);
		price.setProductsPatternId(rs.getLong("products_pattern_id"));
		price.setProductsPattern(productsPattern);
		price.setProductsLengthId(rs.getLong("products_length_id"));
		price.setProductsLength(productsLength);
		price.setProductsColorId(rs.getLong("products_color_id"));
		price.setProductsColor(productsColor);
		price.setSellerId(rs.getLong("seller_id"));
		price.setSeller(seller);
		price.setPriceTypeId(rs.getLong("price_type_id"));
		price.setPriceType(priceType);
		
		return price;
	}
	
	
	public List<Price> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Price> prices = new ArrayList<Price>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		ProductsSize productsSize = new ProductsSize();
    		ProductsPattern productsPattern = new ProductsPattern();
    		ProductsLength productsLength = new ProductsLength();
    		ProductsColor productsColor = new ProductsColor();
    		Seller seller = new Seller();
    		PriceType priceType = new PriceType();
    		Price price = new Price();
    		
    		
    		seller.setId((Long) row.get("seller_id"));
    		seller.setLabel((String) row.get("seller_label"));
    		
    		priceType.setId((Long) row.get("price_type_id"));
    		priceType.setLabel((String) row.get("price_type_label"));
    		
    		price.setId((Long) row.get("id"));
    		price.setProductsSizeId((Long) row.get("products_size_id"));
    		price.setProductsSize(productsSize);
    		price.setProductsColorId((Long) row.get("productsColor_id"));
    		price.setProductsColor(productsColor);
    		price.setProductsLengthId((Long) row.get("productsLength_id"));
    		price.setProductsLength(productsLength);
    		price.setProductsPatternId((Long) row.get("productsPattern_id"));
    		price.setProductsPattern(productsPattern);
    		price.setSellerId((Long) row.get("seller_id"));
    		price.setSeller(seller);
    		price.setPriceTypeId((Long) row.get("price_type_id"));
    		price.setPriceType(priceType);
    		price.setValue((Float) row.get("value"));
    		 		
			prices.add(price);
			
		}
		    	
		return prices;

	}

}
