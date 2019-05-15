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
import vn.edu.vnuk.fashion.model.Customer;
import vn.edu.vnuk.fashion.model.Height;
import vn.edu.vnuk.fashion.model.Length;
import vn.edu.vnuk.fashion.model.Maker;
import vn.edu.vnuk.fashion.model.Material;
import vn.edu.vnuk.fashion.model.Order;
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
import vn.edu.vnuk.fashion.model.Title;

public class OrderRowMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		Category category = new Category();
		Subcategory subcategory = new Subcategory();
		Sleeve sleeve = new Sleeve();
		Shape shape = new Shape();
		Collar collar = new Collar();
		Height height = new Height();
		Material material = new Material();
		Maker maker = new Maker();
		Product product = new Product();
		Pattern pattern = new Pattern();
		Color color = new Color();
		Length length = new Length();
		Size size = new Size();
		ProductsSize productsSize = new ProductsSize();
		ProductsPattern productsPattern = new ProductsPattern();
		ProductsLength productsLength = new ProductsLength();
		ProductsColor productsColor = new ProductsColor();
		Seller seller = new Seller();
		PriceType priceType = new PriceType();
		Price price = new Price();
		Customer customer = new Customer();
		Title title = new Title();
		Order order = new Order();
		
		
		category.setId(rs.getLong("category_id"));
		category.setLabel(rs.getString("category_label"));
		
		subcategory.setId(rs.getLong("subcategory_id"));
		subcategory.setCategoryId(rs.getLong("category_id"));
		subcategory.setLabel(rs.getString("subcategory_label"));
		subcategory.setCategory(category);
		
		sleeve.setId(rs.getLong("sleeve_id"));
		sleeve.setLabel(rs.getString("sleeve_label"));
		
		shape.setId(rs.getLong("shape_id"));
		shape.setLabel(rs.getString("shape_label"));
		
		collar.setId(rs.getLong("collar_id"));
		collar.setLabel(rs.getString("collar_label"));
		
		height.setId(rs.getLong("height_id"));
		height.setLabel(rs.getString("height_label"));
		
		material.setId(rs.getLong("material_id"));
		material.setLabel(rs.getString("material_label"));
		
		maker.setId(rs.getLong("maker_id"));
		maker.setLabel(rs.getString("maker_label"));
		
		product.setId(rs.getLong("product_id"));
		product.setName(rs.getString("name"));
		product.setSubcategoryId(rs.getLong("subcategory_id"));
		product.setSubcategory(subcategory);
		product.setSleeveId(rs.getLong("sleeve_id"));
		product.setSleeve(sleeve);
		product.setShapeId(rs.getLong("shape_id"));
		product.setShape(shape);
		product.setCollarId(rs.getLong("collar_id"));
		product.setCollar(collar);
		product.setHeightId(rs.getLong("height_id"));
		product.setHeight(height);
		product.setMaterialId(rs.getLong("material_id"));
		product.setMaterial(material);
		product.setMakerId(rs.getLong("maker_id"));
		product.setMaker(maker);
		
		pattern.setId(rs.getLong("pattern_id"));
		pattern.setLabel(rs.getString("pattern_label"));
		
		size.setId(rs.getLong("size_id"));
		size.setUniversal(rs.getString("size_universal"));
		size.setUs(rs.getString("size_us"));
		size.setUk(rs.getString("size_uk"));
		size.setFrance(rs.getString("size_france"));
		size.setGermany(rs.getString("size_germany"));
		size.setAustralia(rs.getString("size_australian"));
		size.setJapan(rs.getString("size_japan"));
		
		color.setId(rs.getLong("color_id"));
		color.setLabel(rs.getString("color_label"));
		
		length.setId(rs.getLong("length_id"));
		length.setLabel(rs.getString("length_label"));
		
		seller.setId(rs.getLong("seller_id"));
		seller.setLabel(rs.getString("seller_label"));
		seller.setAddress(rs.getString("seller_address"));
		seller.setEmail(rs.getString("seller_email"));
		seller.setPhone(rs.getString("seller_phone"));
		
		priceType.setId(rs.getLong("priceType_id"));
		priceType.setLabel(rs.getString("priceType_label"));
		
		price.setId(rs.getLong("price_id"));
		price.setValue(rs.getFloat("value"));
		price.setProductsSizeId(rs.getLong("productsSize_id"));
		price.setProductsSize(productsSize);
		price.setProductsPatternId(rs.getLong("productsPattern_id"));
		price.setProductsPattern(productsPattern);
		price.setProductsLengthId(rs.getLong("productsLength_id"));
		price.setProductsLength(productsLength);
		price.setProductsColorId(rs.getLong("productsColor_id"));
		price.setProductsColor(productsColor);
		price.setSellerId(rs.getLong("seller_id"));
		price.setSeller(seller);
		price.setPriceTypeId(rs.getLong("priceType_id"));
		price.setPriceType(priceType);
		
		title.setId(rs.getLong("title_id"));
		title.setLabel(rs.getString("title_lable"));
		
		customer.setId(rs.getLong("customer_id"));
		customer.setEmail(rs.getString("customer_email"));
		customer.setTitleId(rs.getLong("title_id"));
		customer.setLabel(rs.getString("customer_label"));
		customer.setPhone(rs.getString("customer_phone"));
		customer.setTitle(title);
		customer.setAddress(rs.getString("customer_address"));
		
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
			
    		Category category = new Category();
    		Subcategory subcategory = new Subcategory();
    		Sleeve sleeve = new Sleeve();
    		Shape shape = new Shape();
    		Collar collar = new Collar();
    		Height height = new Height();
    		Material material = new Material();
    		Maker maker = new Maker();
    		Product product = new Product();
    		Pattern pattern = new Pattern();
    		Color color = new Color();
    		Length length = new Length();
    		Size size = new Size();
    		ProductsSize productsSize = new ProductsSize();
    		ProductsPattern productsPattern = new ProductsPattern();
    		ProductsLength productsLength = new ProductsLength();
    		ProductsColor productsColor = new ProductsColor();
    		Seller seller = new Seller();
    		PriceType priceType = new PriceType();
    		Price price = new Price();
    		Customer customer = new Customer();
    		Title title = new Title();
    		Order order = new Order();
    		
    		category.setId((Long) row.get("category_id"));
    		category.setLabel((String) row.get("category_label"));
    		
    		subcategory.setId((Long) row.get("subcategory_id"));
    		subcategory.setCategoryId((Long) row.get("category_id"));
    		subcategory.setLabel((String) row.get("subcategory_label"));
    		subcategory.setCategory(category);
    		
    		sleeve.setId((Long) row.get("sleeve_id"));
    		sleeve.setLabel((String) row.get("sleeve_label"));
    		
    		shape.setId((Long) row.get("shape_id"));
    		shape.setLabel((String) row.get("shape_label"));
    		
    		collar.setId((Long) row.get("collar_id"));
    		collar.setLabel((String) row.get("collar_label"));
    		
    		height.setId((Long) row.get("height_id"));
    		height.setLabel((String) row.get("height_label"));
    		
    		material.setId((Long) row.get("material_id"));
    		material.setLabel((String) row.get("material_label"));
    		
    		maker.setId((Long) row.get("maker_id"));
    		maker.setLabel((String) row.get("maker_label"));
    		
    		product.setId((Long) row.get("product_id"));
    		product.setName((String) row.get("name"));
    		product.setSubcategoryId((Long) row.get("subcategory_id"));
    		product.setSubcategory(subcategory);
    		product.setSleeveId((Long) row.get("sleeve_id"));
    		product.setSleeve(sleeve);
    		product.setShapeId((Long) row.get("shape_id"));
    		product.setShape(shape);
    		product.setCollarId((Long) row.get("collar_id"));
    		product.setCollar(collar);
    		product.setHeightId((Long) row.get("height_id"));
    		product.setHeight(height);
    		product.setMaterialId((Long) row.get("material_id"));
    		product.setMaterial(material);
    		product.setMakerId((Long) row.get("maker_id"));
    		product.setMaker(maker);
			
    		pattern.setId((Long) row.get("pattern_id"));
    		pattern.setLabel((String) row.get("pattern_label"));
    		
    		color.setId((Long) row.get("color_id"));
    		color.setLabel((String) row.get("color_label"));
    		
    		length.setId((Long) row.get("length_id"));
    		length.setLabel((String) row.get("length_label"));
    		
    		size.setId((Long) row.get("size_id"));
    		size.setUniversal((String) row.get("size_universal"));
    		size.setUs((String) row.get("size_us"));
    		size.setUk((String) row.get("size_uk"));
    		size.setFrance((String) row.get("size_france"));
    		size.setItaly((String) row.get("size_italy"));
    		size.setGermany((String) row.get("size_germany"));
    		size.setAustralia((String) row.get("size_australia"));
    		size.setJapan((String) row.get("size_japan"));
    		
    		seller.setId((Long) row.get("seller_id"));
    		seller.setLabel((String) row.get("seller_label"));
    		seller.setAddress((String) row.get("seller_addresss"));
    		seller.setEmail((String) row.get("seller_email"));
    		seller.setPhone((String) row.get("seller_phone"));
    		
    		priceType.setId((Long) row.get("priceType_id"));
    		priceType.setLabel((String) row.get("priceType_label"));
    		
    		price.setId((Long) row.get("price_id"));
    		price.setProductsSizeId((Long) row.get("productsSize_id"));
    		price.setProductsSize(productsSize);
    		price.setProductsColorId((Long) row.get("productsColor_id"));
    		price.setProductsColor(productsColor);
    		price.setProductsLengthId((Long) row.get("productsLength_id"));
    		price.setProductsLength(productsLength);
    		price.setProductsPatternId((Long) row.get("productsPattern_id"));
    		price.setProductsPattern(productsPattern);
    		price.setSellerId((Long) row.get("seller_id"));
    		price.setSeller(seller);
    		price.setPriceTypeId((Long) row.get("priceType_id"));
    		price.setPriceType(priceType);
    		price.setValue((Float) row.get("value"));
    		
    		title.setId((Long) row.get("title_id"));
    		title.setLabel((String) row.get("title_label"));
    		
    		customer.setId((Long) row.get("customer_id"));
    		customer.setAddress((String) row.get("customer_address"));
    		customer.setEmail((String) row.get("customer_email"));
    		customer.setLabel((String) row.get("customer_label"));
    		customer.setPhone((String) row.get("customer_phone"));
    		customer.setTitleId((Long) row.get("title_id"));
    		customer.setTitle(title);
    		
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
