package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;

public class Sql0000RunAllQueries {

	public static void main(String[] args) throws SQLException {
		
		Connection connectionDb = new ConnectionFactory()
				.getConnection("jdbc:mysql://localhost/");
		
		// Create database
		
		new Sql1000DropDatabase(connectionDb).run();
		new Sql1010CreateDatabase(connectionDb).run();

		connectionDb.close();
		
		//Create tbels 
		
		Connection connectionTbel = new ConnectionFactory()
				.getConnection("jdbc:mysql://localhost/vnuk_e_commerce_fashion");
		
		new Sql2010CreateCategories(connectionTbel).run();
		new Sql2020CreateSubcategories(connectionTbel).run();
		new Sql2030CreateGenders(connectionTbel).run();
		new Sql2040CreateBodyParts(connectionTbel).run();
		new Sql2050CreateSleeves(connectionTbel).run();
		new Sql2060CreatePatterns(connectionTbel).run();
		new Sql2070CreateLengths(connectionTbel).run();
		new Sql2080CreateShapes(connectionTbel).run();
		new Sql2090CreateCollars(connectionTbel).run();
		new Sql2100CreateColors(connectionTbel).run();
		new Sql2110CreateSizes(connectionTbel).run();
		new Sql2120CreateTitles(connectionTbel).run();
		new Sql2130CreateSellers(connectionTbel).run();
		new Sql2140CreateMakers(connectionTbel).run();
		new Sql2150CreatePriceTypes(connectionTbel).run();
		new Sql2160CreateSubcategoriesBodyParts(connectionTbel).run();
		new Sql2230CreateHeights(connectionTbel).run();
		new Sql2240CreateMaterials(connectionTbel).run();
		new Sql2170CreateProducts(connectionTbel).run();
		new Sql2171CreateProductsSizes(connectionTbel).run();
		new Sql2172CreateProductsColors(connectionTbel).run();
		new Sql2173CreateProductsPatterns(connectionTbel).run();
		new Sql2174CreateProductsLengths(connectionTbel).run();
		new Sql2180CreateProductsGenders(connectionTbel).run();
		new Sql2190CreatePrices(connectionTbel).run();
		new Sql2200CreateCustomers(connectionTbel).run();
		new Sql2210CreateOrders(connectionTbel).run();
		new Sql2220CreateReviews(connectionTbel).run();
		
		
		//Insert data into tbels
		
		new Sql5010InsertIntoCategories(connectionTbel).run();
		new Sql5020InsertIntoSubcategories(connectionTbel).run();
		new Sql5030InsertIntoGenders(connectionTbel).run();
		new Sql5040InsertIntoBodyParts(connectionTbel).run();
		new Sql5050InsertIntoSleeves(connectionTbel).run();
		new Sql5060InsertIntoPatterns(connectionTbel).run();
		new Sql5070InsertIntoLengths(connectionTbel).run();
		new Sql5080InsertIntoShapes(connectionTbel).run();
		new Sql5090InsertIntoCollars(connectionTbel).run();
		new Sql5100InsertIntoColors(connectionTbel).run();
		new Sql5110InsertIntoSizes(connectionTbel).run();
		new Sql5120InsertIntoTitles(connectionTbel).run();
		new Sql5130InsertIntoSellers(connectionTbel).run();
		new Sql5140InsertIntoMakers(connectionTbel).run();
		new Sql5150InsertIntoPriceTypes(connectionTbel).run();
		new Sql5160InsertIntoSubcategoriesBodyParts(connectionTbel).run();
		new Sql5230InsertIntoHeights(connectionTbel).run();
		new Sql5240InsertIntoMaterials(connectionTbel).run();
		new Sql5170InsertIntoProducts(connectionTbel).run();
		new Sql5171InsertIntoProductsSizes(connectionTbel).run();
		new Sql5172InsertIntoProductsColors(connectionTbel).run();
		new Sql5173InsertIntoProductsPatterns(connectionTbel).run();
		new Sql5174InsertIntoProductsLengths(connectionTbel).run();
		new Sql5180InsertIntoProductsGenders(connectionTbel).run();
		new Sql5190InsertIntoPrices(connectionTbel).run();
		new Sql5200InsertIntoCustomers(connectionTbel).run();
		new Sql5210InsertIntoOrders(connectionTbel).run();
		new Sql5220InsertIntoReviews(connectionTbel).run();
		
		connectionTbel.close();
		
	}

}
